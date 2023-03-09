package uz.pdp.library.repository;

import uz.pdp.library.model.Rent;

import java.util.ArrayList;

public interface RentRepository {
    void addData(Rent rent);
    ArrayList<Rent> readData();
    void updateData(ArrayList<Rent> rents);
}
