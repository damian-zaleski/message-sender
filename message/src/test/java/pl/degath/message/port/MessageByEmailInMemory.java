package pl.degath.message.port;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import pl.degath.message.domain.MessageByEmail;
import pl.degath.message.domain.MessageByEmailKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MessageByEmailInMemory implements MessageByEmailRepository {
    final Map<MessageByEmailKey, MessageByEmail> entities;

    public MessageByEmailInMemory() {
        this.entities =  new HashMap<>();
    }

    @Override
    public Slice<MessageByEmail> findByKeyEmail(String email, Pageable pageable) {
        var a = entities.values()
                .stream()
                .filter(message -> message.getKey().getEmail().equals(email))
                .collect(Collectors.toList());
        return new SliceImpl<MessageByEmail>(a, pageable, true);
    }

    @Override
    public <S extends MessageByEmail> S save(S entity) {
        entities.put(entity.getKey(), entity);
        return entity;
    }

    @Override
    public <S extends MessageByEmail> List<S> saveAll(Iterable<S> entites) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MessageByEmail> findById(MessageByEmailKey key) {
        return Optional.ofNullable(entities.get(key));
    }

    @Override
    public boolean existsById(MessageByEmailKey messageByEmailKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MessageByEmail> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<MessageByEmail> findAllById(Iterable<MessageByEmailKey> messageByEmailKeys) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        return entities.size();
    }

    @Override
    public void deleteById(MessageByEmailKey messageByEmailKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(MessageByEmail entity) {
        entities.remove(entity.getKey());
    }

    @Override
    public void deleteAll(Iterable<? extends MessageByEmail> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Slice<MessageByEmail> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends MessageByEmail> S insert(S entity) {
        return save(entity);
    }

    @Override
    public <S extends MessageByEmail> List<S> insert(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends MessageByEmail> S save(S entity, int ttl) {
        //todo consider how to implement in memory ttl and keep test fast?
        return save(entity);
    }
}
