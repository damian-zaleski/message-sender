package pl.degath.message.port;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;
import pl.degath.message.domain.Message;

import java.util.UUID;

public class MessageRepositoryImpl extends SimpleCassandraRepository<Message, UUID> implements CustomizedSave<Message>, MessageRepository {

    private static final int fiveMinutesInSeconds = 300;
    private final CassandraOperations operations;
    private final MessageByEmailRepository messageByEmailRepository;
    private final MessageByMagicNumberRepository messageByMagicNumberRepository;

    public MessageRepositoryImpl(CassandraEntityInformation<Message, UUID> metadata, CassandraOperations operations, MessageByEmailRepository messageByEmailRepository, MessageByMagicNumberRepository messageByMagicNumberRepository) {
        super(metadata, operations);
        this.operations = operations;
        this.messageByEmailRepository = messageByEmailRepository;
        this.messageByMagicNumberRepository = messageByMagicNumberRepository;
    }

    @Override
    public <S extends Message> S save(S entity, int ttl) {
        InsertOptions insertOptions = org.springframework.data.cassandra.core.InsertOptions.builder().ttl(ttl).build();
        operations.insert(entity, insertOptions);
        return entity;
    }

    @Override
    public <S extends Message> S insert(S entity) {
        messageByEmailRepository.save(entity.toMessageByEmail(), fiveMinutesInSeconds);
        messageByMagicNumberRepository.save(entity.toMessageByMagicNumber(), fiveMinutesInSeconds);
        save(entity, fiveMinutesInSeconds);
        return entity;
    }

    @Override
    public <S extends Message> S save(S entity) {
        return insert(entity);
    }

    @Override
    public void delete(Message entity) {
        messageByEmailRepository.delete(entity.toMessageByEmail());
        messageByMagicNumberRepository.delete(entity.toMessageByMagicNumber());
        super.delete(entity);
    }

    @Override
    public Message findByMessageId(UUID messageId) {
        return operations.selectOneById(messageId, Message.class);
    }
}
