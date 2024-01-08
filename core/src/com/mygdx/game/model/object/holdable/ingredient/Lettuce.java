package com.mygdx.game.model.object.holdable.ingredient;

import com.badlogic.gdx.math.Vector2;

/**
 * This class represents Lettuce. If it is uncut, then it can be put on the cuttingboard so that it is processed.
 * Once it is fully cut, it can be stacked onto the plate and its texture is changed accordingly.
 * <p>The price of this Ingredient is two virtual coins</p>
 */
public class Lettuce extends Cuttable {

   public Lettuce() {
      super(new String[] {"Ingredients/lettuceSlices.png"}, Vector2.Zero, new Vector2(95, 40), 5);
      price = 2;
   }

   /** Constructor used by reflection in the IngredientSpawner class */
   public Lettuce(Vector2 position) {
      super(new String[] {"Ingredients/lettuce.png", "Ingredients/lettuceSlices.png"}, position, new Vector2(55, 70), 5);
      price = 2;
   }
}
