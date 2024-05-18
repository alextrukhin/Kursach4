package nau.coursework4.server;

import jakarta.annotation.Nullable;

import java.util.List;

public class Bunch {
    private Integer id;
    private List<BunchProduct> products;

    public Bunch() {
    }
    public Bunch(@Nullable Integer id, List<BunchProduct> products) {
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
