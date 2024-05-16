package nau.coursework4.server;

public class ProductBuilder {
    private int id;
    private String name;
    private String color;
    private Product.Type type;
    private String description;
    private double price;
    private String seasoning;
    private String image;
    private String image_single;

    public ProductBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public ProductBuilder setType(String type) {
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder setSeasoning(String seasoning) {
        this.seasoning = seasoning;
        return this;
    }

    public ProductBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public ProductBuilder setImageSingle(String image_single) {
        this.image_single = image_single;
        return this;
    }

    public Product build() {
        return new Product(id, name, color, type, description, price, seasoning, image, image_single);
    }
}
