package br.edu.restinga.ifrs.adotemeau.http.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.UserDTO;
import br.edu.restinga.ifrs.adotemeau.services.UserService;

@RestController
@RequestMapping("/api/v1/admin/user")
public class AdminUserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @DeleteMapping("")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.delete(id));
    }
}