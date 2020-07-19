package pl.degath.message.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import pl.degath.message.infrastructure.Validator;

import java.util.UUID;

@Table
public class Message {

    @PrimaryKey
    private UUID messageId;
    private String title;
    private String content;
    private String email;
    private int magicNumber;

    private Message() {
    }

    public Message(String title, String content, String email, int magicNumber) {
        this.messageId = UUID.randomUUID();
        this.title = Validator.notBlank(title, "Message title is required. Cannot be blank.");
        this.content = Validator.notBlank(content, "Message content is required. Cannot be blank.");
        this.email = Validator.validEmail(email, "Message email is required. Has to be a valid email address.");
        this.magicNumber = magicNumber;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getEmail() {
        return email;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public MessageByEmail toMessageByEmail() {
        return new MessageByEmail(new MessageByEmailKey(this.email, this.messageId, this.magicNumber), this.title, this.content);
    }

    public MessageByMagicNumber toMessageByMagicNumber() {
        return new MessageByMagicNumber(new MessageByMagicNumberKey(this.magicNumber, this.messageId, this.email), this.title, this.content);
    }
}
