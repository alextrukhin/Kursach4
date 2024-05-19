package nau.coursework4.server;

/**
 * ProductBuilder class is used to create Product objects
 */
public class ProductBuilder {
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
    private Product.Type type;

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
    public ProductBuilder() {
    }

    /**
     * Constructor with parameters
     *
     * @param product product object
     */
    public ProductBuilder(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.color = product.getColor();
        this.type = product.getType();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.seasoning = product.getSeasoning();
        this.image = product.getImage();
        this.image_single = product.getImageSingle();
    }

    /**
     * Set product id
     *
     * @param id product id
     * @return product builder
     */
    public ProductBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Set product name
     *
     * @param name product name
     * @return product builder
     */
    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set product color
     *
     * @param color product color
     * @return product builder
     */
    public ProductBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Set product type
     *
     * @param type product type
     * @return product builder
     */
    public ProductBuilder setType(String type) {
        this.type = Product.Type.valueOf(type);
        return this;
    }

    /**
     * Set product description
     *
     * @param description product description
     * @return product builder
     */
    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Set product price
     *
     * @param price product price
     * @return product builder
     */
    public ProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * Set product seasoning
     *
     * @param seasoning product seasoning
     * @return product builder
     */
    public ProductBuilder setSeasoning(String seasoning) {
        this.seasoning = seasoning;
        return this;
    }

    /**
     * Set product image
     *
     * @param image product image
     * @return product builder
     */
    public ProductBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    /**
     * Set product single image
     *
     * @param image_single product single image
     * @return product builder
     */
    public ProductBuilder setImageSingle(String image_single) {
        this.image_single = image_single;
        return this;
    }

    /**
     * Build product object
     *
     * @return product object
     */
    public Product build() {
        return new Product(id, name, color, type, description, price, seasoning, image, image_single);
    }
}
