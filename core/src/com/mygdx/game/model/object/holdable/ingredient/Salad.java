package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.Player;

public class Salad extends Ingredient implements ICuttable {
   private boolean isCut;

   public Salad() {
      super("badlogic.jpg", Vector2.Zero, Vector2.Zero);
   }

   public Salad(Vector2 position) {
      super("badlogic.jpg", position, new Vector2(20, 20));
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
}
