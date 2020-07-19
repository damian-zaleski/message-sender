package pl.degath.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Slice;
import pl.degath.message.port.MessageRepository;
import pl.degath.message.query.GetMessagesByEmail;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class GetMessagesByEmailQueryHandlerTest {

    private GetMessagesByEmailQueryHandler handler;
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        messageRepository = new InMemoryMessageRepository();
        handler = new GetMessagesByEmailQueryHandler(messageRepository);
    }

    @Test
    void shouldReturnSlicedMessagesWithMatchingEmail() {
        existingMessages(4);

        GetMessagesByEmail query = new GetMessagesByEmail("test@wp.pl", 0, 2);

        Slice<Message> result = handler.handle(query);


        assertThat(result.getSize()).isEqualTo(2);
        assertThat(result.getNumber()).isEqualTo(0);
        assertThat(result.getNumberOfElements()).isEqualTo(4);
    }

    @Test
    void shouldReturnEmptyListWhenNoResults() {
        Slice<Message> result = handler.handle(new GetMessagesByEmail("a", 0, 10));

        assertThat(result).isEmpty();
    }

    private Message existingMessage() {
        return messageRepository.insert(MessageFixtures.message());
    }

    void existingMessages(int messagesCount) {
        IntStream.rangeClosed(1, messagesCount)
                .forEach(hotel -> existingMessage());
    }
}