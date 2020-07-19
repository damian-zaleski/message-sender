package pl.degath.message.command;

import pl.degath.message.infrastructure.Command;

import java.util.UUID;

public class SendMessage implements Command {
    private final UUID messageId;

    public SendMessage(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getMessageId() {
        return messageId;
    }
}
