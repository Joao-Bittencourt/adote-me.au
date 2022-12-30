package br.edu.restinga.ifrs.adotemeau.http.dtos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.restinga.ifrs.adotemeau.models.Animal;
import br.edu.restinga.ifrs.adotemeau.models.AnimalBreed;
import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;
import br.edu.restinga.ifrs.adotemeau.models.AnimalTemperament;
import br.edu.restinga.ifrs.adotemeau.models.User;
import br.edu.restinga.ifrs.adotemeau.models.enums.AnimalSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;

    @NotBlank(message = "O campo name não pode ser nulo, vazio ou em branco")
    private String name;

    @NotBlank(message = "O campo description não pode ser nulo, vazio ou em branco")
    private String description;

    @NotNull(message = "O campo age não pode ser nulo, vazio ou em branco")
    private int age;

    @NotBlank(message = "O campo physicalCharacteristics não pode ser nulo, vazio ou em branco")
    private String physicalCharacteristics;
    
    @NotNull(message = "O campo physicalCharacteristics não pode ser nulo, vazio ou em branco")
    private boolean specialNeeds;
    
    @Enumerated
    @NotNull(message = "O campo sex não pode ser nulo, vazio ou em branco!")
    private AnimalSexEnum sex;

    @NotNull(message = "O campo animalTemperament não pode ser nulo, vazio ou em branco!")
    private List<AnimalTemperament> temperaments;

    @NotNull(message = "O campo animalFamily não pode ser nulo, vazio ou em branco!")
    private AnimalFamily family;

    @NotNull(message = "O campo animalBreed não pode ser nulo, vazio ou em branco!")
    private AnimalBreed breed;

    @NotNull(message = "O campo animalBreed não pode ser nulo, vazio ou em branco!")
    private User user;

    // public AnimalDTO(Animal animal) {
    //     this.id = animal.getId();
    //     this.name = animal.getName();
    //     this.description = animal.getDescription();
    //     this.age = animal.getAge();
    //     this.physicalCharacteristics = animal.getPhysicalCharacteristics();
    //     this.specialNeeds = animal.isSpecialNeeds();
    //     this.sex = animal.getSex();
    //     this.temperaments = animal.getTemperament();
    //     this.family = animal.getFamily();
    //     this.breed = animal.getBreed();
    //     this.user = animal.getUser();
    // }

    // public static List<AnimalDTO> convertList(List<Animal> animals) {
    //     return animals.stream().map(AnimalDTO::new).collect(Collectors.toList());
    // }
}


