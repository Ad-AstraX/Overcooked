package com.mygdx.game.model.object.customer;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

public class Customer extends WorldObject {
    private Order order;
    private int patience;

    public Customer(String texturePath, Vector2 position, Vector2 size) {
        super("badlogic.jpg", position, new Vector2(20, 20));
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
