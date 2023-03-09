package uz.pdp.library.service;

import uz.pdp.library.model.Book;

import java.util.ArrayList;
import java.util.UUID;

public interface BookService extends BaseService<Book>{
    ArrayList<Book> getAll();

    void updateBookData(Book book);

    Book getBook(String title, String author);
    ArrayList<Book> getUserBooks(ArrayList<UUID> ids);
}
