package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.object.workstation.Cuttingboard;

public class World {
    private final List<WorldObject> allObjects = new List<>();
    public void generateKitchenScene() {
        // TODO DESIGN KITCHEN SOON
        // TODO Example: allObjects.append(new Cuttingboard(new Vector2(200, 200)));
    }

    public List<WorldObject> getAllObjects() {
        return allObjects;
    }
}
