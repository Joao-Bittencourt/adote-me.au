package br.edu.restinga.ifrs.adotemeau.services;

import br.edu.restinga.ifrs.adotemeau.exceptions.InvalidField;
import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalFamilyDTO;
import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalFamilyRepository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class AnimalFamilyService {

    final AnimalFamilyRepository animalFamilyRepository;

    public AnimalFamilyService(AnimalFamilyRepository animalFamilyRepository) {
        this.animalFamilyRepository = animalFamilyRepository;
    }

    @Transactional
    public AnimalFamilyDTO create(AnimalFamily animalFamily) {
        animalFamilyRepository.save(animalFamily);
        AnimalFamilyDTO animalFamilyDTO = new AnimalFamilyDTO(animalFamily);
        return animalFamilyDTO;
    }

    public List<AnimalFamilyDTO> findAll() {
        List<AnimalFamily> listAnimalFamily = animalFamilyRepository.findAll();
        return AnimalFamilyDTO.convertList(listAnimalFamily);
    }

    @Transactional
    public AnimalFamilyDTO update(AnimalFamily updateAnimalFamily, Long id) {
        Optional<AnimalFamily> optional = this.animalFamilyRepository.findById(id);

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe familia com este id!");
        }

        AnimalFamily animalFamily = optional.get();

        if (updateAnimalFamily.getType() != null) {
            animalFamily.setType(updateAnimalFamily.getType());
        }

        if (updateAnimalFamily.getActive() != null) {
            animalFamily.setActive(updateAnimalFamily.getActive());
        }

        AnimalFamilyDTO animalFamilyDTO = new AnimalFamilyDTO(this.animalFamilyRepository.save(animalFamily));

        return animalFamilyDTO;
    }

    @Transactional
    public AnimalFamilyDTO changeStatus(AnimalFamily updateAnimalFamily) {
        Optional<AnimalFamily> optional = this.animalFamilyRepository.findById(updateAnimalFamily.getId());

        if (!optional.isPresent()) {
            throw new InvalidField("id", "Não existe familia com este id!");
        }

        AnimalFamily animalFamily = optional.get();

        if (updateAnimalFamily.getActive() != null) {
            animalFamily.setActive(updateAnimalFamily.getActive());
        }

        AnimalFamilyDTO animalFamilyDTO = new AnimalFamilyDTO(this.animalFamilyRepository.save(animalFamily));

        return animalFamilyDTO;
    }

    public AnimalFamilyDTO findById(long id) {

        var animalFamily = animalFamilyRepository.findById(id);

        if (!animalFamily.isPresent()) {
            throw new InvalidField("id", "Não existe familia com este id!");
        }

        AnimalFamilyDTO animalFamilyDTO = new AnimalFamilyDTO(animalFamily.get());
        return animalFamilyDTO;
    }

    public List<AnimalFamilyDTO> findAllByActive(Boolean status) {
        List<AnimalFamily> listAnimalFamily = animalFamilyRepository.findAllByActive(status);
        return AnimalFamilyDTO.convertList(listAnimalFamily);
    }
}
