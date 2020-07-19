package pl.degath.message.domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import pl.degath.message.infrastructure.Validator;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@PrimaryKeyClass
public class MessageByEmailKey implements Serializable {

    @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.PARTITIONED)
    private String email;

    @PrimaryKeyColumn(name = "messageId", ordinal = 1, ordering = Ordering.DESCENDING)
    private UUID messageId;

    @PrimaryKeyColumn(name = "magicNumber", ordinal = 1, ordering = Ordering.DESCENDING)
    private int magicNumber;

    public MessageByEmailKey(String email, UUID messageId, int magicNumber) {
        this.email = Validator.validEmail(email, "Message email is required. Has to be a valid email address.");
        this.messageId = Objects.requireNonNull(messageId, "MessageId has to be specified. Cannot be null");
        this.magicNumber = magicNumber;
    }

    public String getEmail() {
        return email;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageByEmailKey that = (MessageByEmailKey) o;
        return magicNumber == that.magicNumber &&
                Objects.equals(email, that.email) &&
                Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, messageId, magicNumber);
    }
}
