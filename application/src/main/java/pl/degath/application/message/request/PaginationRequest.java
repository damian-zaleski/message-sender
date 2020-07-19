package pl.degath.application.message.request;

public class PaginationRequest {

    private final int pageNumber;
    private final int size;

    public PaginationRequest(int pageNumber, int size) {
        this.pageNumber = pageNumber;
        this.size = size;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getSize() {
        return size;
    }
}
