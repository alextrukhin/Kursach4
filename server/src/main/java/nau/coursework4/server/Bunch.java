package nau.coursework4.server;

import java.util.List;

public class Bunch {
    private int id;
    private List<BunchProduct> products;

    public Bunch() {
    }
    public Bunch(int id, List<BunchProduct> products) {
        this.id = id;
        this.products = products;
    }

    public int getId() {
        return id;
    }
    public List<BunchProduct> getProducts() {
        return products;
    }
    public void setProducts(List<BunchProduct> products) {
        this.products = products;
    }
}
