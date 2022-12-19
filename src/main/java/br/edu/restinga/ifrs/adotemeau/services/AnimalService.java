package br.edu.restinga.ifrs.adotemeau.services;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.exceptions.InvalidField;
import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalDTO;
import br.edu.restinga.ifrs.adotemeau.models.Animal;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalRepository;


@Service
public class AnimalService {
    
    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public AnimalDTO create(Animal animal) {
        animalRepository.save(animal);
        AnimalDTO animalDto = new AnimalDTO(animal);
        return animalDto;
    }

    public List<AnimalDTO> findAll() {
        List<Animal> animals = (List<Animal>) animalRepository.findAll();
        return AnimalDTO.convertList(animals);
    }

    public AnimalDTO findByAnimalTemperament(String animalTemperament) {
        var animal = animalRepository.findByAnimalTemperament(animalTemperament);

        if (!animal.isPresent()) {
            throw new InvalidField("animalTemperament", "Não existe animal com este Temperamento!");
        } 

        AnimalDTO animalDto = new AnimalDTO(animal.get());
        return animalDto;
    }

    public AnimalDTO findByAnimalFamily(String animalFamily) {
        var animal = animalRepository.findByAnimalFamily(animalFamily);

        if (!animal.isPresent()) {
            throw new InvalidField("animalFamily", "Não existe animal com este Temperamento!");
        } 

        AnimalDTO animalDto = new AnimalDTO(animal.get());
        return animalDto;
    }

    public AnimalDTO findByAnimalBreed(String animalBreed) {
        var animal = animalRepository.findByAnimalBreed(animalBreed);

        if (!animal.isPresent()) {
            throw new InvalidField("animalBreed", "Não existe animal com este Temperamento!");
        } 

        AnimalDTO animalDto = new AnimalDTO(animal.get());
        return animalDto;
    }

    @Transactional
    public AnimalDTO update(Animal updateAnimal, Long id) {
        Optional<Animal> optional = this.animalRepository.findById(id);

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe animal com este id!");
        }

        Animal animal = optional.get();

        if(updateAnimal.getName() != null)
            animal.setName(updateAnimal.getName());

        if(updateAnimal.getDescription() != null)
            animal.setDescription(updateAnimal.getDescription());

        if(updateAnimal.getAge() != 0)
            animal.setAge(updateAnimal.getAge());

        if(updateAnimal.getPhysicalCharacteristics() != null)
            animal.setPhysicalCharacteristics(updateAnimal.getPhysicalCharacteristics());

        if(updateAnimal.getSpecialNeeds() != null)
            animal.setSpecialNeeds(updateAnimal.getSpecialNeeds());

        if(updateAnimal.getAdopted() != null)
            animal.setAdopted(updateAnimal.getAdopted());
        
        AnimalDTO animalDto = new AnimalDTO(this.animalRepository.save(animal));

        return animalDto;
    }

    @Transactional
    public String delete(Long id) {
        Optional<Animal> optional = this.animalRepository.findById(id);

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe animal com este id!");
        }

        Animal animal = optional.get();
        this.animalRepository.deleteById(id);
        return String.format("Animal %s foi deletado com sucesso", animal.getName());
    }

    @Transactional
    public void changeAdoptionStatus(boolean adopted, Long id) throws IOException{
        Scanner ler = new Scanner(System.in);
        char key;
        Optional<Animal> optional = this.animalRepository.findById(id);

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe animal com este id!");
        }
        System.out.printf("\nAnimal esta disponivel (S/N):\n");
        key = (char)System.in.read();
        
        if ((key == 'S') || (key == 's'))
        System.out.printf("Adotado? %s.\n");
            else 
            System.out.printf("Adotado? %s.\n");
    }

}
