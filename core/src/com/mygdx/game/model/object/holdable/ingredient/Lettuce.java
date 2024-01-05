package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * Class which represents Lettuce
 */
public class Lettuce extends Cuttable {

   public Lettuce() {
      super(new String[] {"Ingredients/lettuce.png", "Ingredients/lettuceSlices.png"}, Vector2.Zero, Vector2.Zero, 5);
      price = 2;
   }

   public Lettuce(Vector2 position) {
      super(new String[] {"Ingredients/lettuce.png", "Ingredients/lettuceSlices.png"}, position, new Vector2(55, 70), 5);
      price = 2;
   }
}
