package nau.coursework4.server;

import com.google.gson.annotations.SerializedName;

public class BunchProduct {
    private int id;
    private int x;
    private int y;

    public BunchProduct() {
    }
    public BunchProduct(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @SerializedName("id")
    public int getId() {
        return id;
    }
    @SerializedName("x")
    public int getX() {
        return x;
    }
    @SerializedName("y")
    public int getY() {
        return y;
    }
}
