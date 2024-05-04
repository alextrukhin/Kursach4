package nau.coursework4.server;

public class FlowerBuilder {
    private int id;
    private String name;
    private String color;
    private String description;
    private double price;
    private String seasoning;
    private String image;

    public FlowerBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public FlowerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public FlowerBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public FlowerBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public FlowerBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public FlowerBuilder setSeasoning(String seasoning) {
        this.seasoning = seasoning;
        return this;
    }

    public FlowerBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public Flower build() {
        return new Flower(id, name, color, description, price, seasoning, image);
    }
}
