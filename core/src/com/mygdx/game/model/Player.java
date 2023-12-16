package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.object.holdable.IHoldable;

public class Player extends WorldObject {
    public static final int MOVEMENT_SPEED = 100;
    private final int id;
    private IHoldable hand;

    public Player(int id, Vector2 position) {
        super("Players/PlayerOne/playerOrangeFront.png", position, new Vector2(50, 110));

        if (id == 1) this.setTexture("Players/PlayerTwo/playerGreenFront.png");
        this.id = id;
    }

    public IHoldable getHand() {
        return hand;
    }
}
