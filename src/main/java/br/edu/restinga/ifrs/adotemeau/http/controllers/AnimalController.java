package br.edu.restinga.ifrs.adotemeau.http.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalDTO;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.services.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    
    @Autowired
    AnimalService animalService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid AnimalDTO animalDTO) {
        return new ResponseEntity(animalService.create(animalDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return new ResponseEntity(animalService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(animalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/by-temperament/{id}")
    public ResponseEntity findByTemperament(@PathVariable("id") Long id) {
        return new ResponseEntity(animalService.findByTemperament(id), HttpStatus.OK);
    }

    @GetMapping("/by-family/{id}")
    public ResponseEntity findByFamily(@PathVariable("id") Long id) {
        return new ResponseEntity(animalService.findByFamily(id), HttpStatus.OK);
    }

    @GetMapping("/by-breed/{id}")
    public ResponseEntity findByBreed(@PathVariable("id") Long id) {
        return new ResponseEntity(animalService.findByBreed(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody @Valid AnimalDTO animalDTO) {
        return new ResponseEntity(animalService.update(id, animalDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return new ResponseEntity(animalService.delete(id), HttpStatus.OK);
    }

    @PatchMapping("/adopted/{id}/{adopted}")
    public ResponseEntity isAdopted(@PathVariable("id") Long id, @PathVariable("adopted") Boolean adopted) {
        return new ResponseEntity(animalService.isAdopted(id, adopted), HttpStatus.OK);
    }

    @GetMapping("/all-by-user/")
    public ResponseEntity findAnimalByUser(@RequestParam("user") User user) {
        return new ResponseEntity(animalService.findAnimalByUser(user), HttpStatus.OK);
    }
}
