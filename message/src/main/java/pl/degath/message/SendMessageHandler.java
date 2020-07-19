package pl.degath.message;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.degath.message.command.SendMessage;
import pl.degath.message.exception.MessageNotFoundException;
import pl.degath.message.infrastructure.CommandHandler;
import pl.degath.message.port.MessageRepository;

import java.util.Objects;

public class SendMessageHandler implements CommandHandler<SendMessage> {

    private final MessageRepository messageRepository;
    private final JavaMailSender mailSender;

    public SendMessageHandler(MessageRepository messageRepository, JavaMailSender mailSender) {
        this.messageRepository = messageRepository;
        this.mailSender = mailSender;
    }

    @Override
    //todo this should be transactional
    public void handle(SendMessage command) {
        Objects.requireNonNull(command, "Command has to be specified.");

        var message = getMessage(command);
        mailSender.send(from(message));
        messageRepository.deleteById(message.getUuid());
    }

    private Message getMessage(SendMessage command) {
        return messageRepository.findById(command.getMessageId())
                .orElseThrow(MessageNotFoundException::new);
    }

    private SimpleMailMessage from(Message message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getEmail());
        simpleMailMessage.setSubject(message.getTitle());
        simpleMailMessage.setText(message.getContent());
        return simpleMailMessage;
    }
}
