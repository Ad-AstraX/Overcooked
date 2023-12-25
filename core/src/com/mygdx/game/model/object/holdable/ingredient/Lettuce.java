package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.WorldObject;

/**
 * Class which represents Lettuce
 */
public class Lettuce extends Ingredient implements ICuttable {
   private double cutTime;

   public Lettuce() {
      super("Ingredients/lettuce.png", Vector2.Zero, Vector2.Zero);
   }

   public Lettuce(Vector2 position) {
      super("Ingredients/lettuce.png", position, new Vector2(55, 165));
   }

   @Override
   public void cut(float dt) {
      cutTime += dt;
      if (cutTime > 5) {
         cutTime = 5;
         this.setTexture("Ingredients/lettuceSlices.png");
         this.size.x += 30;
         this.size.y = 150;
         this.position.x -= 20;
         this.position.y -= 15;
      }
   }

   // All Getters
   public boolean isCut() {
      return cutTime == 5;
   }
   @Override
   public WorldObject getCopy() {
      return new Lettuce(this.position);
   }
}
