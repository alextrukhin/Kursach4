package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

public class Flower {
    private int id;
    private String name;
    private String color;
    private String description;
    private double price;
    private String seasoning;
    private String image;

    public Flower() {
    }
    public Flower(int id, String name, String color, String description, double price, String seasoning, String image) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.description = description;
        this.price = price;
        this.seasoning = seasoning;
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
}
