package nau.coursework4.server;

/**
 * ProductsStoreSingleton class is used to create a single instance of ProductsStore
 */
public class ProductsStoreSingleton {
    /**
     * Single instance of ProductsStore
     */
    private static ProductsStore instance;

    /**
     * Private constructor
     */
    private ProductsStoreSingleton() {
    }

    /**
     * Get single instance of ProductsStore
     *
     * @return single instance of ProductsStore
     */
    public static ProductsStore getInstance() {
        if (instance == null) {
            instance = new ProductsStore();
        }
        return instance;
    }
}
