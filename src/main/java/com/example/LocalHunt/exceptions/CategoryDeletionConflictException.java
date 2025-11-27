package com.example.LocalHunt.exceptions;

import com.example.LocalHunt.enums.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryDeletionConflictException extends RuntimeException {
    public CategoryDeletionConflictException() {
        super(ErrorMessages.CATEGORY_DELETION_CONFLICT.getMessage());
    }
}
