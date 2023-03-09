package uz.pdp.library.repository;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.pdp.library.model.Book;
import uz.pdp.library.model.Rent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookRepositoryImp implements BookRepository{
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    @Override
    public void addData(Book book) {
        ArrayList<Book> books = readData();
        books.add(book);
        try(FileWriter writer = new FileWriter("Book.json")){
            gson.toJson(books, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Book> readData() {
        ArrayList<Book> books = new ArrayList<>();
        try(FileReader reader = new FileReader("Book.json")){
            Type type = new TypeToken<ArrayList<Book>>(){}.getType();
            books = gson.fromJson(reader, type);
        } catch (IOException e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void updateInfo(ArrayList<Book> books) {
        try(FileWriter writer = new FileWriter("Book.json")){
            gson.toJson(books, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
