package nau.coursework4.server;

import java.util.List;

/**
 * OrderBuilder class is used to build Order objects
 */
public class OrderBuilder {
    /**
     * Order id
     */
    private int id;

    /**
     * List of products in the order
     */
    private List<OrderProduct> products;

    /**
     * List of bunches in the order
     */
    private List<OrderBunch> bunches;

    /**
     * Order status
     */
    private String status;

    /**
     * Order creation date
     */
    private long createdAt;

    /**
     * Order last status change date
     */
    private long lastStatusChange;

    /**
     * Client first name
     */
    private String client_firstname;

    /**
     * Client last name
     */
    private String client_lastname;

    /**
     * Client address
     */
    private String client_address;

    /**
     * Client phone
     */
    private String client_phone;

    /**
     * Client email
     */
    private String client_email;

    /**
     * Client comments
     */
    private String client_comments;

    /**
     * Payment type
     */
    private Order.PaymentType payment_type;

    /**
     * Delivery type
     */
    private Order.DeliveryType delivery_type;

    /**
     * Default constructor
     */
    public OrderBuilder() {
    }

    /**
     * Constructor with parameters
     *
     * @param order order object
     */
    public OrderBuilder(Order order) {
        this.id = order.getId();
        this.products = order.getProducts();
        this.bunches = order.getBunches();
        this.status = order.getStatus();
        this.createdAt = order.getCreatedAt();
        this.lastStatusChange = order.getLastStatusChange();
        this.client_firstname = order.getClient_firstname();
        this.client_lastname = order.getClient_lastname();
        this.client_address = order.getClient_address();
        this.client_phone = order.getClient_phone();
        this.client_email = order.getClient_email();
        this.client_comments = order.getClient_comments();
        this.payment_type = order.getPayment_type();
        this.delivery_type = order.getDelivery_type();
    }

    /**
     * Build Order object
     *
     * @return Order object
     */
    public Order build() {
        return new Order(id,
                products,
                bunches,
                status,
                createdAt,
                lastStatusChange,
                client_firstname,
                client_lastname,
                client_address,
                client_phone,
                client_email,
                client_comments,
                payment_type,
                delivery_type);
    }

    /**
     * Set order id
     *
     * @param id order id
     * @return OrderBuilder object
     */
    public OrderBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Set list of products in the order
     *
     * @param products list of products in the order
     * @return OrderBuilder object
     */
    public OrderBuilder setProducts(List<OrderProduct> products) {
        this.products = products;
        return this;
    }

    /**
     * Set list of bunches in the order
     *
     * @param bunches list of bunches in the order
     * @return OrderBuilder object
     */
    public OrderBuilder setBunches(List<OrderBunch> bunches) {
        this.bunches = bunches;
        return this;
    }

    /**
     * Set order status
     *
     * @param status order status
     * @return OrderBuilder object
     */
    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Set order creation date
     *
     * @param createdAt order creation date
     * @return OrderBuilder object
     */
    public OrderBuilder setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Set order last status change date
     *
     * @param lastStatusChange order last status change date
     * @return OrderBuilder object
     */
    public OrderBuilder setLastStatusChange(long lastStatusChange) {
        this.lastStatusChange = lastStatusChange;
        return this;
    }

    /**
     * Set client first name
     *
     * @param client_firstname client first name
     * @return OrderBuilder object
     */
    public OrderBuilder setClient_firstname(String client_firstname) {
        this.client_firstname = client_firstname;
        return this;
    }

    /**
     * Set client last name
     *
     * @param client_lastname client last name
     * @return OrderBuilder object
     */
    public OrderBuilder setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
        return this;
    }

    /**
     * Set client address
     *
     * @param client_address client address
     * @return OrderBuilder object
     */
    public OrderBuilder setClient_address(String client_address) {
        this.client_address = client_address;
        return this;
    }

    /**
     * Set client phone
     *
     * @param client_phone client phone
     * @return OrderBuilder object
     */
    public OrderBuilder setClient_phone(String client_phone) {
        this.client_phone = client_phone;
        return this;
    }

    /**
     * Set client email
     *
     * @param client_email client email
     * @return OrderBuilder object
     */
    public OrderBuilder setClient_email(String client_email) {
        this.client_email = client_email;
        return this;
    }

    /**
     * Set client comments
     *
     * @param client_comments client comments
     * @return OrderBuilder object
     */
    public OrderBuilder setClient_comments(String client_comments) {
        this.client_comments = client_comments;
        return this;
    }

    /**
     * Set payment type
     *
     * @param payment_type payment type
     * @return OrderBuilder object
     */
    public OrderBuilder setPayment_type(Order.PaymentType payment_type) {
        this.payment_type = payment_type;
        return this;
    }

    /**
     * Set delivery type
     *
     * @param delivery_type delivery type
     * @return OrderBuilder object
     */
    public OrderBuilder setDelivery_type(Order.DeliveryType delivery_type) {
        this.delivery_type = delivery_type;
        return this;
    }
}
