package pl.degath.application.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.degath.application.message.request.PaginationRequest;
import pl.degath.application.message.request.SaveMessageRequest;
import pl.degath.application.message.request.SendMessagesRequest;
import pl.degath.message.domain.CreateMessageHandler;
import pl.degath.message.domain.GetMessagesByEmailHandler;
import pl.degath.message.domain.MessageByEmail;
import pl.degath.message.domain.SendMessagesByMagicNumberHandler;
import pl.degath.message.query.GetMessagesByEmail;

@RestController
@RequestMapping("api/v1/messages")
public class MessageRestController implements MessageApi {

    @Autowired
    private CreateMessageHandler createMessageHandler;
    @Autowired
    private SendMessagesByMagicNumberHandler sendMessagesByMagicNumberHandler;
    @Autowired
    private GetMessagesByEmailHandler getMessagesByEmailHandler;

    @Override
    @PostMapping
    public void saveMessage(@RequestBody SaveMessageRequest saveMessageRequest) {
        createMessageHandler.handle(saveMessageRequest.toCommand());
    }

    @Override
    @PostMapping("/send")
    public void sendMessagesByMagicNumber(@RequestBody SendMessagesRequest sendMessagesRequest) {
        sendMessagesByMagicNumberHandler.handle(sendMessagesRequest.toCommand());
    }

    @Override
    @GetMapping("/{email}")
    @ResponseBody
    public Slice<MessageByEmail> getMessagesByEmail(@PathVariable String email,
                                                    PaginationRequest pageRequest) {
        return getMessagesByEmailHandler.handle(new GetMessagesByEmail(email, pageRequest.getPageNumber(), pageRequest.getSize()));
    }
}
