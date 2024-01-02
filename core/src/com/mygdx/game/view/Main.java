package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.Player;
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

	private static final Player[] players = new Player[2];
	private static final List<WorldObject>[] staticObjectLists = new List[] {new List<WorldObject>(), new List<WorldObject>()};
	private static final List<WorldObject> dynamicObjectList = new List<>();
	private static final List<Rectangle> allRectangles = new List<>();
	private GameController gameController;
	private Music music;
	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("Lynn Music Boulangerie - Gaming Background Music (HD).mp3"));
		music.play();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1950, 1425);
		viewport = new FitViewport(1950, 1425, camera);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		gameController = new GameController(120f, 60, 1f);
	}

	@Override
	public void render() {
		gameController.mainLoop(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		Utilities.quickSort(players, 0, players.length-1);
		WorldObject[] worldObjectArray = sortListAsArray(dynamicObjectList);

		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		drawFromWorldObjectList(staticObjectLists[0]);
		drawFromWorldObjectArray(players);
		drawFromWorldObjectList(staticObjectLists[1]);
		drawFromWorldObjectArray(worldObjectArray);

		batch.end();
		allRectangles.toFirst();
		while (allRectangles.hasAccess()) {
			Rectangle current = allRectangles.getContent();
			drawRectangle(ShapeRenderer.ShapeType.Filled, Color.WHITE, current.x, current.y, 100, current.height);
			drawRectangle(ShapeRenderer.ShapeType.Filled, new Color(current.width/100f, 1-current.width/100f, 0, 1),
						  current.x, current.y, current.width, current.height);
			drawRectangle(ShapeRenderer.ShapeType.Line, Color.BLACK, current.x, current.y, 100, current.height);
			allRectangles.next();
		}
	}

	/**
	 * Iterates over all elements in a list of type List<WorldObject> and draws them
	 * @param list the list whose elements are to be drawn
	 */
	private void drawFromWorldObjectList(List<WorldObject> list) {
		list.toFirst();
		while (list.hasAccess()) {
			WorldObject current = list.getContent();
			batch.draw(
					current.getTexture(),
					current.getPosition().x, current.getPosition().y,
					current.getSize().x, current.getSize().y);
			list.next();
		}
	}

	/**
	 * Iterates over all elements in an array of type WorldObject and draws them
	 * @param array the array to be drawn from
	 */
	private void drawFromWorldObjectArray(WorldObject[] array) {
		for (WorldObject worldObject : array) {
			batch.draw(
					worldObject.getTexture(),
					worldObject.getPosition().x, worldObject.getPosition().y,
					worldObject.getSize().x, worldObject.getSize().y
			);
		}
	}

	/**
	 * Draw a rectangle
	 * @param type Filled or line
	 * @param color the color
	 * @param x x position
	 * @param y y position
	 * @param width width
	 * @param height height
	 */
	private void drawRectangle(ShapeRenderer.ShapeType type, Color color, float x, float y, float width, float height) {
		shapeRenderer.begin(type);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.end();
	}

	/**
	 * Copies all elements of a given list into an array and sorts it
	 * @param list the list that is to be sorted
	 * @return the sorted array
	 */
	private WorldObject[] sortListAsArray(List<WorldObject> list) {
		WorldObject[] resultArray = new WorldObject[Utilities.countListElements(list)];
		list.toFirst();
		for (int i = 0; i < resultArray.length; i++) {
			resultArray[i] = list.getContent();
			list.next();
		}
		Utilities.quickSort(resultArray, 0, resultArray.length-1);
		return resultArray;
	}

	/**
	 * Takes a list of type List<WorldObject> and disposes of all textures inside
	 * @param list the given list
	 */
	private void disposeOfTexturesInList(List<WorldObject> list) {
		list.toFirst();
		while (list.hasAccess()) {
			list.getContent().getTexture().dispose();
			list.next();
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		shapeRenderer.dispose();

		// Dispose of all Textures
		disposeOfTexturesInList(dynamicObjectList);
		disposeOfTexturesInList(staticObjectLists[0]);
		disposeOfTexturesInList(staticObjectLists[1]);
		players[0].getTexture().dispose();
		players[1].getTexture().dispose();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	// All Getters
	public static List<WorldObject>[] getStaticObjectLists() {
		return staticObjectLists;
	}
	public static List<WorldObject> getDynamicObjectList() {
		return dynamicObjectList;
	}
	public static List<Rectangle> getAllRectangles() {
		return allRectangles;
	}
	public static Player[] getPlayers() {
		return players;
	}
}