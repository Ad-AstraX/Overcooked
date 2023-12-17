package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.object.workstation.Cuttingboard;
import com.mygdx.game.model.object.workstation.Grill;
import com.mygdx.game.model.object.workstation.Workbench;

public class World {
    private final List<WorldObject> allObjects = new List<>();

    /**
     * Allows user to position objects so that they can design a scene (here specifically the kitchen)
     */
    public void generateKitchenScene() {
        // TODO FINISH DESIGN OF KITCHEN SOON
        // back row
        for (int i = 0; i < 8; i++) {
            allObjects.append(new Workbench(new Vector2(990-i*130, 50)));
            allObjects.append(new Workbench(new Vector2(990-i*130, 475+145)));
        }
        WorldObject[] sideRow = new WorldObject[] {
                new Grill(),
                new Workbench(),
                new Cuttingboard(),
                new Workbench(),
                new Grill(),
        };
        for (int i = 0; i < sideRow.length; i++) {
            sideRow[i].setPosition(new Vector2(990, 145+i*95));
            allObjects.append(sideRow[i]);

            WorldObject copy = sideRow[i].getCopy();
            copy.setPosition(new Vector2(80, 145+i*95));
            allObjects.append(copy);
        }
    }

    public List<WorldObject> getAllObjects() {
        return allObjects;
    }
}
