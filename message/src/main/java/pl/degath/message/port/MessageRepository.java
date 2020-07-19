package pl.degath.message.port;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import pl.degath.message.Message;

import java.util.UUID;

public interface MessageRepository extends ExtendedCassandraRepository<Message, UUID> {
    Slice<Message> findByKeyEmail(String email, Pageable pageable);
}