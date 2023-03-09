package uz.pdp.library.controller;

import uz.pdp.library.model.Roles;
import uz.pdp.library.model.User;
import uz.pdp.library.service.UserServiceImp;

import java.util.Objects;
import java.util.UUID;

import static uz.pdp.library.controller.ClassUtils.*;

public class UserUI {

    UserServiceImp userService = new UserServiceImp();
    UserMenu userMenu = new UserMenu();
    LibrarianMenu librarianMenu = new LibrarianMenu();


    public void start(){
        int choice = 0;
        while (true) {
            System.out.println("1. Sign In\t 2. Sign Up\t 0. Exit");
            choice = scanNum.nextInt();
            switch (choice){
                case 1 -> {
                    signIn();
                }
                case 2 -> {
                    signUp();
                }
                case 0 -> {
                    System.out.println("Thank you for your cooperation!!!");
                    return;
                }
            }
        }
    }

    public void signUp(){
        System.out.print("New phone number : ");
        String phoneNumber = scanStr.nextLine();
        System.out.print("New password : ");
        String password = scanStr.nextLine();
        System.out.print("New name : ");
        String name = scanStr.nextLine();
        boolean result = userService.checkUserUnique(phoneNumber);
        if(result){
            System.out.println("This phone number has already been registered in our system...");
            return;
        }
        User user = new User(name, phoneNumber, password, Roles.USER, false);
        user.setId(UUID.randomUUID());
        userService.addElement(user);
        System.out.println("\nSuccessfully registered!!\n");
    }

    public void signIn(){
        System.out.print("Phone number : ");
        String phoneNumber = scanStr.nextLine();
        System.out.print("Password : ");
        String password = scanStr.nextLine();
        User user = userService.getUser(phoneNumber, password);
        if(user == null){
            System.out.println("\nNot found\n");
            return;
        }
        if(Objects.equals(user.getRole(), Roles.USER) && user.getBlock()){
            System.out.println("\nSorry, you cannot use our services as you are blocked by admin\n");
            return;
        }
        System.out.println("\nWelcome\n");
        if(Objects.equals(user.getRole(), Roles.USER))
            userMenu.userMenu(user);
        else
            librarianMenu.librarianMenu();
    }
}
