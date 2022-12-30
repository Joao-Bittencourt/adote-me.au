package br.edu.restinga.ifrs.adotemeau.services;

import br.edu.restinga.ifrs.adotemeau.exceptions.InvalidField;
import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalBreedDTO;
import br.edu.restinga.ifrs.adotemeau.models.AnimalBreed;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalBreedRepository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class AnimalBreedService {

    final AnimalBreedRepository animalBreedRepository;

    public AnimalBreedService(AnimalBreedRepository animalBreedRepository) {
        this.animalBreedRepository = animalBreedRepository;
    }

    @Transactional
    public AnimalBreedDTO create(AnimalBreed animalBreed) {
        animalBreed.setActive(true);
        animalBreedRepository.save(animalBreed);
        AnimalBreedDTO animalBreedDTO = new AnimalBreedDTO(animalBreed);
        return animalBreedDTO;
    }

    public List<AnimalBreedDTO> findAll() {
        List<AnimalBreed> listAnimalBreed = animalBreedRepository.findAll();
        return AnimalBreedDTO.convertList(listAnimalBreed);
    }

    @Transactional
    public AnimalBreedDTO update(AnimalBreed updateAnimalBreed, Long id) {
        Optional<AnimalBreed> optional = this.animalBreedRepository.findById(id);
        System.out.println(optional);
        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe raça com este id!");
        }

        AnimalBreed animalBreed = optional.get();

        if (updateAnimalBreed.getType() != null) {
            animalBreed.setType(updateAnimalBreed.getType());
        }

        if (updateAnimalBreed.getActive() != null) {
            animalBreed.setActive(updateAnimalBreed.getActive());
        }
        
        if (updateAnimalBreed.getAnimalFamily() != null) {
            animalBreed.setAnimalFamily(updateAnimalBreed.getAnimalFamily());
        }

        AnimalBreedDTO animalBreedDTO = new AnimalBreedDTO(this.animalBreedRepository.save(animalBreed));

        return animalBreedDTO;
    }

    @Transactional
    public AnimalBreedDTO changeStatus(AnimalBreed updateAnimalBreed) {
        Optional<AnimalBreed> optional = this.animalBreedRepository.findById(updateAnimalBreed.getId());

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe raça com este id!");
        }

        AnimalBreed animalBreed = optional.get();

        if (updateAnimalBreed.getActive() != null) {
            animalBreed.setActive(updateAnimalBreed.getActive());
        }

        AnimalBreedDTO animalBreedDTO = new AnimalBreedDTO(this.animalBreedRepository.save(animalBreed));

        return animalBreedDTO;
    }

    public AnimalBreedDTO findById(long id) {

        var animalBreed = animalBreedRepository.findById(id);

        if (!animalBreed.isPresent()) {
            throw new InvalidField("id", "Não existe raça com este id!");
        }

        AnimalBreedDTO animalBreedDTO = new AnimalBreedDTO(animalBreed.get());
        return animalBreedDTO;
    }
    
    public List<AnimalBreedDTO> findByAnimalFamilyTypeContaining(String animalFamilyType) {
         List<AnimalBreed> listAnimalBreed = animalBreedRepository.findByAnimalFamilyTypeContaining(animalFamilyType);
        return AnimalBreedDTO.convertList(listAnimalBreed);
    }

    public List<AnimalBreedDTO> findAllByActive(Boolean status) {
        List<AnimalBreed> listAnimalFamily = animalBreedRepository.findAllByActive(status);
        return AnimalBreedDTO.convertList(listAnimalFamily);
    }

}
