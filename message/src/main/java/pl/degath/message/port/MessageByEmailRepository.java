package pl.degath.message.port;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import pl.degath.message.domain.MessageByEmail;
import pl.degath.message.domain.MessageByEmailKey;

@Repository
public interface MessageByEmailRepository extends CassandraRepository<MessageByEmail, MessageByEmailKey>, CustomizedSave<MessageByEmail> {
    Slice<MessageByEmail> findByKeyEmail(String email, Pageable pageable);
}