package br.edu.restinga.ifrs.adotemeau.http.controllers;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalFamilyDTO;
import br.edu.restinga.ifrs.adotemeau.services.AnimalFamilyService;
import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

@RestController
@RequestMapping("/animal/family")
public class AnimalFamilyController {

    final AnimalFamilyService animalFamilyService;

    public AnimalFamilyController(AnimalFamilyService animalFamilyService) {
        this.animalFamilyService = animalFamilyService;
    }

    @GetMapping
    public ResponseEntity<List<AnimalFamilyDTO>> findAll() {
        return ResponseEntity.ok().body(animalFamilyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalFamilyDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(animalFamilyService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AnimalFamilyDTO> create(@Valid @RequestBody AnimalFamilyDTO animalFamilyDto) {

        var animalFamily = new AnimalFamily();
        BeanUtils.copyProperties(animalFamilyDto, animalFamily);
        animalFamily.setActive(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(animalFamilyService.create(animalFamily));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalFamilyDTO> update(@Valid @RequestBody AnimalFamily animalFamily, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(animalFamilyService.update(animalFamily, id));
    }

    @PatchMapping("/active")
    public ResponseEntity<AnimalFamilyDTO> changeStatus(@Valid @RequestBody AnimalFamily animalFamily) {
        return ResponseEntity.ok().body(animalFamilyService.changeStatus(animalFamily));
    }

    @GetMapping("/find")
    public ResponseEntity<List<AnimalFamilyDTO>> findAllByActive(@RequestParam("status") Boolean status) {
        return ResponseEntity.ok().body(animalFamilyService.findAllByActive(status));
    }
}
