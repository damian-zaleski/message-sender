package pl.degath.message;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import pl.degath.message.port.MessageRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryMessageRepository implements MessageRepository {

    private final Map<UUID, Message> entities;

    public InMemoryMessageRepository() {
        this.entities = new HashMap<>();
    }

    //todo consider how to make pseudo implementation which:
    // allow us to test insertion part and doesn't take 5 mins
    // ATM ttl part isn't implemented...
    @Override
    public <S extends Message> S insert(S entity, int ttl) {
        insert(entity);
        return entity;
    }

    @Override
    public <S extends Message> List<S> saveAll(Iterable<S> entites) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public List<Message> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public Slice<Message> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Message> S insert(S entity) {
        entities.put(entity.getUuid(), entity);
        return entity;
    }

    @Override
    public <S extends Message> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Message> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Message> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {
        entities.remove(uuid);
    }

    @Override
    public void delete(Message entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Message> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
