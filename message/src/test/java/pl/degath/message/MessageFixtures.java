package pl.degath.message;

import pl.degath.message.command.CreateMessage;

public class MessageFixtures {

    private MessageFixtures() {
        throw new AssertionError();
    }

    public static CreateMessage createMessage() {
        return new CreateMessage("valid@wp.pl", "Test title", "This is example test content.", 1337);
    }

    public static Message message() {
        return new Message("valid@wp.pl", "Test title", "This is example test content.", 1337);
    }
}
