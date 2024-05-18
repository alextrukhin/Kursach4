package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BunchesStoreTest {
    private final FlowersStore flowersStore = FlowersStoreSingleton.getInstance();

    @Test
    void getProducts() {
        List<Product> products = flowersStore.getProducts();
        assertNotNull(products);
    }

    @Test
    void addBunch() {
    }

    @Test
    void updateBunch() {
    }

    @Test
    void removeBunch() {
    }
}