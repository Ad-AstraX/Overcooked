package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * A representation of the player. It can move around, interact with objects and in some cases hold them
 */
public class Player extends WorldObject {
    public static final int MOVEMENT_SPEED = 200;
    private IHoldable hand;
    private Vector2 direction;

    public Player(String path, Vector2 position) {
        super(path, position, new Vector2(80 * 1.3f, 140 * 1.3f));
        direction = new Vector2(0, 0);
    }

    // All Getters
    public IHoldable getHand() {
        return hand;
    }
    public Vector2 getDirection() {
        return direction;
    }

    // All Setters
    public void setHand(IHoldable hand) {
        if (hand != null) hand.setInteractionPartner(this);
        if (hand == null) this.hand.setInteractionPartner(null);
        this.hand = hand;
    }
    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
}
