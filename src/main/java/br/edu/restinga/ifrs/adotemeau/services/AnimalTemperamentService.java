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
    
    //Cadastra um novo temperamento → create
    @Transactional
    public AnimalTemperamentDTO create(AnimalTemperament aTemp){
        aTempRepository.save(aTemp);
        AnimalTemperamentDTO animalTemperamentDTO = new AnimalTemperamentDTO(aTemp);
        return animalTemperamentDTO;
    }

    //Lista todos os temperamentos → findAll
    public List<AnimalTemperamentDTO> findAll(){
        List<AnimalTemperament> aTemperaments = aTempRepository.findAll();
        return AnimalTemperamentDTO.convertList(aTemperaments);
    }

    //Atualiza um temperamento → update(int id)
    public AnimalTemperamentDTO update(AnimalTemperament updateAn, Long id){
        AnimalTemperament aTemp = aTempRepository.findById(id).get();
        aTemp.setId(id);
        aTemp.setType(updateAn.getType());
        aTemp.setActive(updateAn.isActive());
        AnimalTemperamentDTO animalTemperamentDTO = new AnimalTemperamentDTO(aTempRepository.save(aTemp));
        return animalTemperamentDTO;
    }

    //Desativar ou ativar um temperamento. Nesse caso, a service deve receber true ou false → changeStatus(String action)
    public String changeStatus(String action){
        boolean status = Boolean.getBoolean(action);
        if(status){
            status = false;
        }else{
            status = true;
        }
        return String.valueOf(status);
    }
}