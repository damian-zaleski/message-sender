package pl.degath.message;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.degath.message.command.SendMessage;
import pl.degath.message.domain.Message;
import pl.degath.message.infrastructure.CommandHandler;
import pl.degath.message.port.MessageRepository;

import java.util.Objects;

public class SendMessageHandler implements CommandHandler<SendMessage> {

    private final MessageRepository repository;
    private final JavaMailSender mailSender;

    public SendMessageHandler(MessageRepository repository, JavaMailSender mailSender) {
        this.repository = Objects.requireNonNull(repository, "Repository has to be specified.");
        this.mailSender = Objects.requireNonNull(mailSender, "MailSender has to be specified.");
    }

    @Override
    public void handle(SendMessage command) {
        Objects.requireNonNull(command, "Command has to be specified.");

        Message message = repository.findByMessageId(command.getMessageId());
        mailSender.send(from(message));
        repository.delete(message);
    }

    private SimpleMailMessage from(Message message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getEmail());
        simpleMailMessage.setSubject(message.getTitle());
        simpleMailMessage.setText(message.getContent());
        return simpleMailMessage;
    }
}
