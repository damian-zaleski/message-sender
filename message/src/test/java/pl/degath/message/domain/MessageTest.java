package pl.degath.message.domain;

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
                new Message("Valid title", "This is test content", null, 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message email is required. Has to be a valid email address.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            ", "@wp.pl", "test@", "test.pl"})
    void cannotCreateMessageWithInvalidEmail(String input) {
        Throwable thrown = catchThrowable(() ->
                new Message("Valid title", "This is test content", input, 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message email is required. Has to be a valid email address.");
    }

    @Test
    void cannotCreateMessageWithMissingTitle() {
        Throwable thrown = catchThrowable(() ->
                new Message(null, "This is test content", "valid@email.pl", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message title is required. Cannot be blank.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            "})
    void cannotCreateMessageWithBlankTitle(String input) {
        Throwable thrown = catchThrowable(() ->
                new Message(input, "This is test content", "valid@email.pl", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message title is required. Cannot be blank.");
    }

    @Test
    void cannotCreateMessageWithMissingContent() {
        Throwable thrown = catchThrowable(() ->
                new Message("This is a title.", null, "valid@email.pl", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message content is required. Cannot be blank.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "            "})
    void cannotCreateMessageWithBlankContent(String input) {
        Throwable thrown = catchThrowable(() ->
                new Message("This is a title", input, "valid@email.pl", 1337));

        assertThat(thrown)
                .isInstanceOf(ValidationException.class)
                .hasMessage("Message content is required. Cannot be blank.");
    }
}