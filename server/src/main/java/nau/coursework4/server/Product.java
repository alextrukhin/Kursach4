package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

public class Product {
    private int id;
    private String name;
    private String color;

    public enum Type {
        Flower, Bouquet
    }

    private Type type;
    private String description;
    private double price;
    private String seasoning;
    private String image;
    private String image_single;

    public Product() {
    }

    public Product(int id, String name, String color, Type type, String description, double price, String seasoning, String image, String image_single) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.type = type;
        this.description = description;
        this.price = price;
        this.seasoning = seasoning;
        this.image = image;
        this.image_single = image_single;
    }

    @SerializedName("id")
    public int getId() {
        return id;
    }

    @SerializedName("name")
    public String getName() {
        return name;
    }

    @SerializedName("color")
    public String getColor() {
        return color;
    }

    @SerializedName("type")
    public Type getType() {
        return type;
    }

    @SerializedName("description")
    public String getDescription() {
        return description;
    }

    @SerializedName("price")
    public double getPrice() {
        return price;
    }

    @SerializedName("seasoning")
    public String getSeasoning() {
        return seasoning;
    }

    @SerializedName("image")
    public String getImage() {
        return image;
    }

    @SerializedName("image_single")
    public String getImageSingle() {
        return image_single;
    }
}
