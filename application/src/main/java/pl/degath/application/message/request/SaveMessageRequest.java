package pl.degath.application.message.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.degath.message.command.CreateMessage;
import pl.degath.message.infrastructure.Validator;

public class SaveMessageRequest {

    private final String email;
    private final String title;
    private final String content;
    private final int magicNumber;

    @JsonCreator
    public SaveMessageRequest(@JsonProperty("email") String email,
                              @JsonProperty("title") String title,
                              @JsonProperty("content") String content,
                              @JsonProperty("magicNumber") int magicNumber) {
        this.email = Validator.validEmail(email, "Message email is required. Has to be a valid email address.");
        this.title = Validator.notBlank(title, "Message title is required. Cannot be blank.");
        this.content = Validator.notBlank(content, "Message content is required. Cannot be blank.");
        this.magicNumber = magicNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    @JsonIgnore
    public CreateMessage toCommand() {
        return new CreateMessage(email, title, content, magicNumber);
    }
}
