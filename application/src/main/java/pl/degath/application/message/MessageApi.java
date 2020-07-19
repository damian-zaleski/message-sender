package pl.degath.application.message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Slice;
import pl.degath.application.message.request.PaginationRequest;
import pl.degath.application.message.request.SaveMessageRequest;
import pl.degath.application.message.request.SendMessagesRequest;
import pl.degath.message.domain.Message;
import pl.degath.message.domain.MessageByEmail;

public interface MessageApi {

    /**
     * Save a {@link Message} based on the {@link SaveMessageRequest request}
     *
     * @param saveMessageRequest save message request that has to be provided
     */
    @ApiOperation("Save the message.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully saved a message"),
            @ApiResponse(code = 400, message = "Validation failed."),
    })
    void saveMessage(SaveMessageRequest saveMessageRequest);

    /**
     * Send a list of {@link Message messages} based on the {@link SendMessagesRequest request}
     *
     * @param sendMessagesRequest send message request that has to be provided
     */
    @ApiOperation("Send messages by magic number.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully send the messages."),
            @ApiResponse(code = 400, message = "Validation failed."),
    })
    void sendMessagesByMagicNumber(SendMessagesRequest sendMessagesRequest);

    /**
     * Fetch a Slice of {@link Message messages} based on the email and {@link PaginationRequest paginationRequest}
     *
     * @param email
     * @param pageRequest
     * @return
     */
    @ApiOperation("Get messages slice by email.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully fetch the messages."),
            @ApiResponse(code = 400, message = "Validation failed."),
    })
    Slice<MessageByEmail> getMessagesByEmail(String email, PaginationRequest pageRequest);
}
