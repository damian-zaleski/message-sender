package pl.degath.message.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import pl.degath.message.infrastructure.Validator;

import java.util.Objects;

@Table
public class MessageByEmail {

    @PrimaryKey
    private MessageByEmailKey key;
    private String title;
    private String content;

    public MessageByEmail(MessageByEmailKey key, String title, String content) {
        this.key = Objects.requireNonNull(key, "Key has to be specified. Cannot be null.");
        this.title = Validator.notBlank(title, "Message title is required. Cannot be blank.");
        this.content = Validator.notBlank(content, "Message content is required. Cannot be blank.");

    }

    public MessageByEmailKey getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Message toMessage() {
        return new Message(title, content, key.getEmail(), key.getMagicNumber());
    }

}
