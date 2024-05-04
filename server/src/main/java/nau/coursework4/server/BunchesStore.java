package nau.coursework4.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BunchesStore {
    List<Bunch> data = new ArrayList<Bunch>();
    public BunchesStore() {
        data = readFromFile("bunches.json");
    }

    public List<Bunch> getProducts() {
        return data;
    }
    public void addBunch(Bunch product) {
        data.add(product);
        saveListToFile(data, "bunches.json");
    }
    public void updateBunch(Bunch product) {
        data.removeIf(p -> p.getId() == product.getId());
        data.add(product);
        saveListToFile(data, "bunches.json");
    }
    public void removeBunch(Integer id) {
        data.removeIf(product -> product.getId() == id);
        saveListToFile(data, "bunches.json");
    }

    private List<Bunch> readFromFile(String fileName) {
        Type REVIEW_TYPE = new TypeToken<List<Bunch>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            saveListToFile(data, fileName);
            return data;
        }
        return gson.fromJson(reader, REVIEW_TYPE);
    }
    private void saveListToFile(List<Bunch> list, String fileName) {
        try (Writer writer = new FileWriter("bunches.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
