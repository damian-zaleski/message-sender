package pl.degath.message.query;

import pl.degath.message.infrastructure.Query;

public class GetMessagesByEmail implements Query {
    private final String email;
    private final int pageNumber;
    private final int size;

    public GetMessagesByEmail(String email, int pageNumber, int size) {
        this.email = email;
        this.pageNumber = pageNumber;
        this.size = size;
    }

    public String getEmail() {
        return email;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getSize() {
        return size;
    }
}
