package pl.degath.message.port;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

public class ExtendedCassandraRepositoryImpl<T, ID> extends SimpleCassandraRepository<T, ID> implements ExtendedCassandraRepository<T, ID> {

    private final CassandraEntityInformation<T, ID> entityInformation;
    private final CassandraOperations operations;

    public ExtendedCassandraRepositoryImpl(CassandraEntityInformation metadata, CassandraOperations operations) {
        super(metadata, operations);

        this.entityInformation = metadata;
        this.operations = operations;
    }

    @Override
    public <S extends T> S insert(S entity, int ttl) {
        operations.insert(entity, insertOptions(ttl));
        return entity;
    }

    private InsertOptions insertOptions(int ttl) {
        return InsertOptions
                .builder()
                .ttl(ttl)
                .build();
    }
}