package pl.degath.message.infrastructure;

public interface CommandHandler<T extends Command> {

    void handle(T command);
}
