package br.edu.restinga.ifrs.adotemeau.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalDTO;
import br.edu.restinga.ifrs.adotemeau.models.Animal;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalRepository;

@Service
public class AnimalService {
    
    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public Animal create(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        BeanUtils.copyProperties(animalDTO, animal);
        animal.setTemperament(animalDTO.getTemperaments());

        return animalRepository.save(animal);
    }

    public Animal findById(Long id) {
        return this.animalRepository.findById(id).get();
    }

    public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }

    public List<Animal> findByTemperament(Long id) {
        return this.animalRepository.findByTemperament_Id(id);
    }

    public List<Animal> findByFamily(Long id) {
        return this.animalRepository.findByFamily_Id(id);
    }

    public List<Animal> findByBreed(Long id) {
        return this.animalRepository.findByBreed_Id(id);
    }

    public Animal update(Long id, @Valid AnimalDTO animalDTO) {

        Animal animal = new Animal();
        BeanUtils.copyProperties(animalDTO, animal);

        Animal animalEntity = animalRepository.findById(id).get();

        animal.setId(animalEntity.getId());
        
        return this.animalRepository.save(animal);
    }

    public String delete(Long id) {
        this.animalRepository.deleteById(id);
        return "Deleted!";
    }

    public Animal isAdopted(Long id, Boolean adopted) {
        Animal animal = animalRepository.findById(id).get();
        animal.setAdopted(adopted);
        return this.animalRepository.save(animal);
    }

    public List<Animal> findAnimalByUser(User user) {
        return this.animalRepository.findByUser(user);
    }

}
