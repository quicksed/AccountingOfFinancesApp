package com.quicksed.accounting_of_finances_app.helper;

import javassist.NotFoundException;

import java.util.Optional;

public class OptionalChecker {

    public static <T> T checkOptional(Optional<T> optional) throws NotFoundException {
        if (optional.isEmpty()) {
            throw new NotFoundException("Object not found!");
        } else {
            return optional.get();
        }
    }
}
