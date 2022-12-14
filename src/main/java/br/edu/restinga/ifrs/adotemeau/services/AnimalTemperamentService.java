package br.edu.restinga.ifrs.adotemeau.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.http.dtos.AnimalTemperamentDTO;
import br.edu.restinga.ifrs.adotemeau.models.AnimalTemperament;
import br.edu.restinga.ifrs.adotemeau.repositories.AnimalTemperamentRepository;

@Service
public class AnimalTemperamentService {

    @Autowired
    private AnimalTemperamentRepository aTempRepository;
    
    @Transactional
    public AnimalTemperamentDTO create(AnimalTemperament aTemp){
        aTemp.setActive(true);
        aTempRepository.save(aTemp);
        AnimalTemperamentDTO animalTemperamentDTO = new AnimalTemperamentDTO(aTemp);
        return animalTemperamentDTO;
    }

    public List<AnimalTemperament> findAll(){
        return aTempRepository.findAll();
    }

    public AnimalTemperamentDTO update(AnimalTemperament updateAn, Long id){
        AnimalTemperament aTemp = aTempRepository.findById(id).get();
        aTemp.setId(id);
        aTemp.setType(updateAn.getType());
        aTemp.setActive(updateAn.isActive());
        AnimalTemperamentDTO animalTemperamentDTO = new AnimalTemperamentDTO(aTempRepository.save(aTemp));
        return animalTemperamentDTO;
    }
}