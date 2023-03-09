package uz.pdp.library.service;

import uz.pdp.library.model.Roles;
import uz.pdp.library.model.Status;
import uz.pdp.library.model.User;
import uz.pdp.library.repository.UserRepositoryImp;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class UserServiceImp implements UserService{

    UserRepositoryImp userRepository = new UserRepositoryImp();

    @Override
    public void addElement(User user) {
        userRepository.addData(user);
    }

    @Override
    public User getById(UUID id) {
        for (User readDatum : userRepository.readData()) {
            if(Objects.equals(readDatum.getId(), id))
                return readDatum;
        }
        return null;
    }

    @Override
    public boolean checkUserUnique(String phoneNumber) {
        for (User readDatum : userRepository.readData()) {
            if(Objects.equals(readDatum.getPhoneNumber(), phoneNumber)){
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUser(String phoneNumber, String password) {
        for (User readDatum : userRepository.readData()) {
            if(Objects.equals(readDatum.getPhoneNumber(), phoneNumber) &&
                    Objects.equals(readDatum.getPassword(), password))
                return readDatum;
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (User readDatum : userRepository.readData()) {
            if(Objects.equals(readDatum.getRole(), Roles.USER))
                users.add(readDatum);
        }
        return users;
    }

    @Override
    public void updateUserInfo(User user) {
        ArrayList<User> users = new ArrayList<>();
        for (User readDatum : userRepository.readData()) {
            if(Objects.equals(readDatum.getId(), user.getId())){
                users.add(user);
            } else {
                users.add(readDatum);
            }
        }
        userRepository.updateData(users);
    }
}
