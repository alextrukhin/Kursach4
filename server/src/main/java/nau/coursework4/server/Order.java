package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Order class represents an order
 */
public class Order {
    /**
     * Delivery type
     */
    public enum DeliveryType {
        postman, pickup, delivery
    }

    /**
     * Payment type
     */
    public enum PaymentType {
        cash, card
    }

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
    private PaymentType payment_type;

    /**
     * Delivery type
     */
    private DeliveryType delivery_type;

    /**
     * Default constructor
     */
    public Order() {
    }

    /**
     * Constructor with parameters
     *
     * @param id               order id
     * @param products         list of products in the order
     * @param bunches          list of bunches in the order
     * @param status           order status
     * @param createdAt        order creation date
     * @param lastStatusChange order last status change date
     * @param client_firstname client first name
     * @param client_lastname  client last name
     * @param client_address   client address
     * @param client_phone     client phone
     * @param client_email     client email
     * @param client_comments  client comments
     * @param payment_type     payment type
     * @param delivery_type    delivery type
     */
    public Order(int id,
                 List<OrderProduct> products,
                 List<OrderBunch> bunches,
                 String status,
                 long createdAt,
                 long lastStatusChange,
                 String client_firstname,
                 String client_lastname,
                 String client_address,
                 String client_phone,
                 String client_email,
                 String client_comments,
                 PaymentType payment_type,
                 DeliveryType delivery_type) {
        this.id = id;
        this.products = products;
        this.bunches = bunches;
        this.status = status;
        this.createdAt = createdAt;
        this.lastStatusChange = lastStatusChange;
        this.client_firstname = client_firstname;
        this.client_lastname = client_lastname;
        this.client_address = client_address;
        this.client_phone = client_phone;
        this.client_email = client_email;
        this.client_comments = client_comments;
        this.payment_type = payment_type;
        this.delivery_type = delivery_type;
    }

    /**
     * Get order id
     *
     * @return order id
     */
    @SerializedName("id")
    public int getId() {
        return id;
    }

    /**
     * Get list of products in the order
     *
     * @return list of products in the order
     */
    @SerializedName("products")
    public List<OrderProduct> getProducts() {
        return products;
    }

    /**
     * Get list of bunches in the order
     *
     * @return list of bunches in the order
     */
    @SerializedName("bunches")
    public List<OrderBunch> getBunches() {
        return bunches;
    }

    /**
     * Get order status
     *
     * @return order status
     */
    @SerializedName("status")
    public String getStatus() {
        return status;
    }

    /**
     * Get order creation date
     *
     * @return order creation date
     */
    @SerializedName("createdAt")
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Get order last status change date
     *
     * @return order last status change date
     */
    @SerializedName("lastStatusChange")
    public long getLastStatusChange() {
        return lastStatusChange;
    }

    /**
     * Get client first name
     *
     * @return client first name
     */
    @SerializedName("client_firstname")
    public String getClient_firstname() {
        return client_firstname;
    }

    /**
     * Get client last name
     *
     * @return client last name
     */
    @SerializedName("client_lastname")
    public String getClient_lastname() {
        return client_lastname;
    }

    /**
     * Get client address
     *
     * @return client address
     */
    @SerializedName("client_address")
    public String getClient_address() {
        return client_address;
    }

    /**
     * Get client phone
     *
     * @return client phone
     */
    @SerializedName("client_phone")
    public String getClient_phone() {
        return client_phone;
    }

    /**
     * Get client email
     *
     * @return client email
     */
    @SerializedName("client_email")
    public String getClient_email() {
        return client_email;
    }

    /**
     * Get client comments
     *
     * @return client comments
     */
    @SerializedName("client_comments")
    public String getClient_comments() {
        return client_comments;
    }

    /**
     * Get payment type
     *
     * @return payment type
     */
    @SerializedName("payment_type")
    public PaymentType getPayment_type() {
        return payment_type;
    }

    /**
     * Get delivery type
     *
     * @return delivery type
     */
    @SerializedName("delivery_type")
    public DeliveryType getDelivery_type() {
        return delivery_type;
    }
}
