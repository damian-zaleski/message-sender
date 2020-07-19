package pl.degath.message.port;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import pl.degath.message.domain.MessageByMagicNumber;
import pl.degath.message.domain.MessageByMagicNumberKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageByMagicNumberInMemory implements MessageByMagicNumberRepository {

    final Map<MessageByMagicNumberKey, MessageByMagicNumber> entities;

    public MessageByMagicNumberInMemory() {
        this.entities = new HashMap<>();
    }

    @Override
    public List<MessageByMagicNumber> findByKeyMagicNumber(int magicNumber) {
        return entities.values()
                .stream()
                .filter(message -> message.getKey().getMagicNumber() == magicNumber)
                .collect(Collectors.toList());
    }

    @Override
    public <S extends MessageByMagicNumber> S save(S entity) {
        entities.put(entity.getKey(), entity);
        return entity;
    }

    @Override
    public <S extends MessageByMagicNumber> List<S> saveAll(Iterable<S> entites) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MessageByMagicNumber> findById(MessageByMagicNumberKey key) {
        return Optional.ofNullable(entities.get(key));
    }

    @Override
    public boolean existsById(MessageByMagicNumberKey messageByMagicNumberKey) {
        return false;
    }

    @Override
    public List<MessageByMagicNumber> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<MessageByMagicNumber> findAllById(Iterable<MessageByMagicNumberKey> messageByMagicNumberKeys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        return entities.size();
    }

    @Override
    public void deleteById(MessageByMagicNumberKey messageByMagicNumberKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(MessageByMagicNumber entity) {
        entities.remove(entity.getKey());
    }

    @Override
    public void deleteAll(Iterable<? extends MessageByMagicNumber> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Slice<MessageByMagicNumber> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends MessageByMagicNumber> S insert(S entity) {
        return save(entity);
    }

    @Override
    public <S extends MessageByMagicNumber> List<S> insert(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends MessageByMagicNumber> S save(S entity, int ttl) {
        //todo consider how to implement in memory ttl and keep test fast?
        return save(entity);
    }
}
