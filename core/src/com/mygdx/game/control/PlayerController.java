package com.mygdx.game.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.workstation.KitchenCounter;
import com.mygdx.game.view.Main;

/**
 * This class controls the player. <br>
 * It checks for collisions as well as possible interaction and limits the player's movement / executes interaction accordingly.
 */
public class PlayerController {
    /** The player that is being controlled by this playerController */
    private final Player player;
    /** The player's controls */
    private final int[] controls;
    /** The textures of the player */
    private final String[] textures;

    public PlayerController(String[] textures, Vector2 spawnPosition, int[] controls) {
        player = new Player(textures[2], spawnPosition);
        this.controls = controls;
        this.textures = textures;
    }

    /**
     * Updates the player's input for interaction (movement, interaction etc.) <br>
     * It loops over both staticObjectLists and checks for interaction / collision. <br>
     * It also updates the position of the object that it is currently holding.
     * <p>
     * @param dt Time
     */
    public void updateInput(float dt) {
        Vector2 lastPos = player.getPosition().cpy();
        handleMovement(dt);

        for (int i = 0; i < 2; i++) {
            Main.getStaticObjectLists()[i].toFirst();
            while (Main.getStaticObjectLists()[i].hasAccess()) {
                WorldObject currentObj = Main.getStaticObjectLists()[i].getContent();
                if (currentObj instanceof KitchenCounter) {
                    handleCollision(currentObj, lastPos);
                    handleInteractionCheck((KitchenCounter) currentObj);
                }
                Main.getStaticObjectLists()[i].next();
            }
        }

        if (player.getHand() != null) player.getHand().beCarriedByPlayer(player.getDirection());
    }

    /**
     * Controls the player's movement depending on the keyboard input as well as matches texture to the input.
     * The attribute "direction" - which represents the direction of the player - is updated, as it is passed
     * to the object that the player is currently holding. This is necessary, because the object changes its
     * position depending on the direction that the player is looking at.
     * <p>
     * @param dt Time
     */
    private void handleMovement(float dt) {
        Vector2 move = new Vector2(0, 0);

        if (Gdx.input.isKeyPressed(controls[0])) {
            player.setAnimation(textures[0], (int) player.getColsAndRows().x, (int) player.getColsAndRows().y, 0.3f);
            move.add(new Vector2(0, 1));
            player.setDirection(new Vector2(0, 1));
        }

        if (Gdx.input.isKeyPressed(controls[2])) {
            player.setAnimation(textures[2], (int) player.getColsAndRows().x, (int) player.getColsAndRows().y, 0.3f);
            move.add(new Vector2(0, -1));
            player.setDirection(new Vector2(0, -1));
        }

        if (Gdx.input.isKeyPressed(controls[1])) {
            player.setAnimation(textures[1], (int) player.getColsAndRows().x, (int) player.getColsAndRows().y, 0.3f);
            move.add(new Vector2(-1, 0));
            player.setDirection(new Vector2(-1, 0));
        }

        if (Gdx.input.isKeyPressed(controls[3])) {
            player.setAnimation(textures[3], (int) player.getColsAndRows().x, (int) player.getColsAndRows().y, 0.3f);
            move.add(new Vector2(1, 0));
            player.setDirection(new Vector2(1, 0));
        }

        player.getPosition().add(move.nor().scl(Player.MOVEMENT_SPEED + ((!WorldController.isMultiplayerOn() ? 1 : 0) * 50)).scl(dt));
    }

    /**
     * Checks if the player is trying to walk into another object. If that is the case, then the player's
     * position is being reset to its last position before walking into another object.
     * <p>
     * @param currentObj the object whose collision with the player is to be checked
     * @param lastPos the position of the player before processing movement input
     */
    private void handleCollision(WorldObject currentObj, Vector2 lastPos) {
        if (player.getPosition().x > currentObj.getPosition().x - player.getSize().x &&
            player.getPosition().x <= currentObj.getPosition().x + currentObj.getSize().x) {
            if (player.getPosition().y > currentObj.getPosition().y - player.getSize().y/3 &&
                player.getPosition().y <= currentObj.getPosition().y - player.getSize().y/4 + currentObj.getSize().y) {
                player.setPosition(lastPos);
            }
        }
    }

    /**
     * Checks whether the player is close enough to interact with a given KitchenCounter object. If that
     * is the case and the player is pressing the interact button then the given KitchenCounter object
     * is interacted with.
     * <p>
     * @param currentObj the KitchenCounter that is being used to check for possible interaction
     */
    private void handleInteractionCheck(KitchenCounter currentObj) {
        // Calculates an "imaginary" point, which depends on the player's direction
        float pointX = player.getPosition().x + player.getSize().x/2 + player.getSize().x * player.getDirection().x;
        float pointY = player.getPosition().y + player.getSize().y/2 * player.getDirection().y;
        /* If said point is within the bounds of a KitchenCounter object and no other player is interacting with that
           kitchenCounter, then its image is updated and its attribute "interactionPartner" is set to this player */
        if (pointX > currentObj.getPosition().x && pointX < currentObj.getPosition().x + currentObj.getSize().x &&
            pointY > currentObj.getPosition().y && pointY < currentObj.getPosition().y - player.getSize().y/3 + currentObj.getSize().y) {
            if (!currentObj.isInteracting() && currentObj.getInteractionPartner() == null) {
                currentObj.setIsInteracting(true);
                currentObj.updateImage();
                currentObj.setInteractionPartner(this.player);
            }
            // Processes interaction
            if (Gdx.input.isKeyJustPressed(controls[4]) && currentObj.getInteractionPartner().equals(this.player)) {
                currentObj.interact();
            }
        } else {
            // If the object is not being interacted with and its texture is yet to be updated then this is done here
            if (currentObj.isInteracting() && currentObj.getInteractionPartner().equals(this.player)) {
                currentObj.setIsInteracting(false);
                currentObj.updateImage();
                currentObj.setInteractionPartner(null);
            }
        }
    }

    // All Getters
    public Player getPlayer() {
        return player;
    }
}
