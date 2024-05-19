package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderProductTest {

    @Test
    void getProductId() {
        OrderProduct orderProduct = new OrderProduct(123, 0);
        assertEquals(123, orderProduct.getProductId());
    }

    @Test
    void setProductId() {
        OrderProduct orderProduct = new OrderProduct(0, 0);
        orderProduct.setProductId(123);
        assertEquals(123, orderProduct.getProductId());
    }

    @Test
    void getQuantity() {
        OrderProduct orderProduct = new OrderProduct(0, 123);
        assertEquals(123, orderProduct.getQuantity());
    }

    @Test
    void setQuantity() {
        OrderProduct orderProduct = new OrderProduct(0, 0);
        orderProduct.setQuantity(123);
        assertEquals(123, orderProduct.getQuantity());
    }
}