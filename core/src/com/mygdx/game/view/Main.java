package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.control.GameController;
import com.mygdx.game.control.WorldController;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;
import com.mygdx.game.model.datastructures.RectangleColored;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.datastructures.Utilities;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;
import com.mygdx.game.model.object.workstation.Processable;
import com.mygdx.game.model.object.workstation.Workbench;

/**
 * This class handles all the graphics
 */
public class Main extends ApplicationAdapter {
	private static Viewport viewport;
	private static OrthographicCamera camera;
	private static SpriteBatch batch;
	private static ShapeRenderer shapeRenderer;

	private static final Player[] players = new Player[2];
	private static final List<WorldObject>[] staticObjectLists = new List[] {new List<WorldObject>(), new List<WorldObject>()};
	private static final List<RectangleColored> allRectangles = new List<>();
	private static GameController gameController;
	private static Music music;
	private static BitmapFont font;
	private static final Vector3 mousePosition = new Vector3(0, 0, 0);

	private static float stateTime;

	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("Sound/Lynn Music Boulangerie - Gaming Background Music (HD).mp3"));
		music.setLooping(true);
		music.setVolume(0.6f);
		music.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1950, 1425);
		viewport = new FitViewport(1950, 1425, camera);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont(Gdx.files.internal("Fonts/CoinDisplay/coinDisplay.fnt"), false);

		gameController = new GameController(120f, 60, 10f);
	}

	@Override
	public void render() {
		if (Gdx.graphics.getDeltaTime() < 0.02f) {
			stateTime += Gdx.graphics.getDeltaTime();
			gameController.mainLoop(Gdx.graphics.getDeltaTime());

			ScreenUtils.clear(0, 0, 0, 1);
			camera.update();

			mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(mousePosition);

			if (gameController.getWorldController().getSceneID() == 0) {
				drawRectanglesFromList(allRectangles);

				batch.begin();
				batch.setProjectionMatrix(camera.combined);
				drawFromWorldObjectList(staticObjectLists[0]);
				drawFromWorldObjectList(staticObjectLists[1]);
				batch.end();
			} else if (gameController.getWorldController().getSceneID() == 1) {
				if (WorldController.isMultiplayerOn() && players[0].getPosition().y < players[1].getPosition().y) {
					Player help = players[1];
					players[1] = players[0];
					players[0] = help;
				}

				batch.begin();
				batch.setProjectionMatrix(camera.combined);
				drawFromWorldObjectList(staticObjectLists[0]);
				drawFromPlayersArray();
				drawFromWorldObjectList(staticObjectLists[1]);

				font.getData().setScale(2.5f);
				font.draw(batch, GameController.getGame().getPayTotal() + " $", 250 - (float) (GameController.getGame().getPayTotal() + " $").length() / 2 * 32f, 1380);
				batch.end();

				drawRectanglesFromList(allRectangles);
			}

			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			RectangleColored current = gameController.getWorldController().getTransitionRect();
			shapeRenderer.begin(current.getShapeType());
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.setColor(current.getColor());
			shapeRenderer.rect(current.x, current.y, current.width, current.height);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
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
			drawWorldObject(current);

			WorldObject ingredientOnCurrent = null;
			if (current instanceof Workbench) ingredientOnCurrent = (WorldObject) ((Workbench) current).getCurrentHoldable();
			else if (current instanceof Processable) ingredientOnCurrent = ((Processable) current).getCurrentIngredient();

			if (ingredientOnCurrent != null) {
				drawWorldObject(ingredientOnCurrent);
				if (ingredientOnCurrent instanceof Plate)  {
					Stack<Ingredient> copy = Utilities.invertStack(((Plate) ingredientOnCurrent).getIngredients());
					while (!copy.isEmpty()) {
						drawWorldObject(copy.top());
						copy.pop();
					}
				}
			}
			list.next();
		}
	}

	/** Iterates over the players and draws them as well as the object they are holding */
	private void drawFromPlayersArray() {
		for (Player worldObject : Main.players) {
			if (worldObject != null) {
				if (!worldObject.getDirection().equals(new Vector2(0, 1))) drawWorldObject(worldObject);
				if (worldObject.getHand() != null) drawWorldObject((WorldObject) worldObject.getHand());
				if (worldObject.getHand() instanceof Plate) {
					Stack<Ingredient> copy = Utilities.invertStack(((Plate) worldObject.getHand()).getIngredients());
					while (!copy.isEmpty()) {
						drawWorldObject(copy.top());
						copy.pop();
					}
				}
				if (worldObject.getDirection().equals(new Vector2(0, 1))) drawWorldObject(worldObject);
			}
		}
	}

	/**
	 * Draws a given WorldObject
	 * @param object the WorldObject that is to be drawn
	 */
	private void drawWorldObject(WorldObject object) {
		if (object.isAnimation()) {
			TextureRegion currentFrame = object.getAnimation().getKeyFrame(stateTime, true);
			batch.draw(currentFrame, object.getPosition().x, object.getPosition().y);
		} else {
			batch.draw(
					object.getTexture(),
					object.getPosition().x, object.getPosition().y,
					object.getSize().x, object.getSize().y
			);
		}
	}

	/**
	 * Draws all rectangles in a list of type List<RectangleColored>
	 * @param list the list that is to be drawn from
	 */
	private void drawRectanglesFromList(List<RectangleColored> list) {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		list.toFirst();
		while (list.hasAccess()) {
			RectangleColored current = list.getContent();
			shapeRenderer.begin(current.getShapeType());
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.setColor(current.getColor());
			shapeRenderer.rect(current.x, current.y, current.width, current.height);
			shapeRenderer.end();
			list.next();
		}
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	/**
	 * Takes a list of type List<WorldObject> and disposes of all textures inside
	 * @param list the given list
	 */
	public static void disposeOfTexturesInList(List<WorldObject> list) {
		list.toFirst();
		while (list.hasAccess()) {
			list.getContent().getTexture().dispose();

			WorldObject ingredientOnCurrent = null;
			if (list.getContent() instanceof Workbench) ingredientOnCurrent = (WorldObject) ((Workbench) list.getContent()).getCurrentHoldable();
			else if (list.getContent() instanceof Processable) ingredientOnCurrent = ((Processable) list.getContent()).getCurrentIngredient();

			if (ingredientOnCurrent != null) {
				ingredientOnCurrent.getTexture().dispose();
				if (ingredientOnCurrent instanceof Plate)  {
					while (!((Plate) ingredientOnCurrent).getIngredients().isEmpty()) {
						((Plate) ingredientOnCurrent).getIngredients().top().getTexture().dispose();
						((Plate) ingredientOnCurrent).getIngredients().pop();
					}
				}
			}
			list.remove();
		}
	}

	/**
	 * Takes an Array of Player objects and disposes of all textures inside
	 * @param players the given array of players
	 */
	private void disposeOfTexturesFromPlayers(Player[] players) {
		for (Player player : players) {
			if (player != null) {
				player.getTexture().dispose();
				if (player.getHand() != null) {
					((WorldObject) player.getHand()).getTexture().dispose();
					if (player.getHand() instanceof Plate) {
						while (!((Plate) player.getHand()).getIngredients().isEmpty()) {
							((Plate) player.getHand()).getIngredients().top().getTexture().dispose();
							((Plate) player.getHand()).getIngredients().pop();
						}
					}
				}
			}
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		shapeRenderer.dispose();
		font.dispose();

		// Dispose of all Textures
		disposeOfTexturesInList(staticObjectLists[0]);
		disposeOfTexturesInList(staticObjectLists[1]);
		disposeOfTexturesFromPlayers(players);
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	// All Getters
	public static List<WorldObject>[] getStaticObjectLists() {
		return staticObjectLists;
	}
	public static List<RectangleColored> getAllRectangles() {
		return allRectangles;
	}
	public static Player[] getPlayers() {
		return players;
	}
	public static GameController getGameController() {
		return gameController;
	}
	public static Vector3 getMousePosition() {
		return mousePosition;
	}
	public static OrthographicCamera getCamera() {
		return camera;
	}
	public static Music getMusic() {
		return music;
	}
}