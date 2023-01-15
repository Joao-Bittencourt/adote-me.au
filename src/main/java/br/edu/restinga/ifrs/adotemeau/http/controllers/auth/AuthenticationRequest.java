package br.edu.restinga.ifrs.adotemeau.http.controllers.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    
    @NotNull(message = "O campo email não pode ser nulo, vazio ou em branco")
    @Email(regexp = "[\\w-]+@([\\w-]+\\.)+[\\w-]+")
    private String email;

    @NotNull(message = "O campo password não pode ser nulo, vazio ou em branco")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$", message = "A senha deve ter pelo menos 4 caracteres, não mais que 8 caracteres e deve incluir pelo menos uma letra maiúscula, uma letra minúscula e um dígito numérico.")
    private String password;
}
