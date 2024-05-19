package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BunchProductTest {

    @Test
    void getId() {
        BunchProduct bunchProduct = new BunchProduct(123, 0, 0);
        assertEquals(123, bunchProduct.getId());
    }

    @Test
    void setId() {
        BunchProduct bunchProduct = new BunchProduct(0, 0, 0);
        bunchProduct.setId(123);
        assertEquals(123, bunchProduct.getId());
    }

    @Test
    void getX() {
        BunchProduct bunchProduct = new BunchProduct(0, 123, 0);
        assertEquals(123, bunchProduct.getX());
    }

    @Test
    void setX() {
        BunchProduct bunchProduct = new BunchProduct(0, 0, 0);
        bunchProduct.setX(123);
        assertEquals(123, bunchProduct.getX());
    }

    @Test
    void getY() {
        BunchProduct bunchProduct = new BunchProduct(0, 0, 123);
        assertEquals(123, bunchProduct.getY());
    }

    @Test
    void setY() {
        BunchProduct bunchProduct = new BunchProduct(0, 0, 0);
        bunchProduct.setY(123);
        assertEquals(123, bunchProduct.getY());
    }
}