package br.edu.restinga.ifrs.adotemeau.http.dtos;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.edu.restinga.ifrs.adotemeau.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "O campo não pode ser nulo, vazio ou em branco")
    private String name;

    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    @Email(regexp = "[\\w-]+@([\\w-]+\\.)+[\\w-]+")
    private String email;

    @NotNull(message = "O campo não pode ser nulo, vazio ou em branco")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$", message = "A senha deve ter pelo menos 4 caracteres, não mais que 8 caracteres e deve incluir pelo menos uma letra maiúscula, uma letra minúscula e um dígito numérico.")
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public static List<UserDTO> convertList(List<User> users) {
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
