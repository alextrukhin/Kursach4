package nau.coursework4.server;

import java.util.List;

public class OrderBuilder {
    private int id;
    private List<OrderProduct> products;
    private List<Bunch> bunches;
    private String status;
    private long createdAt;
    private long lastStatusChange;
    private String client_firstname;
    private String client_lastname;
    private String client_address;
    private String client_phone;
    private String client_email;
    private String client_comments;
    private Order.PaymentType payment_type;
    private Order.DeliveryType delivery_type;

    public OrderBuilder() {
    }

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

    public OrderBuilder setId(int id) {
        this.id = id;
        return this;
    }
    public OrderBuilder setProducts(List<OrderProduct> products) {
        this.products = products;
        return this;
    }
    public OrderBuilder setBunches(List<Bunch> bunches) {
        this.bunches = bunches;
        return this;
    }
    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }
    public OrderBuilder setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    public OrderBuilder setLastStatusChange(long lastStatusChange) {
        this.lastStatusChange = lastStatusChange;
        return this;
    }
    public OrderBuilder setClient_firstname(String client_firstname) {
        this.client_firstname = client_firstname;
        return this;
    }
    public OrderBuilder setClient_lastname(String client_lastname) {
        this.client_lastname = client_lastname;
        return this;
    }
    public OrderBuilder setClient_address(String client_address) {
        this.client_address = client_address;
        return this;
    }
    public OrderBuilder setClient_phone(String client_phone) {
        this.client_phone = client_phone;
        return this;
    }
    public OrderBuilder setClient_email(String client_email) {
        this.client_email = client_email;
        return this;
    }
    public OrderBuilder setClient_comments(String client_comments) {
        this.client_comments = client_comments;
        return this;
    }
    public OrderBuilder setPayment_type(Order.PaymentType payment_type) {
        this.payment_type = payment_type;
        return this;
    }
    public OrderBuilder setDelivery_type(Order.DeliveryType delivery_type) {
        this.delivery_type = delivery_type;
        return this;
    }
}
