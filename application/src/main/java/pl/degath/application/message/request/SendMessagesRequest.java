package pl.degath.application.message.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.degath.message.command.SendMessagesByMagicNumber;

public class SendMessagesRequest {

    private final int magicNumber;

    @JsonCreator
    public SendMessagesRequest(@JsonProperty("magicNumber") int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    @JsonIgnore
    public SendMessagesByMagicNumber toCommand() {
        return new SendMessagesByMagicNumber(magicNumber);
    }
}
