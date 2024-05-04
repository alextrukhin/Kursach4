package nau.coursework4.server;

public class OrdersStoreSingleton {
    private static OrdersStore instance;

    private OrdersStoreSingleton() {
    }

    public static OrdersStore getInstance() {
        if (instance == null) {
            instance = new OrdersStore();
        }
        return instance;
    }
}
