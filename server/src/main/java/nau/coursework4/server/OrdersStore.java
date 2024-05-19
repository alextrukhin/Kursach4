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
 * OrdersStore class is used to store information about orders
 */
public class OrdersStore {
    /**
     * List of orders
     */
    List<Order> data = new ArrayList<Order>();

    /**
     * Default constructor
     */
    public OrdersStore() {
        data = readFromFile("orders.json");
    }

    /**
     * Get list of orders
     *
     * @return list of orders
     */
    public List<Order> getOrders() {
        return data;
    }

    /**
     * Get order by id
     *
     * @param id order id
     * @return order
     */
    public Order getOrderById(Integer id) {
        for (Order order : data) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    /**
     * Add order
     *
     * @param order order
     * @return added order
     */
    public Order addOrder(Order order) {
        int highestId = 0;
        for (Order o : data) {
            if (o.getId() > highestId) {
                highestId = o.getId();
            }
        }
        Order orderAdded = new OrderBuilder(order).setId(highestId + 1).build();
        data.add(orderAdded);
        saveListToFile(data, "orders.json");
        return orderAdded;
    }

    /**
     * Update order
     *
     * @param order order
     * @return updated order
     */
    public Order updateOrder(Order order) {
        Integer index = null;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == order.getId()) {
                index = i;
                break;
            }
        }
        if (index == null) {
            throw new RuntimeException("Order not found");
        }
        data.set(index, order);
        saveListToFile(data, "orders.json");
        return order;
    }

    /**
     * Remove order
     *
     * @param id order id
     */
    public void removeOrder(Integer id) {
        data.removeIf(order -> order.getId() == id);
        saveListToFile(data, "orders.json");
    }

    /**
     * Read list of orders from file
     *
     * @param fileName file name
     * @return list of orders
     */
    private List<Order> readFromFile(String fileName) {
        Type REVIEW_TYPE = new TypeToken<List<Order>>() {
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
     * Save list of orders to file
     *
     * @param list     list of orders
     * @param fileName file name
     */
    private void saveListToFile(List<Order> list, String fileName) {
        try (Writer writer = new FileWriter("orders.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
