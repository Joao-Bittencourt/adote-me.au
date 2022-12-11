package br.edu.restinga.ifrs.adotemeau.http.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalFamilyDTO {

    private Long id;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String type;

//    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private boolean active;

    public AnimalFamilyDTO(AnimalFamily animalFamily) {
        this.id = animalFamily.getId();
        this.type = animalFamily.getType();
        this.active = animalFamily.getActive();
    }

    public static List<AnimalFamilyDTO> convertList(List<AnimalFamily> animalFamily) {
        return animalFamily.stream().map(AnimalFamilyDTO::new).collect(Collectors.toList());
    }
}
