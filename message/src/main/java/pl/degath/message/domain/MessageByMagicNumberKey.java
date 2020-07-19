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
public class MessageByMagicNumberKey implements Serializable {
    @PrimaryKeyColumn(name = "magicNumber", type = PrimaryKeyType.PARTITIONED)
    private int magicNumber;

    @PrimaryKeyColumn(name = "messageId", ordinal = 1, ordering = Ordering.DESCENDING)
    private UUID messageId;

    @PrimaryKeyColumn(name = "email", ordinal = 2, ordering = Ordering.DESCENDING)
    private String email;

    public MessageByMagicNumberKey(int magicNumber, UUID messageId, String email) {
        this.magicNumber = magicNumber;
        this.messageId = Objects.requireNonNull(messageId, "MessageId has to be specified. Cannot be null.");
        this.email = Validator.validEmail(email, "Message email is required. Has to be a valid email address.");
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageByMagicNumberKey that = (MessageByMagicNumberKey) o;
        return magicNumber == that.magicNumber &&
                Objects.equals(messageId, that.messageId) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magicNumber, messageId, email);
    }
}
