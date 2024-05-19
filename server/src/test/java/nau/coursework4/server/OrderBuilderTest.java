package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderBuilderTest {

    @Test
    void build() {
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder
                .setId(0)
                .setProducts(List.of(new OrderProduct(1, 1)))
                .setBunches(List.of(new OrderBunch(new Bunch(null, List.of(new BunchProduct(1, 1, 1))), 1)))
                .setStatus("created")
                .setCreatedAt(123)
                .setLastStatusChange(321)
                .setClient_firstname("Firstname")
                .setClient_lastname("Lastname")
                .setClient_address("м.Київ, просп. Гузара Любомира 1")
                .setClient_phone("+380970000000")
                .setClient_email("support@crwnd.dev")
                .setClient_comments("Test comment")
                .setPayment_type(Order.PaymentType.card)
                .setDelivery_type(Order.DeliveryType.delivery);
        Order order = orderBuilder.build();
        assertEquals(0, order.getId());
        assertEquals(1, order.getProducts().size());
        assertEquals(1, order.getProducts().get(0).getProductId());
        assertEquals(1, order.getProducts().get(0).getQuantity());
        assertEquals(1, order.getBunches().size());
        assertEquals(1, order.getBunches().get(0).getQuantity());
        assertEquals("created", order.getStatus());
        assertEquals(123, order.getCreatedAt());
        assertEquals(321, order.getLastStatusChange());
        assertEquals("Firstname", order.getClient_firstname());
        assertEquals("Lastname", order.getClient_lastname());
        assertEquals("м.Київ, просп. Гузара Любомира 1", order.getClient_address());
        assertEquals("+380970000000", order.getClient_phone());
        assertEquals("support@crwnd.dev", order.getClient_email());
        assertEquals("Test comment", order.getClient_comments());
        assertEquals(Order.PaymentType.card, order.getPayment_type());
        assertEquals(Order.DeliveryType.delivery, order.getDelivery_type());
    }
}