package pl.degath.message.infrastructure;

public interface QueryHandler<T extends Query, R> {

    R handle(T query);
}
