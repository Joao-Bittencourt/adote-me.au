package br.edu.restinga.ifrs.adotemeau.http.controllers.admin;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalBreedDTO;
import br.edu.restinga.ifrs.adotemeau.services.AnimalBreedService;
import br.edu.restinga.ifrs.adotemeau.models.AnimalBreed;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/animal/breed")
public class AdminAnimalBreedController {

    @Autowired
    AnimalBreedService animalBreedService;

    @GetMapping
    public ResponseEntity<List<AnimalBreedDTO>> findAll() {
        return ResponseEntity.ok().body(animalBreedService.findAll());
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<AnimalBreedDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(animalBreedService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AnimalBreedDTO> create(@Valid @RequestBody AnimalBreedDTO animalFamilyDto) {

        var animalFamily = new AnimalBreed();
        BeanUtils.copyProperties(animalFamilyDto, animalFamily);
        animalFamily.setActive(true);

        return ResponseEntity.status(HttpStatus.CREATED).body(animalBreedService.create(animalFamily));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalBreedDTO> update(@Valid @RequestBody AnimalBreed animalFamily, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(animalBreedService.update(animalFamily, id));
    }

    @PatchMapping("/active")
    public ResponseEntity<AnimalBreedDTO> changeStatus(@Valid @RequestBody AnimalBreed animalFamily) {
        return ResponseEntity.ok().body(animalBreedService.changeStatus(animalFamily));
    }
}
