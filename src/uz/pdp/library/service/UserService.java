package uz.pdp.library.service;

import uz.pdp.library.model.User;

import java.util.ArrayList;

public interface UserService extends BaseService<User>{
    boolean checkUserUnique(String phoneNumber);
    User getUser(String phoneNumber, String password);
    ArrayList<User> getAllUsers();
    void updateUserInfo(User user);
}
