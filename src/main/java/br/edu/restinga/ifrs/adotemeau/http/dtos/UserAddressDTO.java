package br.edu.restinga.ifrs.adotemeau.http.dtos;

import javax.validation.constraints.NotBlank;

import br.edu.restinga.ifrs.adotemeau.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDTO {

    private Long id;

//    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private User user;
    
    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String state;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String city;
    
    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String district;

}
