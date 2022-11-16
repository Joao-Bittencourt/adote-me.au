package br.edu.restinga.ifrs.adotemeau.exceptions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorValidationHandler {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<InvalidFields> handler(MethodArgumentNotValidException exception) {

        List<InvalidFields> errors =  new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            InvalidFields error = new  InvalidFields(e.getField(), message);
            errors.add(error);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidField.class)
    public LinkedHashMap<String, String> handleInvalidField(InvalidField invalidField)  {
        var error = new InvalidField(invalidField.getFieldName(), invalidField.getErrorMessage());
        var test = new LinkedHashMap<String, String>();

        test.put("field", error.getFieldName());
        test.put("message", error.getErrorMessage());

        return test;
    }
}
