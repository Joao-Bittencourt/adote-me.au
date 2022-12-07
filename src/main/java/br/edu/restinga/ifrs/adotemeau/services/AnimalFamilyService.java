package br.edu.restinga.ifrs.adotemeau.services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalFamilyRepository;

@Service
public class AnimalFamilyService {
    
    final AnimalFamilyRepository animalFamilyRepository;

    public AnimalFamilyService(AnimalFamilyRepository animalFamilyRepository) {
        this.animalFamilyRepository = animalFamilyRepository;
    }

    @Transactional
    public AnimalFamily create(AnimalFamily animalFamily) {
        return animalFamilyRepository.save(animalFamily);
    }

    public Page<AnimalFamily> findAll(Pageable pageable) {
        return animalFamilyRepository.findAll(pageable);
    }
}
