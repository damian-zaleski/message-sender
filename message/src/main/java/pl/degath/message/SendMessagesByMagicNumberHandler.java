package pl.degath.message;

import pl.degath.message.command.SendMessage;
import pl.degath.message.command.SendMessagesByMagicNumber;
import pl.degath.message.domain.MessageByMagicNumber;
import pl.degath.message.infrastructure.CommandHandler;
import pl.degath.message.port.MessageByMagicNumberRepository;

import java.util.Objects;

public class SendMessagesByMagicNumberHandler implements CommandHandler<SendMessagesByMagicNumber> {

    private final MessageByMagicNumberRepository repository;
    private final SendMessageHandler sendMessageHandler;

    public SendMessagesByMagicNumberHandler(MessageByMagicNumberRepository repository, SendMessageHandler sendMessageHandler) {
        this.repository = Objects.requireNonNull(repository, "Repository has to be specified.");
        this.sendMessageHandler = Objects.requireNonNull(sendMessageHandler, "Handler has to be specified.");
    }

    @Override
    public void handle(SendMessagesByMagicNumber command) {
        Objects.requireNonNull(command, "Command has to be specified.");

        repository.findByKeyMagicNumber(command.getMagicNumber())
                .stream()
                .map(this::getCommand)
                .forEach(sendMessageHandler::handle);
    }

    private SendMessage getCommand(MessageByMagicNumber message) {
        return new SendMessage(message.getKey().getMessageId());
    }
}
