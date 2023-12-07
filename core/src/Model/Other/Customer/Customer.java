package Model.Other.Customer;

import Model.WorldObject;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Customer extends WorldObject {
    private Order order;
    private int patience;
    public Customer(Vector2 position, Sprite texture) {
        super(position, new Vector2(20, 20), texture);
    }

    public Order getOrder() {
        return order;
    }
    public int getPatience() {
        return patience;
    }
    public void setPatience(int patience) {
        this.patience = patience;
    }
}
