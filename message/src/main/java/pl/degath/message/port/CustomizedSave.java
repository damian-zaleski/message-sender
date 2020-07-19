package pl.degath.message.port;

public interface CustomizedSave<T> {
    <S extends T> S save(S entity, int ttl);
}