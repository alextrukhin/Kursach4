package nau.coursework4.server;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BunchBuilderTest {

    @Test
    void build() {
        BunchBuilder bunchBuilder = new BunchBuilder();
        bunchBuilder
                .setId(0)
                .setProducts(List.of(new BunchProduct(1, 1, 1)));
        Bunch bunch = bunchBuilder.build();
        assertEquals(0, bunch.getId());
        assertEquals(1, bunch.getProducts().size());
    }
}