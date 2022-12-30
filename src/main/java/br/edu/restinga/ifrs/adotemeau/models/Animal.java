package br.edu.restinga.ifrs.adotemeau.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.restinga.ifrs.adotemeau.models.enums.AnimalSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    private String description;

    private int age;

    private String physicalCharacteristics;

    private boolean specialNeeds;

    private boolean adopted;
   
    private AnimalSexEnum sex;

    @ManyToMany
    @JoinColumn(name = "temperament_id")
    private List<AnimalTemperament> temperament;

    @OneToOne
    private AnimalFamily family;

    @OneToOne
    private AnimalBreed breed;

    @OneToOne
    private User user;

    public User getUser() {
        this.user.setPassword("*****");
        return this.user;
    }
}
