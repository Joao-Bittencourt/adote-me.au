package br.edu.restinga.ifrs.adotemeau.http.controllers.users;

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

    @GetMapping("/find")
    public ResponseEntity<List<AnimalFamilyDTO>> findAllByActive(@RequestParam("status") Boolean status) {
        return ResponseEntity.ok().body(animalFamilyService.findAllByActive(status));
    }
}
