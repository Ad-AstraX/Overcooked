package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.utilities.RectangleColored;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;
import com.mygdx.game.model.object.holdable.ingredient.Cuttable;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;
import com.mygdx.game.view.Main;

/**
 * Abstract base class for all objects that can process ingredients. This class checks whether another object
 * is being processed already and if not, it takes the object in the player's hand and processes it.
 */
public abstract class Processable extends KitchenCounter {
    protected Sound interactionSound;
    /** The ingredient that is currently on this KitchenCounter */
    protected Ingredient currentIngredient;
    /** The progressbar */
    protected RectangleColored progressBar;
    /** The black outline of the progressbar */
    private RectangleColored progressBarOutline;
    /** The white inside of the progressbar */
    private RectangleColored progressBarInside;
    /** The time that is left until the object is finished processing */
    protected float timeTillFinish;

    public Processable(String[] textures, Vector2 position, Vector2 size, float timeTillFinish, Sound interactionSound) {
        super(textures, position, size);
        this.timeTillFinish = timeTillFinish;
        this.interactionSound = interactionSound;
    }

    /**
     * Updates the current object on the kitchenCounter while it is being cooked or cut
     * @param dt Time
     */
    public void update(float dt) {
        if (currentIngredient != null ) {
            if (currentIngredient instanceof Cuttable && !((Cuttable) currentIngredient).isCut() ||
                currentIngredient instanceof Cookable && !((Cookable) currentIngredient).isCooked()) {
                // The ingredient currently on this KitchenCounter is processed and the progressBar matched accordingly
                try { ((Cuttable) currentIngredient).cut(dt); } catch (Exception ignore){}
                try { ((Cookable) currentIngredient).cook(dt); } catch (Exception ignore) {}
                progressBar.width -= dt*(100/timeTillFinish);
            // If this object is fully cooked or cut but the progressBar is yet to be removed
            } else if (progressBar != null && progressBar.width <= 0) {
                // Size and position are matched to the new Object
                currentIngredient.setSize(new Vector2(currentIngredient.getTexture().getWidth(), currentIngredient.getTexture().getHeight()));
                currentIngredient.setPosition(positionObject(currentIngredient, this));

                this.setTexture(textures[isInteracting ? 1 : 0]);
                // Deletes the progressBar
                Main.getAllRectangles().toFirst();
                while (Main.getAllRectangles().hasAccess()) {
                    RectangleColored current = Main.getAllRectangles().getContent();
                    if (current.equals(progressBar) || current.equals(progressBarInside) || current.equals(progressBarOutline)) {
                        Main.getAllRectangles().remove();
                    }
                    Main.getAllRectangles().next();
                }
                progressBar = null;
                progressBarInside = null;
                progressBarOutline = null;
            }
        }
    }

    public void interact() {
        // If the player wishes to put something down here
        if (interactionPartner.getHand() != null && currentIngredient == null){
            // The object is retrieved from the player's hand and positioned onto this kitchenCounter
            this.currentIngredient = (Ingredient) interactionPartner.getHand();
            interactionPartner.setHand(null);
            currentIngredient.setPosition(positionObject(currentIngredient, this));

            // The progressBar is created
            float progressBarX = position.x+size.x/2-50;
            float progressBarY = position.y+size.y/2;
            progressBarInside = new RectangleColored(ShapeRenderer.ShapeType.Filled, progressBarX, progressBarY, 100, 10, 1, 1, 1, 1);
            progressBar = new RectangleColored(ShapeRenderer.ShapeType.Filled, progressBarX, progressBarY, 100, 10, 1, 0, 0, 1);
            progressBarOutline = new RectangleColored(ShapeRenderer.ShapeType.Line, progressBarX, progressBarY, 100, 10, 0, 0, 0, 1);
            Main.getAllRectangles().append(progressBarInside);
            Main.getAllRectangles().append(progressBar);
            Main.getAllRectangles().append(progressBarOutline);
            // This line of code is meant for the grill, so it can turn on once an ingredient is placed on it
            try { this.setTexture(textures[2 + (isInteracting ? 1 : 0)]); } catch (Exception ignore){}

            interactionSound.play(0.4f);
        // If the player wishes to retrieve the object, then it is placed onto a plate (if the player has none, one is created for him)
        } else if (interactionPartner.getHand() == null || interactionPartner.getHand() instanceof Plate) {
            if (interactionPartner.getHand() == null) interactionPartner.setHand(new Plate(interactionPartner.getPosition()));
            ((Plate) interactionPartner.getHand()).addIngredient(this.currentIngredient);
            this.currentIngredient = null;

            pickUpSound.play(0.5f);
        }
    }

    // All Getters
    public Ingredient getCurrentIngredient() {
        return currentIngredient;
    }
}
