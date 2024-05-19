package nau.coursework4.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductsStore class is used to store information about products
 */
public class ProductsStore {
    /**
     * List of products
     */
    List<Product> data = new ArrayList<Product>();

    /**
     * Default constructor
     */
    public ProductsStore() {
        data = readFromFile("products.json");
    }

    /**
     * Get list of products
     *
     * @return list of products
     */
    public List<Product> getProducts() {
        return data;
    }

    /**
     * Get product by id
     *
     * @param id product id
     * @return product
     */
    public Product getProductById(Integer id) {
        for (Product product : data) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    /**
     * Add product
     *
     * @param product product
     * @return added product
     */
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

    /**
     * Update product
     *
     * @param product product
     * @return updated product
     */
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

    /**
     * Remove product
     *
     * @param id     product id
     * @param orders list of orders
     */
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

    /**
     * Read list of products from file
     *
     * @param fileName file name
     * @return list of products
     */
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

    /**
     * Save list of products to file
     *
     * @param list     list of products
     * @param fileName file name
     */
    private void saveListToFile(List<Product> list, String fileName) {
        try (Writer writer = new FileWriter("products.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
