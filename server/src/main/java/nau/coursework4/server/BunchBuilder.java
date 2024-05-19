package nau.coursework4.server;

import java.util.List;

/**
 * BunchBuilder class is used to build Bunch objects
 */
public class BunchBuilder {
    /**
     * Bunch id
     */
    private int id;

    /**
     * List of products in the bunch
     */
    private List<BunchProduct> products;

    /**
     * Default constructor
     */
    public BunchBuilder() {
    }

    /**
     * Set bunch id
     *
     * @param id bunch id
     * @return BunchBuilder object
     */
    public BunchBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Set list of products in the bunch
     *
     * @param products list of products in the bunch
     * @return BunchBuilder object
     */
    public BunchBuilder setProducts(List<BunchProduct> products) {
        this.products = products;
        return this;
    }

    /**
     * Build Bunch object
     *
     * @return Bunch object
     */
    public Bunch build() {
        return new Bunch(id, products);
    }
}
