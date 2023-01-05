package br.edu.restinga.ifrs.adotemeau.http.controllers;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalDTO;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalRepository;
import br.edu.restinga.ifrs.adotemeau.services.AnimalService;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;
    @Autowired
    AnimalRepository animalRepository;

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

    @PatchMapping("/profile-image/{animalId}")
    public ResponseEntity setProfileImage(@PathVariable("animalId") Long animalId, MultipartFile image)
            throws IOException {
        return new ResponseEntity(animalService.setProfileImage(animalId, image.getBytes()), HttpStatus.OK);
    }

    @PostMapping("/album/{animalId}")
    public ResponseEntity setAlbumImage(@PathVariable("animalId") Long animalId, List<MultipartFile> images) throws Exception {
        return new ResponseEntity(this.animalService.setAlbumImage(animalId, images), HttpStatus.OK);
    }

    @GetMapping("/album/{animalId}")
    public ResponseEntity getAlbum(@PathVariable("animalId") Long animalId) {
        return new ResponseEntity(this.animalService.getAlbum(animalId), HttpStatus.OK);
    }
}
