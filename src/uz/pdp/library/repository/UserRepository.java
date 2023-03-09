package uz.pdp.library.repository;

import uz.pdp.library.model.User;

import java.util.ArrayList;

public interface UserRepository {
    void addData(User user);
    ArrayList<User> readData();
    void updateData(ArrayList<User> users);
}
