package com.mygdx.game.model.object.workstation;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.object.holdable.IHoldable;
import com.mygdx.game.model.object.holdable.ingredient.ICuttable;
import com.mygdx.game.view.Main;

/**
 * This class represents a cuttingboard which can cut cuttable Objects
 */
public class Cuttingboard extends KitchenCounter implements IProcessable {
    private ICuttable currentCuttable;
    private Rectangle progressBar;
    public Cuttingboard() {
        super("Interactables/cuttingboard.png", Vector2.Zero, new Vector2(130, 160));
    }

    public Cuttingboard(Vector2 position) {
        super("Interactables/cuttingboard.png", position, new Vector2(130, 160));
    }

    public void updateImage() {
        if (this.isInteracting) {
            this.setTexture("Interactables/cuttingboardSelected.png");
        } else {
            this.setTexture("Interactables/cuttingboard.png");
        }
    }

    public void update(float dt) {
        if (currentCuttable != null && !currentCuttable.isCut()) {
            currentCuttable.cut(dt);
            progressBar.width -= dt*20;
        } else if (currentCuttable != null && currentCuttable.isCut() && progressBar != null) {
            Main.getAllRectangles().toFirst();
            while (Main.getAllRectangles().hasAccess() && !Main.getAllRectangles().getContent().equals(progressBar)) {
                Main.getAllRectangles().next();
            }
            Main.getAllRectangles().remove();
        }
    }

    /** Method is called whenever player wishes to cut an uncut Cuttable object on this Cuttingboard */
    @Override
    public void interact() {
        if (interactionPartner.getHand() != null && currentCuttable == null &&
            interactionPartner.getHand() instanceof ICuttable && !((ICuttable) interactionPartner.getHand()).isCut()){
            this.currentCuttable = (ICuttable) interactionPartner.getHand();
            interactionPartner.setHand(null);
            ((WorldObject) currentCuttable).setPosition(
                    new Vector2(this.position.x - ((WorldObject) currentCuttable).getSize().x/2 + this.size.x/2, this.position.y-1)
            );
            progressBar = new Rectangle(position.x+size.x/2-50, position.y+size.y/2, 100, 10);
            Main.getAllRectangles().append(progressBar);
        } else if (interactionPartner.getHand() == null && currentCuttable != null && currentCuttable.isCut()) {
            interactionPartner.setHand((IHoldable) this.currentCuttable);
            this.currentCuttable = null;
        }
    }

    // All Getters
    public ICuttable getCurrentCuttable() {
        return currentCuttable;
    }
    @Override
    public WorldObject getCopy() {
        return new Cuttingboard(this.position);
    }
}
