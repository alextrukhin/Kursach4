package nau.coursework4.server;

/**
 * OrderProduct class is used to store information about product in the order
 */
public class OrderProduct {
    /**
     * Product id
     */
    private int productId;

    /**
     * Product quantity
     */
    private int quantity;

    /**
     * Default constructor
     */
    public OrderProduct() {
    }

    /**
     * Constructor with parameters
     *
     * @param productId product id
     * @param quantity  product quantity
     */
    public OrderProduct(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Get product id
     *
     * @return product id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Set product id
     *
     * @param productId product id
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Get product quantity
     *
     * @return product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set product quantity
     *
     * @param quantity product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
