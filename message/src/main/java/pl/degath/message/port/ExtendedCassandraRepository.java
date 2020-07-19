package pl.degath.message.port;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtendedCassandraRepository<T, ID> extends CassandraRepository<T, ID> {
    <S extends T> S insert(S entity, int ttl);
}
