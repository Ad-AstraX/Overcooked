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
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;

public class Main extends ApplicationAdapter {
	private Viewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private static final List<WorldObject> worldObjectList = new List<>();
	private GameController gameController;

	public Main() {
	}

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 1000);
		viewport = new FitViewport(1200, 1000, camera);

		batch = new SpriteBatch();

		worldObjectList.append(
				new WorldObject("floorTiles.png", new Vector2(0, 0), new Vector2(1200, 1000)));
		gameController = new GameController(120f, 60, 1f);
	}

	@Override
	public void render() {
		gameController.mainLoop(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		worldObjectList.toFirst();
		while (worldObjectList.hasAccess()) {
			batch.draw(
					worldObjectList.getContent().getTexture(),
					worldObjectList.getContent().getPosition().x,
					worldObjectList.getContent().getPosition().y,
					worldObjectList.getContent().getSize().x,
					worldObjectList.getContent().getSize().y);

			worldObjectList.next();
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
