package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrdersStoreTest {
    private final OrdersStore ordersStore = OrdersStoreSingleton.getInstance();
    private final ProductsStore productsStore = ProductsStoreSingleton.getInstance();

    @Test
    void getOrders() {
        List<Order> orders = ordersStore.getOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertInstanceOf(Order.class, orders.get(0));
    }

    @Test
    void getOrderById() {
        Order order = ordersStore.addOrder(new OrderBuilder()
                .setId(0)
                .setProducts(List.of(new OrderProduct(1, 1)))
                .setStatus("created")
                .setBunches(List.of(new OrderBunch(new Bunch(null, List.of(new BunchProduct(1, 1, 1))), 1)))
                .setClient_firstname("Test")
                .setClient_lastname("Test")
                .setClient_phone("Test")
                .setClient_email("support@crwnd.dev")
                .build());
        try {
            Order orderById = ordersStore.getOrderById(order.getId());

            assertNotNull(orderById);
            assertEquals(order.getId(), orderById.getId());
            assertEquals(order.getStatus(), orderById.getStatus());
            assertEquals(order.getClient_firstname(), orderById.getClient_firstname());
            assertEquals(order.getClient_lastname(), orderById.getClient_lastname());
            assertEquals(order.getClient_phone(), orderById.getClient_phone());
            assertEquals(order.getClient_email(), orderById.getClient_email());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            ordersStore.removeOrder(order.getId());
        }
    }

    @Test
    void addOrder() {
        Order order = ordersStore.addOrder(new OrderBuilder()
                .setId(0)
                .setProducts(List.of(new OrderProduct(1, 1)))
                .setStatus("created")
                .setBunches(List.of(new OrderBunch(new Bunch(null, List.of(new BunchProduct(1, 1, 1))), 1)))
                .setClient_firstname("Test")
                .setClient_lastname("Test")
                .setClient_phone("Test")
                .setClient_email("support@crwnd.dev")
                .build());
        try {
            assertNotNull(order);
            assertEquals("created", order.getStatus());
            assertEquals("Test", order.getClient_firstname());
            assertEquals("Test", order.getClient_lastname());
            assertEquals("Test", order.getClient_phone());
            assertEquals("support@crwnd.dev", order.getClient_email());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            ordersStore.removeOrder(order.getId());
        }
    }

    @Test
    void updateOrder() {
        Order order = ordersStore.addOrder(new OrderBuilder()
                .setId(0)
                .setProducts(List.of(new OrderProduct(1, 1)))
                .setStatus("created")
                .setBunches(List.of(new OrderBunch(new Bunch(null, List.of(new BunchProduct(1, 1, 1))), 1)))
                .setClient_firstname("Test")
                .setClient_lastname("Test")
                .setClient_phone("Test")
                .setClient_email("support@crwnd.dev")
                .build());
        try {
            Order updatedOrder = ordersStore.updateOrder(new OrderBuilder(order)
                    .setStatus("updated")
                    .setClient_firstname("Updated")
                    .setClient_lastname("Updated")
                    .setClient_phone("Updated")
                    .setClient_email("support@crwnd.dev")
                    .build());
            assertNotNull(updatedOrder);
            assertEquals("updated", updatedOrder.getStatus());
            assertEquals("Updated", updatedOrder.getClient_firstname());
            assertEquals("Updated", updatedOrder.getClient_lastname());
            assertEquals("Updated", updatedOrder.getClient_phone());
            assertEquals("support@crwnd.dev", updatedOrder.getClient_email());
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            ordersStore.removeOrder(order.getId());
        }
    }

    @Test
    void removeOrder() {
        Order order = ordersStore.addOrder(new OrderBuilder()
                .setId(0)
                .setProducts(List.of(new OrderProduct(1, 1)))
                .setStatus("created")
                .setBunches(List.of(new OrderBunch(new Bunch(null, List.of(new BunchProduct(1, 1, 1))), 1)))
                .setClient_firstname("Test")
                .setClient_lastname("Test")
                .setClient_phone("Test")
                .setClient_email("support@crwnd.dev")
                .build());
        ordersStore.removeOrder(order.getId());
        assertNull(ordersStore.getOrderById(order.getId()));
    }
}