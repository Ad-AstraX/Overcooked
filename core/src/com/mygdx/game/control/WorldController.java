package com.mygdx.game.control;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.BackgroundObject;
import com.mygdx.game.model.Button;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.datastructures.RectangleColored;
import com.mygdx.game.model.object.holdable.ingredient.*;
import com.mygdx.game.model.object.workstation.*;
import com.mygdx.game.view.Main;

/** Controls all world scenes and can generate them whenever */
public class WorldController {
    private int sceneID;
    private final RectangleColored transitionRect;
    private boolean transitionDarker;
    private final List<Processable> allProcessableObjects = new List<>();
    private final List<Button> allButtons = new List<>();

    public WorldController() {
        transitionRect = new RectangleColored(ShapeRenderer.ShapeType.Filled, 0, 0, 1950, 1425, 0, 0, 0, 1);
    }

    public void showStart() {
        sceneID = 0;
        Main.getAllRectangles().append(new RectangleColored(ShapeRenderer.ShapeType.Filled, 0, 0, 1950, 1425, 188/255f,238/255f,104/255f, 0.8f));

        Button newButton = new Button(new String[] {"Buttons/playButton.png", "Buttons/playButtonSelected.png"}, new Vector2(750, 700), new Vector2(500, 200));
        Main.getStaticObjectLists()[1].append(newButton);
        allButtons.append(newButton);
    }

    /** Generates the kitchen scene. It positions all backgroundObjects as well as kitchenCounters */
    public void showKitchenScene() {
        Main.getStaticObjectLists()[0].append(new BackgroundObject("Other/floorTiles.png", new Vector2(0, 0), new Vector2(1950, 1425)));
        Main.getStaticObjectLists()[0].append(new BackgroundObject("Other/coinAnimTextures.png", 1, 6, 0.2f, new Vector2(30, 1300)));

        int[][] kitchenScene = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 7, 1, 1, 1, 1, 1, 1, 1, 7, 1, 0, 0, 0},
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

        for (int i = kitchenScene.length-1; i > 0; i--) {
            List<WorldObject> currentList = Main.getStaticObjectLists()[1];
            if (i > 4) currentList = Main.getStaticObjectLists()[0];
            for (int j = 0; j < kitchenScene[i].length; j++) {
                Vector2 position = new Vector2(j*130, i*95);
                if (kitchenScene[i][j] == 1) currentList.append(new Workbench(position));
                else if (kitchenScene[i][j] == 2) currentList.append(new IngredientSpawner<>(Tomato.class, position));
                else if (kitchenScene[i][j] == 3) currentList.append(new IngredientSpawner<>(Lettuce.class, position));
                else if (kitchenScene[i][j] == 4) currentList.append(new IngredientSpawner<>(Sauce.class, position));
                else if (kitchenScene[i][j] == 5) currentList.append(new IngredientSpawner<>(Patty.class, position));
                else if (kitchenScene[i][j] == 6) currentList.append(new IngredientSpawner<>(Bun.class, position));
                else if (kitchenScene[i][j] == 7) currentList.append(new Trash(position));
                else if (kitchenScene[i][j] == 8) {
                    Cuttingboard cuttingboard = new Cuttingboard(position);
                    currentList.append(cuttingboard);
                    allProcessableObjects.append(cuttingboard);
                } else if (kitchenScene[i][j] == 9) {
                    Grill grill = new Grill(position);
                    currentList.append(grill);
                    allProcessableObjects.append(grill);
                }
            }
        }
    }

    public void update(float dt) {
        if (transitionDarker && transitionRect.getColor().a < 1) transitionRect.setAlpha(transitionRect.getColor().a+dt);
        else if (!transitionDarker && transitionRect.getColor().a > 0.1f) transitionRect.setAlpha(transitionRect.getColor().a-dt);
        if (transitionDarker && transitionRect.getColor().a >= 1) {
            transitionDarker = false;
            discard();
            sceneID++;
            if (sceneID == 1) showKitchenScene();
        }

        if (sceneID == 0) {
            allButtons.toFirst();
            if (allButtons.getContent().mouseClicked()) {
                transitionDarker = true;
            }
        } else if (sceneID == 1) {
            allProcessableObjects.toFirst();
            while (allProcessableObjects.hasAccess()) {
                allProcessableObjects.getContent().update(dt);
                allProcessableObjects.next();
            }
        }
    }

    private void discard() {
        Main.getAllRectangles().toFirst();
        while (!Main.getAllRectangles().isEmpty()) {
            Main.getAllRectangles().remove();
        }

        Main.disposeOfTexturesInList(Main.getStaticObjectLists()[0]);
        Main.disposeOfTexturesInList(Main.getStaticObjectLists()[1]);
    }

    public int getSceneID() {
        return sceneID;
    }

    public RectangleColored getTransitionRect() {
        return transitionRect;
    }
}
