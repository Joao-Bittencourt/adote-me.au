package br.edu.restinga.ifrs.adotemeau.http.controllers.users;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalTemperamentDTO;
import br.edu.restinga.ifrs.adotemeau.models.AnimalTemperament;
import br.edu.restinga.ifrs.adotemeau.services.AnimalTemperamentService;

@RestController
@RequestMapping("/animal/temperament")
public class AnimalTemperamentController {

    @Autowired
    AnimalTemperamentService animalTemperamentService;

    @GetMapping("/find")
    public ResponseEntity<List<AnimalTemperament>> findAllByActive(@RequestParam("status") Boolean status) {
        return ResponseEntity.ok().body(animalTemperamentService.findAllByActive(status));
    }
}