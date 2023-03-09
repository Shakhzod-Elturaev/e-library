package uz.pdp.library.service;

import uz.pdp.library.model.Book;
import uz.pdp.library.repository.BookRepositoryImp;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BookServiceImp implements BookService{

    BookRepositoryImp bookRepository = new BookRepositoryImp();

    @Override
    public void addElement(Book book) {
        bookRepository.addData(book);
    }

    @Override
    public Book getBook(String title, String author) {
        for (Book book : bookRepository.readData()) {
            if(Objects.equals(book.getTitle(), title) && Objects.equals(book.getAuthor(), author))
                return book;
        }
        return null;
    }

    @Override
    public ArrayList<Book> getUserBooks(ArrayList<UUID> ids) {
        ArrayList<Book> books = new ArrayList<>();
        for (UUID id : ids) {
            for (Book book : bookRepository.readData()) {
                if(Objects.equals(book.getId(), id)) {
                    books.add(book);
                    break;
                }
            }
        }
        return books;
    }

    @Override
    public Book getById(UUID id) {
        for (Book book : bookRepository.readData()) {
            if(Objects.equals(book.getId(), id))
                return book;
        }
        return null;
    }

    @Override
    public ArrayList<Book> getAll() {
        return bookRepository.readData();
    }

    @Override
    public void updateBookData(Book book) {
        ArrayList<Book> books = new ArrayList<>();
        for (Book readDatum : bookRepository.readData()) {
            if(Objects.equals(readDatum.getId(), book.getId())){
                books.add(book);
            } else {
                books.add(readDatum);
            }
        }
        bookRepository.updateInfo(books);
    }
}
