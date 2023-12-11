package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;

public class Bun extends Ingredient{
    public Bun(Vector2 position) {
        super("badlogic.png", position, new Vector2(20, 20));
    }

    @Override
    public boolean pickup(Player player) {
        if (player != null){
            pickedUp = true;
            position.set(new Vector2(player.getPosition()).add(new Vector2(5, 5)));
            return true;
        }
        return false;
    }

    @Override
    public String getSubclassTypeName() {
        return this.getClass().getTypeName();
    }
}
