package pl.degath.application.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import org.springframework.mail.javamail.JavaMailSender;
import pl.degath.message.CreateMessageHandler;
import pl.degath.message.GetMessagesByEmailHandler;
import pl.degath.message.SendMessageHandler;
import pl.degath.message.SendMessagesByMagicNumberHandler;
import pl.degath.message.domain.*;
import pl.degath.message.port.MessageByEmailRepository;
import pl.degath.message.port.MessageByMagicNumberRepository;
import pl.degath.message.port.MessageRepositoryImpl;

import java.util.UUID;

@Configuration
public class MessageConfig {

    @Bean
    public MessageRepositoryImpl messageRepository(CassandraTemplate cassandraTemplate,
                                               CassandraOperations operations,
                                               MessageByEmailRepository messageByEmailRepository,
                                               MessageByMagicNumberRepository messageByMagicNumberRepository) {
        final CassandraPersistentEntity<?> entity =
                cassandraTemplate
                        .getConverter()
                        .getMappingContext()
                        .getRequiredPersistentEntity(Message.class);
        final CassandraEntityInformation<Message, UUID> metadata =
                new MappingCassandraEntityInformation<>((CassandraPersistentEntity<Message>) entity, cassandraTemplate.getConverter());
        return new MessageRepositoryImpl(metadata, operations, messageByEmailRepository, messageByMagicNumberRepository);
    }

    @Bean
    public CreateMessageHandler createMessageHandler(MessageRepositoryImpl messageRepository) {
        return new CreateMessageHandler(messageRepository);
    }

    @Bean
    public SendMessagesByMagicNumberHandler sendMessagesByMagicNumberHandler(MessageRepositoryImpl messageRepository,
                                                                             MessageByMagicNumberRepository messageByMagicNumberRepository,
                                                                             JavaMailSender mailSender) {
        var sendMessageHandler = new SendMessageHandler(messageRepository, mailSender);
        return new SendMessagesByMagicNumberHandler(messageByMagicNumberRepository, sendMessageHandler);
    }

    @Bean
    public GetMessagesByEmailHandler getMessagesByEmailQueryHandler(MessageByEmailRepository messageByEmailRepository) {
        return new GetMessagesByEmailHandler(messageByEmailRepository);
    }
}
