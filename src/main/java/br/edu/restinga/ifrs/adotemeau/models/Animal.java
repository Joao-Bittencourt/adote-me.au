package br.edu.restinga.ifrs.adotemeau.models;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.edu.restinga.ifrs.adotemeau.models.enums.AnimalSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Animal {
    
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int age;
    private String physicalCharacteristics;
    private boolean specialNeeds;
    private boolean adopted;
   
    @Enumerated
    private AnimalSexEnum sex;

    @OneToMany
    private AnimalTemperament animalTemperament;

    @OneToOne
    private AnimalFamily animalFamily;

    @OneToOne
    private AnimalBreed animalBreed;

}