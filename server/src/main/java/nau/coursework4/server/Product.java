package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

/**
 * Product class is used to store information about product
 */
public class Product {
    /**
     * Product id
     */
    private int id;

    /**
     * Product name
     */
    private String name;

    /**
     * Product color
     */
    private String color;

    /**
     * Product type
     */
    public enum Type {
        Flower, Bouquet
    }

    /**
     * Product type
     */
    private Type type;

    /**
     * Product description
     */
    private String description;

    /**
     * Product price
     */
    private double price;

    /**
     * Product seasoning
     */
    private String seasoning;

    /**
     * Product image
     */
    private String image;

    /**
     * Product single image
     */
    private String image_single;

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * Constructor with parameters
     *
     * @param id           product id
     * @param name         product name
     * @param color        product color
     * @param type         product type
     * @param description  product description
     * @param price        product price
     * @param seasoning    product seasoning
     * @param image        product image
     * @param image_single product single image
     */
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
     * Get product name
     *
     * @return product name
     */
    @SerializedName("name")
    public String getName() {
        return name;
    }

    /**
     * Get product color
     *
     * @return product color
     */
    @SerializedName("color")
    public String getColor() {
        return color;
    }

    /**
     * Get product type
     *
     * @return product type
     */
    @SerializedName("type")
    public Type getType() {
        return type;
    }

    /**
     * Get product description
     *
     * @return product description
     */
    @SerializedName("description")
    public String getDescription() {
        return description;
    }

    /**
     * Get product price
     *
     * @return product price
     */
    @SerializedName("price")
    public double getPrice() {
        return price;
    }

    /**
     * Get product seasoning
     *
     * @return product seasoning
     */
    @SerializedName("seasoning")
    public String getSeasoning() {
        return seasoning;
    }

    /**
     * Get product image
     *
     * @return product image
     */
    @SerializedName("image")
    public String getImage() {
        return image;
    }

    /**
     * Get product single image
     *
     * @return product single image
     */
    @SerializedName("image_single")
    public String getImageSingle() {
        return image_single;
    }
}
