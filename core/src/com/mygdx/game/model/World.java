package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.object.holdable.ingredient.*;
import com.mygdx.game.model.object.workstation.*;
import com.mygdx.game.view.Main;

/**
 * A representation of a singular scene
 */
public class World {
    private final List<Processable> allProcessableObjects = new List<>();

    /**
     * Allows user to position objects so that they can design a scene (here specifically the kitchen)
     */
    public void generateKitchenScene() {
        Main.getStaticObjectLists()[0].append(new BackgroundObject("floorTiles.png", new Vector2(0, 0), new Vector2(1950, 1425)));

        int[][] kitchenScene = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 2, 0, 0, 0, 4, 0, 0, 1, 0, 0, 0},
            {0, 8, 0, 0, 3, 0, 0, 0, 5, 0, 0, 9, 0, 0, 0},
            {0, 1, 0, 0, 6, 0, 0, 0, 6, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        createScene(kitchenScene);
    }

    private void createScene(int[][] scene) {
        for (int i = scene.length-1; i > 0; i--) {
            List<WorldObject> currentList = Main.getStaticObjectLists()[1];
            if (i > 4) currentList = Main.getStaticObjectLists()[0];
            for (int j = 0; j < scene[i].length; j++) {
                Vector2 position = new Vector2(j*130, i*95);
                if (scene[i][j] == 1) currentList.append(new Workbench(position));
                else if (scene[i][j] == 2) currentList.append(new IngredientSpawner<>(Tomato.class, position));
                else if (scene[i][j] == 3) currentList.append(new IngredientSpawner<>(Lettuce.class, position));
                else if (scene[i][j] == 4) currentList.append(new IngredientSpawner<>(Sauce.class, position));
                else if (scene[i][j] == 5) currentList.append(new IngredientSpawner<>(Patty.class, position));
                else if (scene[i][j] == 6) currentList.append(new IngredientSpawner<>(Bun.class, position));
                else if (scene[i][j] == 7) currentList.append(new Trash(position));
                else if (scene[i][j] == 8) {
                    Cuttingboard cuttingboard = new Cuttingboard(position);
                    currentList.append(cuttingboard);
                    allProcessableObjects.append(cuttingboard);
                } else if (scene[i][j] == 9) {
                    Grill grill = new Grill(position);
                    currentList.append(grill);
                    allProcessableObjects.append(grill);
                }
            }
        }
    }

    public List<Processable> getAllProcessableObjects() {
        return allProcessableObjects;
    }
}
