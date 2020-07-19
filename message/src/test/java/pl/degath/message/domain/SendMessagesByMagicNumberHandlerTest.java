package pl.degath.message.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.degath.message.command.SendMessagesByMagicNumber;
import pl.degath.message.port.MessageByEmailInMemory;
import pl.degath.message.port.MessageByMagicNumberInMemory;
import pl.degath.message.port.MessageInMemory;
import pl.degath.message.port.MessageRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.times;


class SendMessagesByMagicNumberHandlerTest {

    private SendMessagesByMagicNumberHandler sendMessagesByMagicNumberHandler;
    private MessageRepository repository;
    @Mock
    private JavaMailSender mailSender;
    private SendMessageHandler sendMessageHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        var byMagicNumberInMemory = new MessageByMagicNumberInMemory();
        repository = new MessageInMemory(new MessageByEmailInMemory(), byMagicNumberInMemory);
        sendMessageHandler = new SendMessageHandler(repository, mailSender);
        sendMessagesByMagicNumberHandler = new SendMessagesByMagicNumberHandler(byMagicNumberInMemory, sendMessageHandler);
    }

    @Test
    void canSendMultipleMessages() {
        new MessageBuilder(repository)
                .withMagicNumber(60)
                .inDbMultiplied(10);

        sendMessagesByMagicNumberHandler.handle(new SendMessagesByMagicNumber(60));

        Mockito.verify(mailSender, times(10))
                .send(Mockito.any(SimpleMailMessage.class));
    }


    @Test
    void messagesAreRemovedAfterSend() {
        new MessageBuilder(repository)
                .withMagicNumber(60)
                .inDbMultiplied(10);

        sendMessagesByMagicNumberHandler.handle(new SendMessagesByMagicNumber(60));

        assertThat(repository.count()).isEqualTo(0);
    }

    @Test
    void cannotSendMessagesWithMissingCommand() {
        SendMessagesByMagicNumber input = null;

        Throwable thrown = catchThrowable(() -> sendMessagesByMagicNumberHandler.handle(input));

        assertThat(thrown)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Command has to be specified.");
    }

    @Test
    void doesNothingWhenNoMessagesWithGivenMagicNumber() {
        sendMessagesByMagicNumberHandler.handle(new SendMessagesByMagicNumber(15789));

        Mockito.verify(mailSender, times(0))
                .send(Mockito.any(SimpleMailMessage.class));
    }
}