package pl.degath.message.command;

import pl.degath.message.infrastructure.Command;

import java.util.Objects;
import java.util.UUID;

public class SendMessage implements Command {
    private final UUID messageId;

    public SendMessage(UUID messageId) {
        this.messageId = Objects.requireNonNull(messageId, "MessageId has to be specified. Cannot be null");
    }

    public UUID getMessageId() {
        return messageId;
    }
}
