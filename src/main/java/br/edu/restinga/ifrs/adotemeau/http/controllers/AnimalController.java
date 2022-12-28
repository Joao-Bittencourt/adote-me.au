package br.edu.restinga.ifrs.adotemeau.http.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalDTO;
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




















    
    //Cadastrar um novo Animal → POST/animal
    // @PostMapping()
    // public ResponseEntity<AnimalDTO> create(@Valid @RequestBody AnimalDTO animalDto) throws URISyntaxException {
    //     var animal = new Animal();
    //     BeanUtils.copyProperties(animalDto, animal);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(animalService.create(animal));
    // }

    // //Listar todos os animais → GET /animal
    // @GetMapping()
    // public ResponseEntity<List<AnimalDTO>> findAll(){
    //     return ResponseEntity.ok().body(animalService.findAll());
    // }

    // //Localizar um animal → GET /animal/temperament/{animalTemperament}
    // @GetMapping("/animal/temperament/{animalTemperament}")
    // public ResponseEntity<AnimalDTO> findByAnimalTemperament(String animalTemperament) {
    //     return ResponseEntity.ok().body(animalService.findByAnimalTemperament(animalTemperament));
    // }

    // //Localizar um animal → GET /animal/family/{animalFamily}
    // @GetMapping("/animal/family/{animalFamily}")
    // public ResponseEntity<AnimalDTO> findByAnimalFamily(String animalFamily) {
    //     return ResponseEntity.ok().body(animalService.findByAnimalFamily(animalFamily));
    // }

    // //Localizar um animal → GET /animal/breed/{animalBreed}
    // @GetMapping("/animal/breed/{animalBreed}")
    // public ResponseEntity<AnimalDTO> findByAnimalBreed(String animalBreed) {
    //     return ResponseEntity.ok().body(animalBreed.findByAnimalBreed(animalBreed));
    // }

    // //Atualizar um animal → UPDATE/animal/{id}
    // @PutMapping("/animal/{id}")
    // public ResponseEntity<AnimalDTO> update(@Valid @RequestBody Animal animal, @PathVariable("id") Long id) {
    //     return ResponseEntity.ok().body(animalService.update(animal, id));
    // }

    // //Deletar um animal → DELETE /animal/{id}
    // @DeleteMapping("/animal/{id}")
    // public ResponseEntity<String> delete(@PathVariable Long id) {
    //     return ResponseEntity.ok().body(animalService.delete(id));
    // }

    /*@PathVariable("/animal/adopted/{id}")
    public ResponseEntity<String> changeAdoptionStatus(boolean adopted) {
        return ResponseEntity.ok().body(animalService.adopted(adopted));
    }*/
}
