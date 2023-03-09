package uz.pdp.library.repository;

import uz.pdp.library.model.Book;

import java.util.ArrayList;

public interface BookRepository {
    void addData(Book book);
    ArrayList<Book> readData();
    void updateInfo(ArrayList<Book> books);
}
