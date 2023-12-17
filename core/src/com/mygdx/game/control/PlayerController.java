package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.Main;

public class PlayerController {
    private final Player player;
    private final int[] controls;
    private final String[] textures;

    public PlayerController(String[] textures, Vector2 spawnPosition, int[] controls) {
        player = new Player(textures[2], spawnPosition);
        this.controls = controls;
        this.textures = textures;

        Main.getWorldObjectList().append(player);
    }

    public void UpdateInput(float dt, boolean pickup, boolean interact) {
        Vector2 move = Vector2.Zero;
        if (Gdx.input.isKeyPressed(controls[0])) {
            player.setTexture(textures[0]);
            move = new Vector2(0, 1).scl(Player.MOVEMENT_SPEED).scl(dt);
        }
        if (Gdx.input.isKeyPressed(controls[1])) {
            player.setTexture(textures[1]);
            move = new Vector2(-1, 0).scl(Player.MOVEMENT_SPEED).scl(dt);
        }
        if (Gdx.input.isKeyPressed(controls[2])) {
            player.setTexture(textures[2]);
            move = new Vector2(0, -1).scl(Player.MOVEMENT_SPEED).scl(dt);
        }
        if (Gdx.input.isKeyPressed(controls[3])) {
            player.setTexture(textures[3]);
            move = new Vector2(1, 0).scl(Player.MOVEMENT_SPEED).scl(dt);
        }
        player.position.add(move);

        // TODO: Player Input (Pickup, Interact)
    }
}
