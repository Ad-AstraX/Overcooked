package com.mygdx.game.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.BackgroundObject;
import com.mygdx.game.model.object.button.*;
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
    private final RectangleColored[] backgroundRects = new RectangleColored[10];
    private final List<Processable> allProcessableObjects = new List<>();
    private final List<Button> allButtons = new List<>();
    private static boolean multiplayerOn = true;

    public WorldController() {
        transitionRect = new RectangleColored(ShapeRenderer.ShapeType.Filled, 0, 0, 1950, 1425, 0, 0, 0, 1);
    }

    public void showStart() {
        sceneID = 0;
        Main.getAllRectangles().append(new RectangleColored(ShapeRenderer.ShapeType.Filled, 0, 0, 1950, 1425, 46/255f, 139/255f, 87/255f, 0.4f));
        for (int i = 0; i < 10; i++) {
            RectangleColored rect = new RectangleColored(ShapeRenderer.ShapeType.Filled, 0, 150*i, 1950, 50, 46/255f,139/255f,87/255f, 0.3f);
            Main.getAllRectangles().append(rect);
            backgroundRects[i] = rect;
        }

        Button newButton = new PlayButton(new Vector2(Main.getCamera().viewportWidth/2f - 250, 650), new Vector2(500, 200));
        Main.getStaticObjectLists()[1].append(newButton);
        allButtons.append(newButton);

        newButton = new InfoButton(new Vector2(50, 1250), new Vector2(110, 110));
        Main.getStaticObjectLists()[1].append(newButton);
        allButtons.append(newButton);

        newButton = new MusicToggleButton(new Vector2(1750, 1250), new Vector2(110, 110));
        Main.getStaticObjectLists()[1].append(newButton);
        allButtons.append(newButton);

        newButton = new GamemodeToggleButton(new Vector2(Main.getCamera().viewportWidth/2f - 250, 475), new Vector2(500, 160));
        Main.getStaticObjectLists()[1].append(newButton);
        allButtons.append(newButton);

        Main.getStaticObjectLists()[1].append(new BackgroundObject("Other/overcookedWriting.png", new Vector2(175.5f, 900), new Vector2(1599, 291)));
    }

    /** Generates the kitchen scene. It positions all backgroundObjects as well as kitchenCounters */
    public void showKitchenScene() {
        Main.getStaticObjectLists()[0].append(new BackgroundObject("Other/floorTiles.png", new Vector2(0, 0), new Vector2(1950, 1425)));
        Main.getStaticObjectLists()[0].append(new BackgroundObject("Other/coinAnimTextures.png", 1, 6, 0.2f, new Vector2(30, 1300)));

        int[][] kitchenScene = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 7, 1, 1, 1, 1, 1, 1, 1, 7, 1, 0, 0, 0},
                {0, 1, 0, 0, 2, 0, 0, 0, 4, 0, 0, 1, 0, 0, 0},
                {0, 9, 0, 0, 3, 0, 0, 0, 5, 0, 0, 10, 0, 0, 0},
                {0, 1, 0, 0, 6, 0, 0, 0, 6, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 1, 1, 8, 1, 1, 1, 8, 1, 1, 1, 0, 0, 0},
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
                else if (kitchenScene[i][j] == 8) currentList.append(new CashRegister(position));
                else if (kitchenScene[i][j] == 9) {
                    Cuttingboard cuttingboard = new Cuttingboard(position);
                    currentList.append(cuttingboard);
                    allProcessableObjects.append(cuttingboard);
                } else if (kitchenScene[i][j] == 10) {
                    Grill grill = new Grill(position);
                    currentList.append(grill);
                    allProcessableObjects.append(grill);
                }
            }
        }
    }

    /**
     * Updates the objects visible on the scene
     * @param dt Time
     */
    public void update(float dt) {
        if (transitionDarker && transitionRect.getColor().a < 1) transitionRect.setAlpha(transitionRect.getColor().a+dt);
        else if (!transitionDarker && transitionRect.getColor().a > 0.1f) transitionRect.setAlpha(transitionRect.getColor().a-dt);
        else {
            allButtons.toFirst();
            while (allButtons.hasAccess() && !allButtons.getContent().checkForInteraction()) allButtons.next();
        }

        if (transitionDarker && transitionRect.getColor().a >= 1) {
            transitionDarker = false;
            if (allButtons.getContent() instanceof PlayButton) {
                sceneID = 1;

                if (WorldController.isMultiplayerOn()) {
                    Main.getGameController().setPlayerController2(new PlayerController(
                            new String[]{
                                    "Players/PlayerOne/playerOrangeBehind.png",
                                    "Players/PlayerOne/playerOrangeLeft.png",
                                    "Players/PlayerOne/playerOrangeFront.png",
                                    "Players/PlayerOne/playerOrangeRight.png"},
                            new Vector2(750, 500),
                            new int[]{Input.Keys.W, Input.Keys.A, Input.Keys.S, Input.Keys.D, Input.Keys.E}
                    ));
                    Main.getPlayers()[1] = Main.getGameController().getPlayerController2().getPlayer();
                }
            }
            discard();
            if (sceneID == 1) showKitchenScene();
        }

        if (sceneID == 0) {
            for (RectangleColored rect : backgroundRects) {
                rect.y -= dt*200;
                if (rect.y + rect.height < 0) rect.y = Main.getCamera().viewportHeight+50;
            }
        } else if (sceneID == 1) {
            allProcessableObjects.toFirst();
            while (allProcessableObjects.hasAccess()) {
                allProcessableObjects.getContent().update(dt);
                allProcessableObjects.next();
            }
        }
    }

    /** Deletes everything from every list so that the lists are prepared for the next scene */
    private void discard() {
        Main.getAllRectangles().toFirst();
        while (!Main.getAllRectangles().isEmpty()) Main.getAllRectangles().remove();

        allButtons.toFirst();
        while (!allButtons.isEmpty()) allButtons.remove();

        Main.disposeOfTexturesInList(Main.getStaticObjectLists()[0]);
        Main.disposeOfTexturesInList(Main.getStaticObjectLists()[1]);
    }

    public void setTransitionDarker(boolean transitionDarker) {
        this.transitionDarker = transitionDarker;
    }
    public static void setMultiplayerOn(boolean multiplayerOn) {
        WorldController.multiplayerOn = multiplayerOn;
    }
    public int getSceneID() {
        return sceneID;
    }
    public RectangleColored getTransitionRect() {
        return transitionRect;
    }
    public static boolean isMultiplayerOn() {
        return multiplayerOn;
    }
}
