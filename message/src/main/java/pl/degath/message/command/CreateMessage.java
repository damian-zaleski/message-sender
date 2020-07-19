package pl.degath.message.command;

import pl.degath.message.infrastructure.Command;

public class CreateMessage implements Command {

    private final String email;
    private final String title;
    private final String content;
    private final int magicNumber;

    public CreateMessage(String email, String title, String content, int magicNumber) {
        this.email = email;
        this.title = title;
        this.content = content;
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
}
