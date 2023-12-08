package com.mygdx.game.control;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;

public class PlayerController {
    private final Player player;

    public PlayerController(Vector2 spawnPosition) {
        player = new Player(spawnPosition);
    }

    public void UpdateInput(float dt, Vector2 movement, boolean pickup, boolean interact) {
        player.position.add(movement.scl(dt));
    }
}
