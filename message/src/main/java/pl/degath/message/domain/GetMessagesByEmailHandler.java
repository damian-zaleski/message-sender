package pl.degath.message.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import pl.degath.message.domain.MessageByEmail;
import pl.degath.message.infrastructure.QueryHandler;
import pl.degath.message.port.MessageByEmailRepository;
import pl.degath.message.query.GetMessagesByEmail;

import java.util.Objects;

public class GetMessagesByEmailHandler implements QueryHandler<GetMessagesByEmail, Slice<MessageByEmail>> {

    private final MessageByEmailRepository repository;

    public GetMessagesByEmailHandler(MessageByEmailRepository repository) {
        this.repository = Objects.requireNonNull(repository, "Repository has to be specified.");
    }

    @Override
    public Slice<MessageByEmail> handle(GetMessagesByEmail query) {
        return repository.findByKeyEmail(query.getEmail(), PageRequest.of(query.getPageNumber(), query.getSize()));
    }
}
