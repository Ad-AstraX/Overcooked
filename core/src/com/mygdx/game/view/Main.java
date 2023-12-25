package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.Floor;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.datastructures.Utilities;

/**
 * This class handles all the graphics
 */
public class Main extends ApplicationAdapter {
	private Viewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	private static final List<WorldObject> worldObjectList = new List<>();
	private static final List<Rectangle> allRectangles = new List<>();
	private GameController gameController;

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		viewport = new FitViewport(1920, 1080, camera);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		worldObjectList.append(
				new Floor("floorTiles.png", new Vector2(0, 0), new Vector2(1920, 1080)));
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

		allRectangles.toFirst();
		while (allRectangles.hasAccess()) {
			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setProjectionMatrix(camera.combined);

			shapeRenderer.setColor(Color.WHITE);
			shapeRenderer.rect(
					allRectangles.getContent().x,
					allRectangles.getContent().y,
					100,
					allRectangles.getContent().height
			);

			shapeRenderer.setColor(allRectangles.getContent().width/100f, 1-allRectangles.getContent().width/100f, 0, 1);
			shapeRenderer.rect(
					allRectangles.getContent().x,
					allRectangles.getContent().y,
					allRectangles.getContent().width,
					allRectangles.getContent().height
			);
			shapeRenderer.end();

			shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.setColor(Color.BLACK);
			shapeRenderer.rect(
					allRectangles.getContent().x,
					allRectangles.getContent().y,
					100,
					allRectangles.getContent().height
			);
			shapeRenderer.end();
			allRectangles.next();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
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

	public static List<Rectangle> getAllRectangles() {
		return allRectangles;
	}
}
