package pl.degath.message.infrastructure;

import pl.degath.message.exception.ValidationException;

public class Validator {

    public static String notBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(message);
        }
        return value;
    }
}
