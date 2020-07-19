package pl.degath.message;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import pl.degath.message.infrastructure.QueryHandler;
import pl.degath.message.port.MessageRepository;
import pl.degath.message.query.GetMessagesByEmail;

public class GetMessagesByEmailQueryHandler implements QueryHandler<GetMessagesByEmail, Slice<Message>> {

    private final MessageRepository messageRepository;

    public GetMessagesByEmailQueryHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Slice<Message> handle(GetMessagesByEmail query) {
        return messageRepository.findByKeyEmail(query.getEmail(), PageRequest.of(query.getPageNumber(), query.getSize()));
    }
}
