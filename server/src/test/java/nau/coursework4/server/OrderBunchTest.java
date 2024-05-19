package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderBunchTest {

    @Test
    void getBunch() {
        Bunch bunch = new Bunch(123, List.of(new BunchProduct(321, 0, 0)));
        OrderBunch orderBunch = new OrderBunch(bunch, 0);
        assertEquals(bunch.getId(), orderBunch.getBunch().getId());
        assertEquals(bunch.getProducts().size(), orderBunch.getBunch().getProducts().size());
        assertEquals(bunch.getProducts().get(0).getId(), orderBunch.getBunch().getProducts().get(0).getId());
    }

    @Test
    void setBunch() {
        Bunch bunch = new Bunch(0, List.of(new BunchProduct(0, 0, 0)));
        OrderBunch orderBunch = new OrderBunch(bunch, 0);
        Bunch newBunch = new Bunch(123, List.of(new BunchProduct(321, 0, 0)));
        orderBunch.setBunch(newBunch);
        assertEquals(newBunch.getId(), orderBunch.getBunch().getId());
        assertEquals(newBunch.getProducts().size(), orderBunch.getBunch().getProducts().size());
        assertEquals(newBunch.getProducts().get(0).getId(), orderBunch.getBunch().getProducts().get(0).getId());
    }

    @Test
    void getQuantity() {
        OrderBunch orderBunch = new OrderBunch(new Bunch(0, List.of(new BunchProduct(0, 0, 0))), 123);
        assertEquals(123, orderBunch.getQuantity());
    }

    @Test
    void setQuantity() {
        OrderBunch orderBunch = new OrderBunch(new Bunch(0, List.of(new BunchProduct(0, 0, 0))), 0);
        orderBunch.setQuantity(123);
        assertEquals(123, orderBunch.getQuantity());
    }
}