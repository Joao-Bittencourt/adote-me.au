package br.edu.restinga.ifrs.adotemeau.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InvalidField extends RuntimeException {
    private String fieldName;
    private String errorMessage;
}
