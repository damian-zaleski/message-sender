package pl.degath.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.degath.message.exception.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MessageTest {

    @Test
    void cannotCreateMessageWithMissingEmail() {
        Throwable thrown = catchThrowable(() ->
                new Message(null, "Valid title", "This is test content", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message email is required. Cannot be blank.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            "})
    void cannotCreateMessageWithBlankEmail(String input) {
        Throwable thrown = catchThrowable(() ->
                new Message(input, "Valid title", "This is test content", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message email is required. Cannot be blank.");
    }

    @Test
    void cannotCreateMessageWithMissingTitle() {
        Throwable thrown = catchThrowable(() ->
                new Message("valid@email.pl", null, "This is test content", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message title is required. Cannot be blank.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            "})
    void cannotCreateMessageWithBlankTitle(String input) {
        Throwable thrown = catchThrowable(() ->
                new Message("valid@email.pl", input, "This is test content", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message title is required. Cannot be blank.");
    }

    @Test
    void cannotCreateMessageWithMissingContent() {
        Throwable thrown = catchThrowable(() ->
                new Message("valid@email.pl", "This is a title.", null, 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message content is required. Cannot be blank.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            "})
    void cannotCreateMessageWithBlankContent(String input) {
        Throwable thrown = catchThrowable(() ->
                new Message("valid@email.pl", "This is a title", input, 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message content is required. Cannot be blank.");
    }
}