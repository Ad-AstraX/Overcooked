package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.utilities.RectangleColored;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Cookable;
import com.mygdx.game.model.object.holdable.ingredient.Cuttable;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;
import com.mygdx.game.view.Main;

/**
 * Abstract base class for all objects that can process ingredients
 */
public abstract class Processable extends KitchenCounter {
    protected Ingredient currentIngredient;
    protected RectangleColored progressBar;
    private RectangleColored progressBarOutline;
    private RectangleColored progressBarInside;
    protected float timeTillFinish;

    public Processable(String[] textures, Vector2 position, Vector2 size, float timeTillFinish) {
        super(textures, position, size);
        this.timeTillFinish = timeTillFinish;
    }

    /**
     * Updates the current object on the kitchenCounter while it is being cooked or cut
     * @param dt Time
     */
    public void update(float dt) {
        if (currentIngredient != null ) {
            if (currentIngredient instanceof Cuttable && !((Cuttable) currentIngredient).isCut() ||
                currentIngredient instanceof Cookable && !((Cookable) currentIngredient).isCooked()) {
                try { ((Cuttable) currentIngredient).cut(dt); } catch (Exception ignore){}
                try { ((Cookable) currentIngredient).cook(dt); } catch (Exception ignore) {}
                progressBar.width -= dt*(100/timeTillFinish);

            } else if (progressBar != null && progressBar.width <= 0) {
                currentIngredient.setSize(new Vector2(currentIngredient.getTexture().getWidth(), currentIngredient.getTexture().getHeight()));
                currentIngredient.setPosition(positionObject(currentIngredient, this));

                this.setTexture(textures[isInteracting ? 1 : 0]);
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
        if (interactionPartner.getHand() != null && currentIngredient == null){
            this.currentIngredient = (Ingredient) interactionPartner.getHand();
            interactionPartner.setHand(null);
            currentIngredient.setPosition(positionObject(currentIngredient, this));

            float progressBarX = position.x+size.x/2-50;
            float progressBarY = position.y+size.y/2;
            progressBarInside = new RectangleColored(ShapeRenderer.ShapeType.Filled, progressBarX, progressBarY, 100, 10, 1, 1, 1, 1);
            progressBar = new RectangleColored(ShapeRenderer.ShapeType.Filled, progressBarX, progressBarY, 100, 10, 1, 0, 0, 1);
            progressBarOutline = new RectangleColored(ShapeRenderer.ShapeType.Line, progressBarX, progressBarY, 100, 10, 0, 0, 0, 1);
            Main.getAllRectangles().append(progressBarInside);
            Main.getAllRectangles().append(progressBar);
            Main.getAllRectangles().append(progressBarOutline);
            try { this.setTexture(textures[2 + (isInteracting ? 1 : 0)]); } catch (Exception ignore){}

        } else if (interactionPartner.getHand() == null || interactionPartner.getHand() instanceof Plate) {
            if (interactionPartner.getHand() == null) interactionPartner.setHand(new Plate(interactionPartner.getPosition()));
            ((Plate) interactionPartner.getHand()).addIngredient(this.currentIngredient);
            this.currentIngredient = null;
        }
    }

    public Ingredient getCurrentIngredient() {
        return currentIngredient;
    }
}
