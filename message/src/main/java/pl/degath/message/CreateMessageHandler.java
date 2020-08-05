package pl.degath.message;

import pl.degath.message.command.CreateMessage;
import pl.degath.message.domain.Message;
import pl.degath.message.infrastructure.CommandHandler;
import pl.degath.message.port.MessageRepository;

import java.util.Objects;

public class CreateMessageHandler implements CommandHandler<CreateMessage> {

    private final MessageRepository repository;

    public CreateMessageHandler(MessageRepository repository) {
        this.repository = Objects.requireNonNull(repository, "Repository has to be specified.");
    }

    @Override
    public void handle(CreateMessage command) {
        Objects.requireNonNull(command, "Command has to be specified.");

        Message newMessage = createMessage(command);
        repository.insert(newMessage);
    }

    private Message createMessage(CreateMessage command) {
        return new Message(command.getTitle(), command.getContent(), command.getEmail(), command.getMagicNumber());
    }
}
