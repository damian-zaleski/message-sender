package pl.degath.message.port;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import pl.degath.message.domain.Message;

import java.util.*;

public class MessageInMemory implements MessageRepository {

    private final Map<UUID, Message> entities;
    private final MessageByEmailInMemory byEmailInMemory;
    private final MessageByMagicNumberInMemory byMagicNumberInMemory;

    public MessageInMemory(MessageByEmailInMemory byEmailInMemory, MessageByMagicNumberInMemory byMagicNumberInMemory) {
        this.entities = new HashMap<>();
        this.byEmailInMemory = byEmailInMemory;
        this.byMagicNumberInMemory = byMagicNumberInMemory;
    }

    @Override
    public <S extends Message> S save(S entity) {
        byEmailInMemory.save(entity.toMessageByEmail());
        byMagicNumberInMemory.save(entity.toMessageByMagicNumber());
        entities.put(entity.getMessageId(), entity);
        return entity;
    }

    @Override
    public Message findByMessageId(UUID messageId) {
        return entities.get(messageId);
    }

    @Override
    public <S extends Message> List<S> saveAll(Iterable<S> entites) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Message> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<Message> findAllById(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Slice<Message> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends Message> S insert(S entity) {
        return save(entity);
    }

    @Override
    public <S extends Message> List<S> insert(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findById(UUID messageId) {
        return Optional.ofNullable(entities.get(messageId));
    }

    @Override
    public boolean existsById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        return entities.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        entities.remove(uuid);
    }

    @Override
    public void delete(Message entity) {
        byEmailInMemory.delete(entity.toMessageByEmail());
        byMagicNumberInMemory.delete(entity.toMessageByMagicNumber());
        entities.remove(entity.getMessageId());
    }

    @Override
    public void deleteAll(Iterable<? extends Message> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
