package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

/**
 * BunchProduct class is used to store information about product in the bunch
 */
public class BunchProduct {
    /**
     * Product id
     */
    private int id;

    /**
     * Product x coordinate
     */
    private int x;

    /**
     * Product y coordinate
     */
    private int y;

    /**
     * Default constructor
     */
    public BunchProduct() {
    }

    /**
     * Constructor with parameters
     *
     * @param id product id
     * @param x  product x coordinate
     * @param y  product y coordinate
     */
    public BunchProduct(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Get product id
     *
     * @return product id
     */
    @SerializedName("id")
    public int getId() {
        return id;
    }

    /**
     * Set product id
     *
     * @param id product id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get product x coordinate
     *
     * @return product x coordinate
     */
    @SerializedName("x")
    public int getX() {
        return x;
    }

    /**
     * Set product x coordinate
     *
     * @param x product x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get product y coordinate
     *
     * @return product y coordinate
     */
    @SerializedName("y")
    public int getY() {
        return y;
    }

    /**
     * Set product y coordinate
     *
     * @param y product y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
