package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;

public class Main extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private static List<WorldObject> worldObjectList = new List<>();
	private GameController gameController;

	public Main() {
	}

	@Override
	public void create() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 800);

		batch = new SpriteBatch();

		gameController = new GameController(120f, 60, 1f);
	}

	@Override
	public void render() {
		gameController.mainLoop(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(new Texture("floorTiles.png"), 0, 0, 800, 800);
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
	}

	public static List<WorldObject> getWorldObjectList() {
		return worldObjectList;
	}
}
