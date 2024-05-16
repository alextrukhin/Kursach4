package nau.coursework4.server;

public class OrderBunch {
    private Bunch bunch;
    private int quantity;

    public OrderBunch() {
    }
    public OrderBunch(Bunch bunch, int quantity) {
        this.bunch = bunch;
        this.quantity = quantity;
    }
    public Bunch getBunch() {
        return bunch;
    }
    public int getQuantity() {
        return quantity;
    }
}
