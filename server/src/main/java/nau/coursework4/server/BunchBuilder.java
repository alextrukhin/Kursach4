package nau.coursework4.server;

import java.util.List;

public class BunchBuilder {
    private int id;
    private List<BunchProduct> products;

    public BunchBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public BunchBuilder setProducts(List<BunchProduct> products) {
        this.products = products;
        return this;
    }

    public Bunch build() {
        return new Bunch(id, products);
    }
}
