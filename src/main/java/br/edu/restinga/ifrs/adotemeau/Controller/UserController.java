package br.edu.restinga.ifrs.adotemeau.Controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.Model.User;
import br.edu.restinga.ifrs.adotemeau.Service.UserService;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;
    
    //Cadastrar um novo usuário → POST/user
    @PostMapping("/user")
    public ResponseEntity<User> create(@Valid @RequestBody User user) throws URISyntaxException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    //Listar todos os usuários → GET /user
    @GetMapping("/user")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    //Localizar um usuário → GET /user/{id}
    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    //Atualizar um usuário → UPDATE /user/{id}
    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userService.update(user, id));
    }

    //Deletar um usuário → DELETE /user/{id}
    @DeleteMapping("user/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }
}