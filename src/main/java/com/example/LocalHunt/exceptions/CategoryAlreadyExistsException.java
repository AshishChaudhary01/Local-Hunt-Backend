package com.example.LocalHunt.exceptions;

import com.example.LocalHunt.enums.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super(ErrorMessages.CATEGORY_ALREADY_EXISTS.getMessage());
    }
}
