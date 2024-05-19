package nau.coursework4.server;

import jakarta.annotation.Nullable;

import java.util.List;

/**
 * Bunch class represents a bunch of flowers
 */
public class Bunch {
    /**
     * Bunch id
     */
    private Integer id;

    /**
     * List of products in the bunch
     */
    private List<BunchProduct> products;

    /**
     * Default constructor
     */
    public Bunch() {
    }

    /**
     * Constructor with parameters
     *
     * @param id       bunch id
     * @param products list of products in the bunch
     */
    public Bunch(@Nullable Integer id, List<BunchProduct> products) {
        this.id = id;
        this.products = products;
    }

    /**
     * Get bunch id
     *
     * @return bunch id
     */
    public int getId() {
        return id;
    }

    /**
     * Get list of products in the bunch
     *
     * @return list of products in the bunch
     */
    public List<BunchProduct> getProducts() {
        return products;
    }
}
