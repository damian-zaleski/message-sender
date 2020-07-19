package pl.degath.message.infrastructure;

import pl.degath.message.exception.ValidationException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validator {

    public static String notBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(message);
        }
        return value;
    }

    public static String validEmail(String value, String message) {
        if (value == null || value.isBlank() || !isValidEmailAddress(value)) {
            throw new ValidationException(message);
        }
        return value;
    }

    private static boolean isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            return false;
        }
        return true;
    }
}
