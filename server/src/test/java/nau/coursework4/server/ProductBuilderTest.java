package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductBuilderTest {

    @Test
    void build() {
        ProductBuilder productBuilder = new ProductBuilder();
        productBuilder
                .setId(0)
                .setName("Test")
                .setColor("Orange")
                .setType("Flower")
                .setDescription("Test description")
                .setPrice(100)
                .setSeasoning("summer")
                .setImage("Test image")
                .setImageSingle("Test image single");
        Product product = productBuilder.build();
        assertEquals(0, product.getId());
        assertEquals("Test", product.getName());
        assertEquals("Orange", product.getColor());
        assertEquals(Product.Type.Flower, product.getType());
        assertEquals("Test description", product.getDescription());
        assertEquals(100, product.getPrice());
        assertEquals("summer", product.getSeasoning());
        assertEquals("Test image", product.getImage());
        assertEquals("Test image single", product.getImageSingle());
    }
}