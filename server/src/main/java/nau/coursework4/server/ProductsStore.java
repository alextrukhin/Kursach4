package nau.coursework4.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductsStore {
    List<Product> data = new ArrayList<Product>();
    public ProductsStore() {
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
    public Product addProduct(Product product) {
        int highestId = 0;
        for (Product p : data) {
            if (p.getId() > highestId) {
                highestId = p.getId();
            }
        }
        Product newProduct = new ProductBuilder(product)
                .setId(highestId + 1)
                .build();
        data.add(newProduct);
        saveListToFile(data, "products.json");
        return newProduct;
    }
    public Product updateProduct(Product product) {
        Integer index = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == product.getId()) {
                index = i;
                break;
            }
        }
        if (index == null) {
            throw new RuntimeException("Product not found");
        }
        data.set(index, product);
        saveListToFile(data, "products.json");
        return product;
    }
    public void removeProduct(Integer id, List<Order> orders) {
        for (Order order : orders) {
            for (OrderProduct orderProduct : order.getProducts()) {
                if (orderProduct.getProductId() == id) {
                    throw new RuntimeException("Product is in use");
                }
            }
        }
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
