package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;

public class Lettuce extends Ingredient implements ICuttable {
   private boolean isCut;

   public Lettuce() {
      super("Ingredients/lettuce.png", Vector2.Zero, Vector2.Zero);
   }

   public Lettuce(Vector2 position) {
      super("Ingredients/lettuce.png", position, new Vector2(20, 20));
   }

   @Override
   public boolean pickup(Player player) {
      return false;
   }

   @Override
   public String getSubclassTypeName() {
      return this.getClass().getTypeName();
   }

   @Override
   public void cut() {
      isCut = true;
   }

   public boolean isCut() {
      return isCut;
   }

   @Override
   public WorldObject getCopy() {
      return new Lettuce(this.position);
   }
}
