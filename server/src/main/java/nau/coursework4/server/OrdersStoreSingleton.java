package nau.coursework4.server;

/**
 * OrdersStoreSingleton class is used to create a single instance of OrdersStore
 */
public class OrdersStoreSingleton {
    /**
     * Single instance of OrdersStore
     */
    private static OrdersStore instance;

    /**
     * Private constructor
     */
    private OrdersStoreSingleton() {
    }

    /**
     * Get single instance of OrdersStore
     *
     * @return single instance of OrdersStore
     */
    public static OrdersStore getInstance() {
        if (instance == null) {
            instance = new OrdersStore();
        }
        return instance;
    }
}
