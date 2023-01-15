package br.edu.restinga.ifrs.adotemeau.http.controllers.users;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.UserDTO;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    //Atualizar um usuário → UPDATE /user/{id}
    @PutMapping("")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody User user, @RequestHeader(value = "Authorization") String authorization) throws Exception {
        return ResponseEntity.ok().body(userService.update(user, authorization));
    }
}