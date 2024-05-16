package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.List;

public class Order {
    public enum DeliveryType {
        postman, pickup, delivery
    }
    public enum PaymentType {
        cash, card
    }
    private int id;
    private LinkedHashMap<Number, OrderProduct> products;
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
    private PaymentType payment_type;
    private DeliveryType delivery_type;


    public Order() {
    }

    public Order(int id,
                 LinkedHashMap<Number, OrderProduct> products,
                 LinkedHashMap<Number, Bunch> bunches,
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

    @SerializedName("id")
    public int getId() {
        return id;
    }

    @SerializedName("products")
    public List<OrderProduct> getProducts() {
        return products instanceof LinkedHashMap<?,?> ? products.values() : null;
        return products;
    }

    @SerializedName("bunches")
    public List<Bunch> getBunches() {
        return bunches;
    }

    @SerializedName("status")
    public String getStatus() {
        return status;
    }

    @SerializedName("createdAt")
    public long getCreatedAt() {
        return createdAt;
    }

    @SerializedName("lastStatusChange")
    public long getLastStatusChange() {
        return lastStatusChange;
    }

    @SerializedName("client_firstname")
    public String getClient_firstname() {
        return client_firstname;
    }

    @SerializedName("client_lastname")
    public String getClient_lastname() {
        return client_lastname;
    }

    @SerializedName("client_address")
    public String getClient_address() {
        return client_address;
    }

    @SerializedName("client_phone")
    public String getClient_phone() {
        return client_phone;
    }

    @SerializedName("client_email")
    public String getClient_email() {
        return client_email;
    }

    @SerializedName("client_comments")
    public String getClient_comments() {
        return client_comments;
    }

    @SerializedName("payment_type")
    public PaymentType getPayment_type() {
        return payment_type;
    }

    @SerializedName("delivery_type")
    public DeliveryType getDelivery_type() {
        return delivery_type;
    }
}
