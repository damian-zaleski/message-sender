package pl.degath.message;

import pl.degath.message.domain.Message;
import pl.degath.message.port.MessageRepository;

import java.util.stream.IntStream;

public class MessageBuilder {

    private final MessageRepository messageRepository;
    private final String title = "This is test title";
    private final String content = "this is test content";
    private final String email = "test@gmail.com";
    private int magicNumber = 1337;

    public MessageBuilder(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    MessageBuilder withMagicNumber(int newMagicNumber) {
        magicNumber = newMagicNumber;
        return this;
    }

    private Message build() {
        return new Message(title, content, email, magicNumber);
    }

    public Message inDb() {
        return messageRepository.save(build());
    }


    void inDbMultiplied(int messages) {
        IntStream.rangeClosed(1, messages)
                .forEach(message -> inDb());
    }
}
