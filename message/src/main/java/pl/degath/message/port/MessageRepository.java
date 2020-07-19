package pl.degath.message.port;

import pl.degath.message.Message;

import java.util.UUID;

public interface MessageRepository extends ExtendedCassandraRepository<Message, UUID> {

}