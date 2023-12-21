package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;

/**
 * A representation of the player. It can move around, interact with objects and in some cases hold them
 */
public class Player extends WorldObject {
    public static final int MOVEMENT_SPEED = 200;
    private IHoldable hand;

    public Player(String path, Vector2 position) {
        super(path, position, new Vector2(80 * 1.3f, 140 * 1.3f));
    }

    public IHoldable getHand() {
        return hand;
    }

    @Override
    public WorldObject getCopy() {
        return new Player("fallbackTexture.png", this.position);
    }
}
