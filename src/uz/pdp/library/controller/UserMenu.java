package uz.pdp.library.controller;

import uz.pdp.library.model.Book;
import uz.pdp.library.model.Rent;
import uz.pdp.library.model.Status;
import uz.pdp.library.model.User;
import uz.pdp.library.service.BookServiceImp;
import uz.pdp.library.service.RentServiceImp;

import java.util.ArrayList;
import java.util.Objects;

import static uz.pdp.library.controller.ClassUtils.*;

public class UserMenu {

    BookUI bookUI = new BookUI();
    BookServiceImp bookService = new BookServiceImp();
    RentServiceImp rentService = new RentServiceImp();

    public void userMenu(User user){
        int choice = 0;
        while (true) {
            System.out.println("1. All books\t 2. Rent\t 3. Return\t 4. See my books\t 5. All history\t 0. Exit");
            choice = scanNum.nextInt();
            switch (choice){
                case 1 -> {
                    bookUI.showAllBooks();
                }
                case 2 -> {
                    rentBook(user);
                }
                case 3 -> {
                    returnBook(user);
                }
                case 4 -> {
                    bookUI.seeMyBooks(user);
                }
                case 5 -> {
                    myHistory(user);
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    public void rentBook(User user){
        bookUI.showAllBooks();
        System.out.print("\nChoose(Back=0) : ");
        int choice = scanNum.nextInt();
        if(choice == 0){
            System.out.println();
            return;
        } else if(choice < 0 || choice > bookService.getAll().size()){
            System.out.println("\nWrong input\n");
            return;
        }
        Book book = bookService.getAll().get(--choice);
        if(Objects.equals(book.getNumberOfBook(), book.getNumOfBorrowedBooks())) {
            System.out.println("\nAll of this book has already been rented\n");
            return;
        }
        boolean result = rentService.checkBookRentedOrNot(user.getId(), book.getId());
        if(result){
            System.out.println("\nThis user have already rented this book\n");
            return;
        }
        book.setNumOfBorrowedBooks(book.getNumOfBorrowedBooks()+1);
        bookService.updateBookData(book);
        Rent rent = new Rent(user.getId(), book.getId(), Status.RENTED);
        rentService.addElement(rent);
        System.out.println("\nSuccessfully rented\n");
    }

    public void returnBook(User user){
        bookUI.seeMyBooks(user);
        ArrayList<Book> myBooks = bookService.getUserBooks(rentService.getUsersBooks(user.getId()));
        System.out.print("\nChoose (Back=0) : ");
        int choice = scanNum.nextInt();
        if(choice == 0){
            System.out.println();
            return;
        } else if(choice < 0 || choice > myBooks.size()){
            System.out.println("\nWrong input\n");
            return;
        }
        Book book = myBooks.get(--choice);
        Rent rent = rentService.getById(book.getId(), user.getId());
        rent.setStatus(Status.RETURNED);
        rentService.updateRentInfo(rent);
        book.setNumOfBorrowedBooks(book.getNumOfBorrowedBooks() - 1);
        bookService.updateBookData(book);
        System.out.println("\nBook is successfully returned\n");
    }

    public void myHistory(User user){
        int i = 1;
        for (Rent rent : rentService.getAll()) {
            if(Objects.equals(rent.getUserId(), user.getId())){
                Book book = bookService.getById(rent.getBookId());
                System.out.println(i++ + ". Title : " + book.getTitle());
                System.out.println("   Author : " + book.getAuthor());
                System.out.println("   Status : " + rent.getStatus());
                System.out.println("---------------------------------------------");
            }
        }
    }
}
