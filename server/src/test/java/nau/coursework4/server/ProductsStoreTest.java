package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductsStoreTest {
    private final ProductsStore productsStore = ProductsStoreSingleton.getInstance();
    private final OrdersStore ordersStore = OrdersStoreSingleton.getInstance();

    @Test
    void testGetProducts() {
        List<Product> products = productsStore.getProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertInstanceOf(Product.class, products.get(0));
    }

    @Test
    void getProductById() {
        Product product = productsStore.addProduct(new ProductBuilder()
                .setId(0)
                .setName("Test")
                .setColor("Test")
                .setType("Flower")
                .setDescription("Test")
                .setPrice(0)
                .setSeasoning("Test")
                .setImage("Test")
                .setImageSingle("Test")
                .build());
        try{
            Product productById = productsStore.getProductById(product.getId());

            assertNotNull(productById);
            assertEquals(product.getId(), productById.getId());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            productsStore.removeProduct(product.getId(), ordersStore.getOrders());
        }
    }

    @Test
    void addProduct() {
        Product product = productsStore.addProduct(new ProductBuilder()
                .setId(0)
                .setName("Test")
                .setColor("Test")
                .setType("Flower")
                .setDescription("Test")
                .setPrice(0)
                .setSeasoning("Test")
                .setImage("Test")
                .setImageSingle("Test")
                .build());
        assertNotNull(product);
        assertEquals("Test", product.getName());
        assertEquals("Test", product.getColor());
        assertEquals(Product.Type.Flower, product.getType());
        assertEquals("Test", product.getDescription());
        assertEquals(0, product.getPrice());
        assertEquals("Test", product.getSeasoning());
        assertEquals("Test", product.getImage());
        assertEquals("Test", product.getImageSingle());
        productsStore.removeProduct(product.getId(), ordersStore.getOrders());
    }

    @Test
    void updateProduct() {
        Product product = productsStore.addProduct(new ProductBuilder()
                .setId(0)
                .setName("Test")
                .setColor("Test")
                .setType("Flower")
                .setDescription("Test")
                .setPrice(0)
                .setSeasoning("Test")
                .setImage("Test")
                .setImageSingle("Test")
                .build());
        try {
            Product updatedProduct = productsStore.updateProduct(new ProductBuilder(product)
                    .setName("Updated")
                    .setColor("Updated")
                    .setType("Bouquet")
                    .setDescription("Updated")
                    .setPrice(1)
                    .setSeasoning("Updated")
                    .setImage("Updated")
                    .setImageSingle("Updated")
                    .build());
            assertEquals("Updated", updatedProduct.getName());
            assertEquals("Updated", updatedProduct.getColor());
            assertEquals(Product.Type.Bouquet, updatedProduct.getType());
            assertEquals("Updated", updatedProduct.getDescription());
            assertEquals(1, updatedProduct.getPrice());
            assertEquals("Updated", updatedProduct.getSeasoning());
            assertEquals("Updated", updatedProduct.getImage());
            assertEquals("Updated", updatedProduct.getImageSingle());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            productsStore.removeProduct(product.getId(), ordersStore.getOrders());
        }
    }

    @Test
    void removeProduct() {
        Product product = productsStore.addProduct(new ProductBuilder()
                .setId(0)
                .setName("Test")
                .setColor("Test")
                .setType("Flower")
                .setDescription("Test")
                .setPrice(0)
                .setSeasoning("Test")
                .setImage("Test")
                .setImageSingle("Test")
                .build());
        productsStore.removeProduct(product.getId(), ordersStore.getOrders());
        assertNull(productsStore.getProductById(product.getId()));
    }
}