package pl.degath.message.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import pl.degath.message.infrastructure.Validator;

import java.util.Objects;

@Table
public class MessageByMagicNumber {

    @PrimaryKey
    private MessageByMagicNumberKey key;
    private String title;
    private String content;

    public MessageByMagicNumber(MessageByMagicNumberKey key, String title, String content) {
        this.key = Objects.requireNonNull(key, "Key has to be specified. Cannot be null.");
        this.title = Validator.notBlank(title, "Message title is required. Cannot be blank.");
        this.content = Validator.notBlank(content, "Message content is required. Cannot be blank.");
    }

    public MessageByMagicNumberKey getKey() {
        return key;
    }
}
