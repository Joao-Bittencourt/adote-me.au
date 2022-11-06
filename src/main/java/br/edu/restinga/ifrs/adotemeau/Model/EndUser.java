package br.edu.restinga.ifrs.adotemeau.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EndUser{

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo nome não pode estar em branco")
    @Length(max = 50)
    private String name;

    @Email(regexp = "[\\w-]+@([\\w-]+\\.)+[\\w-]+")
    private String email;

    private String password;

}
