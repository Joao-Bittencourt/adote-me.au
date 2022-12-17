package br.edu.restinga.ifrs.adotemeau.http.dtos;

import br.edu.restinga.ifrs.adotemeau.models.AnimalBreed;
import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalBreedDTO {

    private Long id;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String type;

    private boolean active;
    
    @NotNull
    private AnimalFamily animalFamily;

    public AnimalBreedDTO(AnimalBreed animalBreed) {
        this.id = animalBreed.getId();
        this.type = animalBreed.getType();
        this.active = animalBreed.getActive();
        this.animalFamily = animalBreed.getAnimalFamily();
    }

    public static List<AnimalBreedDTO> convertList(List<AnimalBreed> animalBreed) {
        return animalBreed.stream().map(AnimalBreedDTO::new).collect(Collectors.toList());
    }
}
