package pl.degath.message.port;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pl.degath.message.domain.Message;

import java.util.UUID;

@NoRepositoryBean
public interface MessageRepository extends CassandraRepository<Message, UUID> {
    Message findByMessageId(UUID messageId);
}
