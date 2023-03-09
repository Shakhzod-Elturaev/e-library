package uz.pdp.library.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.pdp.library.model.Rent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RentRepositoryImp implements RentRepository{
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    @Override
    public void addData(Rent rent) {
        ArrayList<Rent> rents = readData();
        rents.add(rent);
        try(FileWriter writer = new FileWriter("Rent.json")){
            gson.toJson(rents, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Rent> readData() {
        ArrayList<Rent> rents = new ArrayList<>();
        try(FileReader reader = new FileReader("Rent.json")){
            Type type = new TypeToken<ArrayList<Rent>>(){}.getType();
            rents = gson.fromJson(reader, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        return rents;
    }

    @Override
    public void updateData(ArrayList<Rent> rents) {
        try(FileWriter writer = new FileWriter("Rent.json")){
            gson.toJson(rents, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
