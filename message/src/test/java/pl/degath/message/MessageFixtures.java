package pl.degath.message;

import pl.degath.message.command.CreateMessage;
import pl.degath.message.domain.*;

import java.util.UUID;

public class MessageFixtures {

    private static final String exampleTitle = "Test title";
    private static final String exampleContent = "This is example test content.";
    public static final String exampleEmail = "test@wp.pl";
    public static final int exampleMagicNumber = 1337;

    private MessageFixtures() {
        throw new AssertionError();
    }

    public static CreateMessage createMessage() {
        return new CreateMessage(
                exampleEmail, exampleTitle, exampleContent, exampleMagicNumber);
    }

    public static Message message() {
        return new Message(exampleTitle, exampleContent, exampleEmail, exampleMagicNumber);
    }

    public static MessageByEmail messageByEmail() {
        var emailKey = new MessageByEmailKey(exampleEmail, UUID.randomUUID(), exampleMagicNumber);
        return new MessageByEmail(emailKey, exampleTitle, exampleContent);
    }

    public static MessageByMagicNumber messageByMagicNumber() {
        var magicNumberKey = new MessageByMagicNumberKey(exampleMagicNumber, UUID.randomUUID(), exampleEmail);
        return new MessageByMagicNumber(magicNumberKey, exampleTitle, exampleContent);
    }
}
