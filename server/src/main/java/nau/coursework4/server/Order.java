package nau.coursework4.server;

import java.util.List;

public class Order {
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

    public Order() {
    }

    public Order(int id, List<OrderProduct> products, List<Bunch> bunches, String status, long createdAt, long lastStatusChange, String client_firstname, String client_lastname, String client_address, String client_phone, String client_email, String client_comments) {
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
    }

    public int getId() {
        return id;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public List<Bunch> getBunches() {
        return bunches;
    }

    public String getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getLastStatusChange() {
        return lastStatusChange;
    }

    public String getClient_firstname() {
        return client_firstname;
    }

    public String getClient_lastname() {
        return client_lastname;
    }

    public String getClient_address() {
        return client_address;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public String getClient_email() {
        return client_email;
    }

    public String getClient_comments() {
        return client_comments;
    }
}
