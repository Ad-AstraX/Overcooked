package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.workstation.IInteractible;
import com.mygdx.game.model.object.workstation.KitchenCounter;
import com.mygdx.game.model.object.workstation.Workbench;
import com.mygdx.game.view.Main;

public class PlayerController {
    private final Player player;
    private final int[] controls;
    private final String[] textures;
    private Vector2 direction;

    public PlayerController(String[] textures, Vector2 spawnPosition, int[] controls) {
        player = new Player(textures[2], spawnPosition);
        this.controls = controls;
        this.textures = textures;
        direction = new Vector2(0, 0);

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
        Vector2 lastPos = player.position.cpy();
        handleMovement(dt);

        Main.getWorldObjectList().toFirst();
        Main.getWorldObjectList().next();
        while (Main.getWorldObjectList().hasAccess()) {
            WorldObject currentObj = Main.getWorldObjectList().getContent();
            if (!(currentObj instanceof Player)) {
                handleCollision(currentObj, lastPos);
                if (currentObj instanceof KitchenCounter) handleInteractionCheck((KitchenCounter) currentObj);
            }
            Main.getWorldObjectList().next();
        }

        // TODO: Player Input (Pickup, Interact)
    }

    /**
     * Controls the player's movement depending on the keyboard input
     * <p>
     * @param dt Time
     */
    private void handleMovement(float dt) {
        Vector2 move = new Vector2(0, 0);

        if (Gdx.input.isKeyPressed(controls[0])) {
            player.setTexture(textures[0]);
            move.add(new Vector2(0, 1));
            direction = new Vector2(0, 1);
        }

        if (Gdx.input.isKeyPressed(controls[2])) {
            player.setTexture(textures[2]);
            move.add(new Vector2(0, -1));
            direction = new Vector2(0, -1);
        }

        if (Gdx.input.isKeyPressed(controls[1])) {
            player.setTexture(textures[1]);
            move.add(new Vector2(-1, 0));
            direction = new Vector2(-1, 0);
        }

        if (Gdx.input.isKeyPressed(controls[3])) {
            player.setTexture(textures[3]);
            move.add(new Vector2(1, 0));
            direction = new Vector2(1, 0);
        }

        player.getPosition().add(move.nor().scl(Player.MOVEMENT_SPEED).scl(dt));
    }

    /**
     * Makes sure that the player cannot walk into other objects
     * <p>
     * @param currentObj the object whose collision with the player is to be checked
     * @param lastPos the position of the player before processing movement input
     */
    private void handleCollision(WorldObject currentObj, Vector2 lastPos) {
        if (player.position.x > currentObj.getPosition().x - player.size.x &&
            player.position.x <= currentObj.getPosition().x + currentObj.getHitbox().x) {
            if (player.position.y > currentObj.getPosition().y - player.size.y/3 &&
                player.position.y <= currentObj.getPosition().y - player.size.y/3 + currentObj.getHitbox().y) {
                player.position = lastPos;
            }
        }
    }

    /**
     * Checks whether or not the player is close enough to interact with a given KitchenCounter object
     * <p>
     * @param currentObj the KitchenCounter that is being used to check for possible interaction
     */
    private void handleInteractionCheck(KitchenCounter currentObj) {
        float pointX = player.position.x + player.size.x/2 + player.size.x * direction.x;
        float pointY = player.position.y + player.size.y/2 * direction.y;
        if (pointX > currentObj.getPosition().x && pointX < currentObj.getPosition().x + currentObj.getHitbox().x &&
            pointY > currentObj.getPosition().y && pointY < currentObj.getPosition().y - player.size.y/3 + currentObj.getSize().y) {
            if (!currentObj.isInteracting()) {
                currentObj.setIsInteracting(true);
                currentObj.updateImage();
                currentObj.setInteractionPartner(this.player);
            }
        } else {
            if (currentObj.isInteracting() && currentObj.getInteractionPartner().equals(this.player)) {
                currentObj.setIsInteracting(false);
                currentObj.updateImage();
                currentObj.setInteractionPartner(null);
            }
        }
    }

    public int[] getControls() {
        return controls;
    }
}
