package br.edu.restinga.ifrs.adotemeau.http.controllers.users;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalBreedDTO;
import br.edu.restinga.ifrs.adotemeau.services.AnimalBreedService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animal/breed")
public class AnimalBreedController {

    @Autowired
    AnimalBreedService animalBreedService;

    @GetMapping("/{animalFamily}")
    public ResponseEntity<List<AnimalBreedDTO>> findByAnimalFamilyTypeContaining(@PathVariable String animalFamily) {
        return ResponseEntity.ok().body(animalBreedService.findByAnimalFamilyTypeContaining(animalFamily));
    }

    @GetMapping("/find")
    public ResponseEntity<List<AnimalBreedDTO>> findAllByActive(@RequestParam("status") Boolean status) {
        return ResponseEntity.ok().body(animalBreedService.findAllByActive(status));
    }
}
