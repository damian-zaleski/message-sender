package pl.degath.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.degath.message.command.CreateMessage;
import pl.degath.message.port.MessageRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class CreateMessageHandlerTest {

    private MessageRepository repository;
    private CreateMessageHandler handler;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMessageRepository();
        handler = new CreateMessageHandler(repository);
    }

    @Test
    void canCreateMessageWithCorrectCommand() {
        handler.handle(MessageFixtures.createMessage());

        Message output = getFirstMessage();

        assertThat(output).isNotNull();
        assertThat(output.getEmail()).isEqualTo("valid@wp.pl");
        assertThat(output.getTitle()).isEqualTo("Test title");
        assertThat(output.getContent()).isEqualTo("This is example test content.");
        assertThat(output.getMagicNumber()).isEqualTo(1337);
    }

    private Message getFirstMessage() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    @Test
    void cannotCreateMessageWithInvalidCommand() {
        CreateMessage input = null;

        Throwable thrown = catchThrowable(() -> handler.handle(input));

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Command has to be specified.");
    }
}