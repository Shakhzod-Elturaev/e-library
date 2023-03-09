package uz.pdp.library.model;

public class Book extends BaseModel{
    private String title;
    private String author;
    private Integer numberOfBook;
    private Integer numOfBorrowedBooks;

    public Book() {
    }

    public Book(String title, String author, Integer numberOfBook, Integer numOfBorrowedBooks) {
        this.title = title;
        this.author = author;
        this.numberOfBook = numberOfBook;
        this.numOfBorrowedBooks = numOfBorrowedBooks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfBook() {
        return numberOfBook;
    }

    public void setNumberOfBook(Integer numberOfBook) {
        this.numberOfBook = numberOfBook;
    }


    public Integer getNumOfBorrowedBooks() {
        return numOfBorrowedBooks;
    }

    public void setNumOfBorrowedBooks(Integer numOfBorrowedBooks) {
        this.numOfBorrowedBooks = numOfBorrowedBooks;
    }

    @Override
    public String toString() {
        return "Title : " + title + '\n' +
                "   Author : " + author + '\n' +
                "   Number of books : " + numOfBorrowedBooks + "/" + numberOfBook;
    }
}
