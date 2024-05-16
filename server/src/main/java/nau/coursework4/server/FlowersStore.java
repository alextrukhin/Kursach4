package nau.coursework4.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FlowersStore {
    List<Flower> data = new ArrayList<Flower>();
    public FlowersStore() {
        data = readFromFile("products.json");
    }

    public List<Flower> getProducts() {
        return data;
    }
    public Flower getProductById(Integer id) {
        for (Flower product : data) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public void addProduct(Flower product) {
        data.add(product);
        saveListToFile(data, "products.json");
    }
    public void updateProduct(Flower product) {
        data.removeIf(p -> p.getId() == product.getId());
        data.add(product);
        saveListToFile(data, "products.json");
    }
    public void removeProduct(Integer id) {
        data.removeIf(product -> product.getId() == id);
        saveListToFile(data, "products.json");
    }

    private List<Flower> readFromFile(String fileName) {
        Type REVIEW_TYPE = new TypeToken<List<Flower>>() {
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
    private void saveListToFile(List<Flower> list, String fileName) {
        try (Writer writer = new FileWriter("products.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
