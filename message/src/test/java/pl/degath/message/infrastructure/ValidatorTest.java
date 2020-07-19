package pl.degath.message.infrastructure;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.degath.message.exception.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ValidatorTest {

    private static final String errorMessage = "Error message.";

    @ParameterizedTest
    @ValueSource(strings = {" ", "            ", "@wp.pl", "test@", "test.pl", "."})
    void notValidEmailShouldThrowException(String input) {
        Throwable thrown = catchThrowable(() -> Validator.validEmail(input, errorMessage));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage(errorMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            "})
    void notBlankPropertyShouldThrowException(String input) {
        Throwable thrown = catchThrowable(() -> Validator.notBlank(input, errorMessage));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage(errorMessage);
    }
}