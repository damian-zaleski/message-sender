package pl.degath.message;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import pl.degath.message.infrastructure.Validator;

@Table
public class Message {

    @PrimaryKey
    private MessageKey key;
    private String title;
    private String content;
    private int magicNumber;

    private Message() {
        //required...
    }

    public Message(String email, String title, String content, int magicNumber) {
        this.key = new MessageKey(email);
        this.title = Validator.notBlank(title, "Message title is required. Cannot be blank.");
        this.content = Validator.notBlank(content, "Message content is required. Cannot be blank.");
        this.magicNumber = magicNumber;
    }

    public MessageKey getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getMagicNumber() {
        return magicNumber;
    }
}
