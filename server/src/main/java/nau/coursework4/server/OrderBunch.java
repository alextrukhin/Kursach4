package nau.coursework4.server;

/**
 * OrderBunch class is used to store information about bunch in the order
 */
public class OrderBunch {
    /**
     * Bunch object
     */
    private Bunch bunch;

    /**
     * Bunch quantity
     */
    private int quantity;

    /**
     * Default constructor
     */
    public OrderBunch() {
    }

    /**
     * Constructor with parameters
     *
     * @param bunch    bunch object
     * @param quantity bunch quantity
     */
    public OrderBunch(Bunch bunch, int quantity) {
        this.bunch = bunch;
        this.quantity = quantity;
    }

    /**
     * Get bunch object
     *
     * @return bunch object
     */
    public Bunch getBunch() {
        return bunch;
    }

    /**
     * Set bunch object
     *
     * @param bunch bunch object
     */
    public void setBunch(Bunch bunch) {
        this.bunch = bunch;
    }

    /**
     * Get bunch quantity
     *
     * @return bunch quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set bunch quantity
     *
     * @param quantity bunch quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
