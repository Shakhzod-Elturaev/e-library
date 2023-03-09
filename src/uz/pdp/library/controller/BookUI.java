package uz.pdp.library.controller;

import uz.pdp.library.model.Book;
import uz.pdp.library.model.Rent;
import uz.pdp.library.model.Status;
import uz.pdp.library.model.User;
import uz.pdp.library.service.BookServiceImp;
import uz.pdp.library.service.RentServiceImp;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static uz.pdp.library.controller.ClassUtils.*;

public class BookUI {

    BookServiceImp bookService = new BookServiceImp();
    RentServiceImp rentService = new RentServiceImp();

    public void showAllBooks(){
        System.out.println();
        int i = 1;
        for (Book book : bookService.getAll()) {
            System.out.println(i++ + ". " + book);
            System.out.println("---------------------------------------------");
        }
        System.out.println();
    }

    public void seeMyBooks(User user){
        System.out.println();
        ArrayList<Book> myBooks = bookService.getUserBooks(rentService.getUsersBooks(user.getId()));
        if(myBooks.size() == 0){
            System.out.println("\nCurrently, you ain't rented any book\n");
            return;
        }
        int i = 1;
        for (Book myBook : myBooks) {
            System.out.println(i++ + ". Name : " + myBook.getTitle());
            System.out.println("   Author : " + myBook.getAuthor());
            System.out.println("---------------------------------------------");
        }
    }
}
