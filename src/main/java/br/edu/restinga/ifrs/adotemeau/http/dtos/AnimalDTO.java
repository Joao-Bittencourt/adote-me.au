package br.edu.restinga.ifrs.adotemeau.http.dtos;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.edu.restinga.ifrs.adotemeau.http.controllers.AnimalBreedController;
import br.edu.restinga.ifrs.adotemeau.http.controllers.AnimalFamilyController;
import br.edu.restinga.ifrs.adotemeau.http.controllers.AnimalTemperamentController;
import br.edu.restinga.ifrs.adotemeau.models.Animal;
import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;
import br.edu.restinga.ifrs.adotemeau.models.enums.AnimalSexEnum;

public class AnimalDTO {
    
    private Long id;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String name;

    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private String description;

    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private int age;

    private String physicalCharacteristics;
    
    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private boolean specialNeeds;
    
    private boolean adopted;
    
    @Enumerated
    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private AnimalSexEnum sex;

    @OneToMany
    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private AnimalTemperamentController animalTemperament;

    @OneToOne
    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private AnimalFamilyController animalFamily;

    @OneToOne
    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    private AnimalBreedController animalBreed;


    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.name = animal.getName();
        this.description = animal.getDescription();
        this.physicalCharacteristics = animal.getPhysicalCharacteristics();
        this.specialNeeds = animal.getSpecialNeeds();
        this.adopted = animal.getAdopted();
        this.sex = animal.getSex();
        this.animalTemperament = animal.AnimalTemperamentController();
        this.animalFamily = animal.getAnimalFamily();
        this.animalBreed = animal.getAnimalBreed();
    }

    public static List<AnimalDTO> convertList(List<Animal> animals) {
        return animals.stream().map(AnimalDTO::new).collect(Collectors.toList());
    }
}
