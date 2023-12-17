package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;

public class Player extends WorldObject {
    public static final int MOVEMENT_SPEED = 200;
    private IHoldable hand;

    public Player(String path, Vector2 position) {
        super(path, position, new Vector2(80, 140));
    }

    public IHoldable getHand() {
        return hand;
    }
}
