package pl.degath.message.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Slice;
import pl.degath.message.MessageFixtures;
import pl.degath.message.port.MessageByEmailInMemory;
import pl.degath.message.port.MessageByMagicNumberInMemory;
import pl.degath.message.port.MessageInMemory;
import pl.degath.message.port.MessageRepository;
import pl.degath.message.query.GetMessagesByEmail;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class GetMessagesByEmailHandlerTest {

    private GetMessagesByEmailHandler handler;
    private MessageRepository repository;

    @BeforeEach
    void setUp() {
        MessageByEmailInMemory byEmailRepository = new MessageByEmailInMemory();
        repository = new MessageInMemory(byEmailRepository, new MessageByMagicNumberInMemory());
        handler = new GetMessagesByEmailHandler(byEmailRepository);
    }

    @Test
    void shouldReturnSlicedMessagesWithMatchingEmail() {
        existingMessages(4);
        GetMessagesByEmail query = new GetMessagesByEmail("test@wp.pl", 0, 2);

        Slice<MessageByEmail> result = handler.handle(query);

        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getNumberOfElements()).isEqualTo(4);
    }

    @Test
    void shouldReturnEmptyListWhenNoResults() {
        Slice<MessageByEmail> result = handler.handle(new GetMessagesByEmail("test@wp.pl", 0, 10));

        assertThat(result).isEmpty();
    }

    private Message existingMessage() {
        return repository.insert(MessageFixtures.message());
    }

    void existingMessages(int messagesCount) {
        IntStream.rangeClosed(1, messagesCount)
                .forEach(hotel -> existingMessage());
    }
}