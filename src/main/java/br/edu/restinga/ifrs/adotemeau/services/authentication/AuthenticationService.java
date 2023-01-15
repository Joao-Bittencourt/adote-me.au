package br.edu.restinga.ifrs.adotemeau.services.authentication;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.exceptions.InvalidField;
import br.edu.restinga.ifrs.adotemeau.http.dtos.request.AuthenticationRequest;
import br.edu.restinga.ifrs.adotemeau.http.dtos.request.RegisterRequest;
import br.edu.restinga.ifrs.adotemeau.http.dtos.response.AuthenticationResponse;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.models.enums.Role;
import br.edu.restinga.ifrs.adotemeau.repositories.UserRepository;
import br.edu.restinga.ifrs.adotemeau.services.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidField("email", "Já existe este e-mail cadastrado em nossa base!");
        }
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public User getUser(String token) throws Exception {
        if (token.contains("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        Optional<User> user = userRepository.findByEmail(jwtService.extractUsername(token));
        if (user.isPresent()) {
            return user.get();
        }
        throw new Exception("User not found");
    }
}