package uz.pdp.library.controller;

import uz.pdp.library.model.Book;
import uz.pdp.library.model.Rent;
import uz.pdp.library.model.User;
import uz.pdp.library.service.BookServiceImp;
import uz.pdp.library.service.RentServiceImp;
import uz.pdp.library.service.UserServiceImp;

import java.util.Objects;
import java.util.UUID;

import static uz.pdp.library.controller.ClassUtils.*;

public class LibrarianMenu {

    BookUI bookUI = new BookUI();
    BookServiceImp bookService = new BookServiceImp();
    RentServiceImp rentService = new RentServiceImp();
    UserServiceImp userService = new UserServiceImp();

    public void librarianMenu(){
        int choice = 0;
        while(true) {
            System.out.println("1. Add\t 2. Rented books(Choose for all data)\t 3. All books\t 4. Block User\t 0.Back");
            choice = scanNum.nextInt();
            switch (choice){
                case 1 -> {
                    addBook();
                }
                case 2 -> {
                    rentedBooks();
                }
                case 3 -> {
                    bookUI.showAllBooks();
                }
                case 4 -> {
                    blockUnblock();
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    public void addBook(){
        System.out.print("Book title : ");
        String title = scanStr.nextLine();
        System.out.print("Book author : ");
        String author = scanStr.nextLine();
        System.out.print("Number of this book : ");
        int numberOfBooks = scanNum.nextInt();
        Book book = bookService.getBook(title, author);
        if(book == null){
            Book newBook = new Book(title, author, numberOfBooks, 0);
            newBook.setId(UUID.randomUUID());
            bookService.addElement(newBook);
        }else{
            book.setNumberOfBook(book.getNumberOfBook() + numberOfBooks);
            bookService.updateBookData(book);
        }
        System.out.println("\nSuccessfully added\n");
    }

    public void showUsers(){
        System.out.println();
        int i = 1;
        for (User allUser : userService.getAllUsers()) {
            System.out.println(i++ + ". Name : " + allUser.getName());
            System.out.println("   Phone number : " + allUser.getPhoneNumber());
            if(allUser.getBlock()){
                System.out.println("   Situation : Currently this user is blocked for some reasons" );
            }
            System.out.println("---------------------------------------------");
        }
    }

    public void blockUnblock(){
        System.out.println("1. Block\t 2. Unblock\t 0. Back");
        int choice = scanNum.nextInt();
        switch (choice){
            case 1 -> {
                blockUser();
            }
            case 2 -> {
                unBlock();
            }
            case 0 -> {

            }
            default -> System.out.println("\nWrong input\n");
        }
    }

    public void blockUser(){
        showUsers();
        System.out.print("\nChoose (Back=0) : ");
        int choice = scanNum.nextInt();
        if(choice == 0){
            System.out.println();
            return;
        } else if(choice < 0 || choice > userService.getAllUsers().size()){
            System.out.println("\nWrong input\n");
            return;
        }
        User user = userService.getAllUsers().get(--choice);
        if(user.getBlock()){
            System.out.println("\nThis user has already been blocked\n");
            return;
        }
        user.setBlock(true);
        userService.updateUserInfo(user);
        System.out.println("\nUser is successfully blocked\n");
    }

    public void unBlock(){
        showUsers();
        System.out.print("\nChoose (Back=0) : ");
        int choice = scanNum.nextInt();
        if(choice == 0){
            System.out.println();
            return;
        } else if(choice < 0 || choice > userService.getAllUsers().size()){
            System.out.println("\nWrong input\n");
            return;
        }
        User user = userService.getAllUsers().get(--choice);
        if(!user.getBlock()){
            System.out.println("\nThis user ain't blocked yet\n");
            return;
        }
        user.setBlock(false);
        userService.updateUserInfo(user);
        System.out.println("\nUser is successfully unblocked\n");
    }

    public void rentedBooks(){
        System.out.println();
        int i = 1;
        for (Rent rent : rentService.getAllWithoutDuplicates()) {
            Book book = bookService.getById(rent.getBookId());
            System.out.println(i++ + ". Title : " + book.getTitle());
            System.out.println("   Author : " + book.getAuthor());
            System.out.println("   Number of people who rented this book : " + book.getNumOfBorrowedBooks());
            System.out.println("---------------------------------------------------------------");
        }
        chooseToSeeAllInfo();
    }

    public void chooseToSeeAllInfo(){
        System.out.print("\nChoose to see all info (Back=0) : ");
        int choice = scanNum.nextInt();
        if(choice == 0){
            System.out.println();
            return;
        } else if(choice < 0 || choice > rentService.getAllWithoutDuplicates().size()){
            System.out.println("\nWrong input\n");
            return;
        }
        Rent rent = rentService.getAllWithoutDuplicates().get(--choice);
        seeInfo(rent);
    }

    public void seeInfo(Rent rent){
        System.out.println();
        int i = 1;
        for (Rent all : rentService.getAll()) {
            if (Objects.equals(rent.getBookId(), all.getBookId())) {
                User user = userService.getById(all.getUserId());
                System.out.println(i++ + ". Name : " + user.getName());
                System.out.println("   Phone number : " + user.getPhoneNumber());
                System.out.println("---------------------------------------------");
            }
        }
        System.out.println();
    }
}
