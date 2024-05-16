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
    List<Product> data = new ArrayList<Product>();
    public FlowersStore() {
        data = readFromFile("products.json");
    }

    public List<Product> getProducts() {
        return data;
    }
    public Product getProductById(Integer id) {
        for (Product product : data) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    public void addProduct(Product product) {
        data.add(product);
        saveListToFile(data, "products.json");
    }
    public void updateProduct(Product product) {
        data.removeIf(p -> p.getId() == product.getId());
        data.add(product);
        saveListToFile(data, "products.json");
    }
    public void removeProduct(Integer id) {
        data.removeIf(product -> product.getId() == id);
        saveListToFile(data, "products.json");
    }

    private List<Product> readFromFile(String fileName) {
        Type REVIEW_TYPE = new TypeToken<List<Product>>() {
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
    private void saveListToFile(List<Product> list, String fileName) {
        try (Writer writer = new FileWriter("products.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
