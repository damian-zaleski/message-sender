package pl.degath.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.degath.message.command.SendMessage;
import pl.degath.message.port.MessageRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;

class SendMessageHandlerTest {

    private MessageRepository messageRepository;
    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    SendMessageHandler handler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        messageRepository = new InMemoryMessageRepository();
        handler = new SendMessageHandler(messageRepository, mailSender);
    }

    @Test
    void shouldSendMessageWithCorrectCommand() {
        var existingMessageUUID = existingMessage().getKey().getId();
        SendMessage command = new SendMessage(existingMessageUUID);

        handler.handle(command);

        Mockito.verify(mailSender, times(1)).send(Mockito.any(SimpleMailMessage.class));
    }

    @Test
    void shouldRemoveSentMessage() {
        var existingMessageUUID = existingMessage().getKey().getId();
        SendMessage command = new SendMessage(existingMessageUUID);

        handler.handle(command);

        assertThat(messageRepository.findAll()).hasSize(0);
    }

    private Message existingMessage() {
        return messageRepository.insert(MessageFixtures.message());
    }

    @Test
    void cannotSendMessageWithInvalidCommand() {
        SendMessage input = null;

        Throwable thrown = catchThrowable(() -> handler.handle(input));

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Command has to be specified.");
    }
}