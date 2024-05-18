package nau.coursework4.server;

public class ProductsStoreSingleton {
    private static ProductsStore instance;

    private ProductsStoreSingleton() {
    }

    public static ProductsStore getInstance() {
        if (instance == null) {
            instance = new ProductsStore();
        }
        return instance;
    }
}
