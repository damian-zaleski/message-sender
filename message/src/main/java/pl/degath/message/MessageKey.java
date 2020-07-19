package pl.degath.message;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import pl.degath.message.infrastructure.Validator;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@PrimaryKeyClass
public class MessageKey implements Serializable {

    @PrimaryKeyColumn(ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String email;
    @PrimaryKeyColumn
    private UUID id;

    private MessageKey() {
    }

    MessageKey(String email) {
        this.email = Validator.validEmail(email, "Message email is required. Has to be a valid email address.");
        this.id = UUID.randomUUID();
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageKey that = (MessageKey) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, id);
    }
}

