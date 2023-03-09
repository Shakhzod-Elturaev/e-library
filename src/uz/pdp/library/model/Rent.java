package uz.pdp.library.model;

import java.util.UUID;

public class Rent {
    private UUID userId;
    private UUID bookId;
    private Status status;

    public Rent() {
    }

    public Rent(UUID userId, UUID bookId, Status status) {
        this.userId = userId;
        this.bookId = bookId;
        this.status = status;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
