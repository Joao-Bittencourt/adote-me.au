package br.edu.restinga.ifrs.adotemeau.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.exceptions.InvalidField;
import br.edu.restinga.ifrs.adotemeau.http.dtos.UserDTO;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.repositories.UserRepository;
import br.edu.restinga.ifrs.adotemeau.services.authentication.AuthenticationService;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Transactional
    public UserDTO create(User user) {

        userRepository.save(user);
        UserDTO userDto = new UserDTO(user);

        return userDto;
    }

    public List<UserDTO> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return UserDTO.convertList(users);
    }

    public UserDTO findById(Long id) {
        var user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new InvalidField("id", "Não existe usuário com este id!");
        } 

        UserDTO userDto = new UserDTO(user.get());
        return userDto;
    }

    @Transactional
    public UserDTO update(User updateUser, String token) throws Exception {
        User user = authenticationService.getUser(token);

        if(updateUser.getName() != null)
            user.setName(updateUser.getName());
        if(updateUser.getPassword() != null)
            user.setPassword(updateUser.getPassword());
        if(updateUser.getEmail() != null)
            user.setEmail(updateUser.getEmail());
        
        UserDTO userDto = new UserDTO(this.userRepository.save(user));

        return userDto;
    }

    @Transactional
    public String delete(Long id) {
        Optional<User> optional = this.userRepository.findById(id);

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe usuário com este id!");
        }

        User user = optional.get();
        this.userRepository.deleteById(id);
        return String.format("Usuário %s foi deletado com sucesso", user.getName());
    }
}
