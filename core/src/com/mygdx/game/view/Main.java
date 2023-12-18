package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.Floor;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.datastructures.Utilities;

/**
 * DON'T YOU DARE MAKE CHANGES TO THIS CLASS WITHOUT CONSULTING ME
 */
public class Main extends ApplicationAdapter {
	private Viewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private static final List<WorldObject> worldObjectList = new List<>();
	private GameController gameController;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 1000);
		viewport = new FitViewport(1200, 1000, camera);

		batch = new SpriteBatch();

		worldObjectList.append(
				new Floor("floorTiles.png", new Vector2(0, 0), new Vector2(1200, 1000)));
		gameController = new GameController(120f, 60, 1f);
	}

	@Override
	public void render() {
		gameController.mainLoop(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		WorldObject[] worldObjectArray = new WorldObject[Utilities.countListElements(worldObjectList)];
		worldObjectList.toFirst();
		for (int i = 0; i < worldObjectArray.length; i++) {
			worldObjectArray[i] = worldObjectList.getContent();
			worldObjectList.next();
		}

		Utilities.quickSort(worldObjectArray, 1, worldObjectArray.length-1);
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
        for (WorldObject worldObject : worldObjectArray) {
            batch.draw(
                    worldObject.getTexture(),
                    worldObject.getPosition().x,
                    worldObject.getPosition().y,
                    worldObject.getSize().x,
                    worldObject.getSize().y);
		}
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		while (worldObjectList.hasAccess()) {
			worldObjectList.getContent().getTexture().dispose();
			worldObjectList.next();
		}
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	public static List<WorldObject> getWorldObjectList() {
		return worldObjectList;
	}
}
