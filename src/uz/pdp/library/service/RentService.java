package uz.pdp.library.service;

import uz.pdp.library.model.Rent;

import java.util.ArrayList;
import java.util.UUID;

public interface RentService{
    void addElement(Rent rent);
    boolean checkBookRentedOrNot(UUID userId, UUID bookId);
    ArrayList<UUID> getUsersBooks(UUID userId);
    Rent getById(UUID bookId, UUID userId);
    ArrayList<Rent> getAll();
    ArrayList<Rent> getAllWithoutDuplicates();
    void updateRentInfo(Rent rent);
}
