package pl.degath.message.command;

import pl.degath.message.infrastructure.Command;

public class SendMessagesByMagicNumber implements Command {
    private final int magicNumber;

    public SendMessagesByMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public int getMagicNumber() {
        return magicNumber;
    }
}
