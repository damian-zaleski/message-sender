package pl.degath.message.port;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import pl.degath.message.domain.MessageByMagicNumber;
import pl.degath.message.domain.MessageByMagicNumberKey;

import java.util.List;

@Repository
public interface MessageByMagicNumberRepository extends CassandraRepository<MessageByMagicNumber, MessageByMagicNumberKey>, CustomizedSave<MessageByMagicNumber> {
    List<MessageByMagicNumber> findByKeyMagicNumber(int magicNumber);
}