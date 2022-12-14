package br.edu.restinga.ifrs.adotemeau.http.dtos;

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

    private boolean active;

    public AnimalTemperamentDTO(AnimalTemperament aTemperament){
        this.id = aTemperament.getId();
        this.type = aTemperament.getType();
        this.active = aTemperament.isActive();
    }
}
