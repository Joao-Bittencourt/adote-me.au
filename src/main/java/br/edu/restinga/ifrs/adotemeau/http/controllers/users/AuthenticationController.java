package br.edu.restinga.ifrs.adotemeau.http.controllers.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.request.AuthenticationRequest;
import br.edu.restinga.ifrs.adotemeau.http.dtos.request.RegisterRequest;
import br.edu.restinga.ifrs.adotemeau.http.dtos.response.AuthenticationResponse;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.services.authentication.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @GetMapping("/me")
  public ResponseEntity<User> me(@RequestHeader(value = "Authorization") String authorization) throws Exception {
    return ResponseEntity.ok(service.getUser(authorization));
  }
}
