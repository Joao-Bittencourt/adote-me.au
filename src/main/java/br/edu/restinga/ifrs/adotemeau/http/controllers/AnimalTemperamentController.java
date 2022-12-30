package br.edu.restinga.ifrs.adotemeau.http.controllers;

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

    @PostMapping
    public ResponseEntity<AnimalTemperamentDTO> create(@Valid @RequestBody AnimalTemperamentDTO animalTemperamentDTO) {
        AnimalTemperament atemp = new AnimalTemperament();
        BeanUtils.copyProperties(animalTemperamentDTO, atemp);
        return ResponseEntity.status(HttpStatus.CREATED).body(animalTemperamentService.create(atemp));
    }

   @GetMapping
    public ResponseEntity<List<AnimalTemperament>> findAll() {
        return ResponseEntity.ok().body(animalTemperamentService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalTemperamentDTO> update(@Valid @RequestBody AnimalTemperament aTemp, @PathVariable Long id) {
        return ResponseEntity.ok().body(animalTemperamentService.update(aTemp, id));
    }

    @PatchMapping("/active")
    public ResponseEntity<AnimalTemperamentDTO> changeStatus(@Valid @RequestBody AnimalTemperament animalTemperament) {
        return ResponseEntity.ok().body(animalTemperamentService.changeStatus(animalTemperament));
    }

    @GetMapping("/find")
    public ResponseEntity<List<AnimalTemperament>> findAllByActive(@RequestParam("status") Boolean status) {
        return ResponseEntity.ok().body(animalTemperamentService.findAllByActive(status));
    }
}