package uz.pdp.library;


import uz.pdp.library.controller.UserUI;


public class Main {

    static UserUI userUI = new UserUI();

    public static void main(String[] args) {
        System.out.println("***************** Welcome to our e-library *********************\n");
        userUI.start();
    }
}