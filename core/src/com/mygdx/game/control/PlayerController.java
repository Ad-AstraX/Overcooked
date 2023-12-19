package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
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

    /**
     * Updates the player's input for interaction (movement, interaction etc.)
     * <p>
     * @param dt Time
     * @param pickup // TODO
     * @param interact // TODO
     */
    public void UpdateInput(float dt, boolean pickup, boolean interact) {
        Vector2 move = new Vector2(0, 0);

        Vector2 currentPos = player.position.cpy();

        if (Gdx.input.isKeyPressed(controls[0])) {
            player.setTexture(textures[0]);
            move.add(new Vector2(0, 1));
        }

        if (Gdx.input.isKeyPressed(controls[2])) {
            player.setTexture(textures[2]);
            move.add(new Vector2(0, -1));
        }

        if (Gdx.input.isKeyPressed(controls[1])) {
            player.setTexture(textures[1]);
            move.add(new Vector2(-1, 0));
        }

        if (Gdx.input.isKeyPressed(controls[3])) {
            player.setTexture(textures[3]);
            move.add(new Vector2(1, 0));
        }

        player.getPosition().add(move.nor().scl(Player.MOVEMENT_SPEED).scl(dt));

        Main.getWorldObjectList().toFirst();
        Main.getWorldObjectList().next();
        while (Main.getWorldObjectList().hasAccess()) {
            WorldObject currentObj = Main.getWorldObjectList().getContent();
            if (!(currentObj instanceof Player)) {
                if (player.position.x > currentObj.getPosition().x - player.size.x &&
                        player.position.x <= currentObj.getPosition().x + currentObj.getHitbox().x) {
                    if (player.position.y > currentObj.getPosition().y - player.size.y/3 &&
                            player.position.y <= currentObj.getPosition().y - player.size.y/3f + currentObj.getHitbox().y) {
                        player.position = currentPos;
                    }
                }
            }
            Main.getWorldObjectList().next();
        }

        // TODO: Player Input (Pickup, Interact)
    }

    public int[] getControls() {
        return controls;
    }
}
