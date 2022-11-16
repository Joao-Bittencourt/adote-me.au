package br.edu.restinga.ifrs.adotemeau.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidFields {
    private String fieldName;
    private String errorMessage;
}
