package pl.degath.message;

import pl.degath.message.command.CreateMessage;
import pl.degath.message.infrastructure.CommandHandler;
import pl.degath.message.port.MessageRepository;

import java.util.Objects;

public class CreateMessageHandler implements CommandHandler<CreateMessage> {

    private final static int fiveMinutesInSeconds = 300;
    private final MessageRepository messageRepository;

    public CreateMessageHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void handle(CreateMessage command) {
        Objects.requireNonNull(command, "Command has to be specified.");

        Message newMessage = createMessage(command);
        messageRepository.insert(newMessage, fiveMinutesInSeconds);
    }

    private Message createMessage(CreateMessage command) {
        return new Message(command.getEmail(), command.getTitle(), command.getContent(), command.getMagicNumber());
    }
}
