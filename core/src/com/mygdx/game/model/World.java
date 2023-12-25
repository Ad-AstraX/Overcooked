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
    private final List<IProcessable> allProcessableObjects = new List<>();

    /**
     * Allows user to position objects so that they can design a scene (here specifically the kitchen)
     */
    public void generateKitchenScene() {
        // TODO FINISH DESIGN OF KITCHEN SOON
        // back and front row
        for (int i = 0; i < 10; i++) {
            Main.getWorldObjectList().append(new Workbench(new Vector2(1250-i*130, 50)));
            Main.getWorldObjectList().append(new Workbench(new Vector2(1250-i*130, 475+145)));
        }

        // rows on the sides
        WorldObject[] sideRow = new WorldObject[] {
                new Grill(),
                new Workbench(),
                new Cuttingboard(),
                new Workbench(),
                new Grill(),
        };
        for (int i = 0; i < sideRow.length; i++) {
            sideRow[i].setPosition(new Vector2(1250, 145+i*95));
            Main.getWorldObjectList().append(sideRow[i]);
            if (sideRow[i] instanceof IProcessable) allProcessableObjects.append((IProcessable) sideRow[i]);

            WorldObject copy = sideRow[i].getCopy();
            copy.setPosition(new Vector2(80, 145+i*95));
            Main.getWorldObjectList().append(copy);
            if (copy instanceof IProcessable) allProcessableObjects.append((IProcessable) copy);
        }

        // all Spawners
        Main.getWorldObjectList().append(new IngredientSpawner<>(Tomato.class, new Vector2(470, 145)));
        Main.getWorldObjectList().append(new IngredientSpawner<>(Lettuce.class, new Vector2(470, 240)));
        Main.getWorldObjectList().append(new IngredientSpawner<>(Bun.class, new Vector2(470, 335)));

        Main.getWorldObjectList().append(new IngredientSpawner<>(Patty.class, new Vector2(860, 145)));
        Main.getWorldObjectList().append(new IngredientSpawner<>(Sauce.class, new Vector2(860, 245)));
        Main.getWorldObjectList().append(new IngredientSpawner<>(Bun.class, new Vector2(860, 335)));
    }

    public List<IProcessable> getAllProcessableObjects() {
        return allProcessableObjects;
    }
}
