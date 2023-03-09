package uz.pdp.library.service;

import uz.pdp.library.model.Rent;
import uz.pdp.library.model.Status;
import uz.pdp.library.repository.RentRepositoryImp;

import java.util.*;

public class RentServiceImp implements RentService{

    RentRepositoryImp rentRepository = new RentRepositoryImp();

    @Override
    public void addElement(Rent rent) {
        rentRepository.addData(rent);
    }

    @Override
    public boolean checkBookRentedOrNot(UUID userId, UUID bookId) {
        for (Rent readDatum : rentRepository.readData()) {
            if(Objects.equals(readDatum.getUserId(), userId) && Objects.equals(readDatum.getBookId(), bookId) &&
                    Objects.equals(readDatum.getStatus(), Status.RENTED))
                return true;
        }
        return false;
    }

    @Override
    public ArrayList<UUID> getUsersBooks(UUID userId) {
        ArrayList<UUID> usersBookIds = new ArrayList<>();
        for (Rent readDatum : rentRepository.readData()) {
            if(Objects.equals(readDatum.getUserId(), userId) && Objects.equals(readDatum.getStatus(), Status.RENTED)){
                usersBookIds.add(readDatum.getBookId());
            }
        }
        return usersBookIds;
    }

    @Override
    public Rent getById(UUID bookId, UUID userId) {
        for (Rent readDatum : rentRepository.readData()) {
            if(Objects.equals(readDatum.getUserId(), userId) && Objects.equals(readDatum.getBookId(), bookId))
                return readDatum;
        }
        return null;
    }

    @Override
    public ArrayList<Rent> getAll() {
        return rentRepository.readData();
    }

    @Override
    public ArrayList<Rent> getAllWithoutDuplicates() {
        ArrayList<Rent> rents = new ArrayList<>();
        for (Rent readDatum : rentRepository.readData()) {
            boolean res = false;
            for(Rent r : rents){
                if (Objects.equals(readDatum.getBookId(), r.getBookId())) {
                    res = true;
                    break;
                }
            }
            if(!res && Objects.equals(readDatum.getStatus(), Status.RENTED)){
                rents.add(readDatum);
            }
        }
        return new ArrayList<>(rents);
    }

    @Override
    public void updateRentInfo(Rent rent) {
        ArrayList<Rent> rents = new ArrayList<>();
        for (Rent r : rentRepository.readData()) {
            if(Objects.equals(r.getBookId(), rent.getBookId()) && Objects.equals(r.getUserId(), rent.getUserId()))
                rents.add(rent);
            else
                rents.add(r);
        }
        rentRepository.updateData(rents);
    }
}
