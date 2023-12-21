package com.mygdx.game.model.object.customer;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * This class represents a customer. It can order, get angry, pay the player etc.
 */
public class Customer extends WorldObject {
    private Order order;
    private int patience;

    public Customer(Vector2 position, Order order) {
        // TODO MUST REPLACE WITH CUSTOMER TEXTURE NAME
        super("badlogic.jpg", position, new Vector2(20, 20));
        this.order = order;
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

    @Override
    public WorldObject getCopy() {
        return new Customer(this.position, this.order);
    }
}
