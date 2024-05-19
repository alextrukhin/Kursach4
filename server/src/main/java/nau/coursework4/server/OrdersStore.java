package nau.coursework4.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OrdersStore {
    List<Order> data = new ArrayList<Order>();
    public OrdersStore() {
        data = readFromFile("orders.json");
    }

    public List<Order> getOrders() {
        return data;
    }
    public Order getOrderById(Integer id) {
        for (Order order : data) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
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
    public void removeOrder(Integer id) {
        data.removeIf(order -> order.getId() == id);
        saveListToFile(data, "orders.json");
    }

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
    private void saveListToFile(List<Order> list, String fileName) {
        try (Writer writer = new FileWriter("orders.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(list, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
