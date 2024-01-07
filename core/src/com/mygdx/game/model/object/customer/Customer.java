package com.mygdx.game.model.object.customer;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * <p>This class represents a customer. </p> <br>
 * The customer can place an order and waits in a line until it is their turn. Once that has happened
 * the customer's patience starts to gradually decrease until their order has been finished and given to them.
 * He then pays the Player for the burger and tips him according to how quick the player was.
 */
public class Customer extends WorldObject {
    /** The order that the customer placed. It consist of one burger */
    private final Order order;
    /** The patience of the customer. Decreases while waiting */
    private int patience;

    public Customer(Vector2 position, Order order) {
        // TODO MUST REPLACE WITH CUSTOMER TEXTURE NAME
        super("badlogic.jpg", position, new Vector2(20, 20));
        this.order = order;
    }

    // All Getters
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
