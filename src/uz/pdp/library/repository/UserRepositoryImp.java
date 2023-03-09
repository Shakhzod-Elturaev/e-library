package uz.pdp.library.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.pdp.library.model.Rent;
import uz.pdp.library.model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserRepositoryImp implements UserRepository{
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    @Override
    public void addData(User user) {
        ArrayList<User> users = readData();
        users.add(user);
        try(FileWriter writer = new FileWriter("User.json")){
            gson.toJson(users, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> readData() {
        ArrayList<User> users = new ArrayList<>();
        try(FileReader reader = new FileReader("User.json")){
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            users = gson.fromJson(reader, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateData(ArrayList<User> users) {
        try(FileWriter writer = new FileWriter("User.json")){
            gson.toJson(users, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
