package br.edu.restinga.ifrs.adotemeau.http.dtos;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;


import br.edu.restinga.ifrs.adotemeau.models.AnimalTemperament;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalTemperamentDTO {
    private Long id;

    @NotNull(message="O campo NÂO pode se nulo, vazio ou em branco!")
    private String type;

    //@Value("${validate.key:true}") //Inicializa o atributo com o valor TRUE
    private boolean active;

    //Construtor
    public AnimalTemperamentDTO(AnimalTemperament aTemperament){
        this.id = aTemperament.getId();
        this.type = aTemperament.getType();
        this.active = aTemperament.isActive();
    }

    //Converte Lista AnimalTemperament em AnimaltemperamentDTO
    public static List<AnimalTemperamentDTO> convertList (List<AnimalTemperament> animalTemperament){
        return animalTemperament.stream().map(AnimalTemperamentDTO::new).collect(Collectors.toList());
    }
}
