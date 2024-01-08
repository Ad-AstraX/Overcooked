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
import com.mygdx.game.model.utilities.RectangleColored;
import com.mygdx.game.model.datastructures.Stack;
import com.mygdx.game.model.utilities.Utilities;
import com.mygdx.game.model.object.holdable.Plate;
import com.mygdx.game.model.object.holdable.ingredient.Ingredient;
import com.mygdx.game.model.object.workstation.Processable;
import com.mygdx.game.model.object.workstation.Workbench;

/**
 * <p> This class handles all the graphics. Since the Main class is unique in the whole program, all its attributes are static </p> <br>
 *
 * <p> It "collects" all the objects that need to be drawn in the staticObjectLists / players array. Since ingredients
 * cannot exist independently of either the players or the kitchenCounters (e.g. it can't just lie around, it must
 * be held by someone or something) they are accessed through the player that is holding the ingredient. </p> <br>
 *
 * <p> Once the project has finished execution, all Objects are deleted and the textures as well as the camera, viewport
 * and font are disposed of </p>
 */
public class Main extends ApplicationAdapter {
	// Attributes regarding graphics and sound
	private static OrthographicCamera camera;
	private static Viewport viewport;
	private static ShapeRenderer shapeRenderer;
	private static SpriteBatch batch;
	private static BitmapFont font;
	private static Music music;

	// Lists / Array that store(s) the objects which need to be drawn
	/** The array which stores the two players (if there are two) */
	private static final Player[] PLAYERS = new Player[2];
	/**
	 * This array stores two lists - one with all the objects that are <b>always</b> in the background and one with the
	 * objects that are <b>always</b> in the foreground
	 */
	private static final List<WorldObject>[] STATIC_OBJECT_LISTS = new List[] {new List<WorldObject>(), new List<WorldObject>()};
	/** All Rectangles that need to be drawn */
	private static final List<RectangleColored> ALL_RECTANGLES = new List<>();

	/** The gameController of this class */
	private static GameController gameController;
	/** Position of the mouse */
	private static final Vector3 MOUSE_POSITION = new Vector3(0, 0, 0);
	/** Time that has passed since the creation of the project */
	private static float stateTime;

	/**
	 * This method is called when the application is first created.
	 * As such, it is only called once in the whole program, meaning that relevant attributes are instantiated here.
	 */
	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("Sound/Lynn Music Boulangerie - Gaming Background Music (HD).mp3"));
		music.setLooping(true);
		music.setVolume(0.2f);
		music.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1950, 1425);
		viewport = new FitViewport(1950, 1425, camera);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont(Gdx.files.internal("Fonts/CoinDisplay/coinDisplay.fnt"), false);

		gameController = new GameController(120f, 60, 10f);
	}

	/** This method is called once every frame and updates the graphics on the screen */
	@Override
	public void render() {
		// If condition that stops program from updating when it is being resized or dragged
		if (Gdx.graphics.getDeltaTime() < 0.02f) {
			// updating the stateTime, camera and mousePosition to prepare for the next frame
			stateTime += Gdx.graphics.getDeltaTime();
			gameController.mainLoop(Gdx.graphics.getDeltaTime());

			ScreenUtils.clear(0, 0, 0, 1);
			camera.update();

			MOUSE_POSITION.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(MOUSE_POSITION);

			// First scene - Game UI
			if (gameController.getWorldController().getSceneID() == 0) {
				drawFromRectanglesList();

				batch.begin();
				batch.setProjectionMatrix(camera.combined);
				drawFromWorldObjectList(STATIC_OBJECT_LISTS[0]);
				drawFromWorldObjectList(STATIC_OBJECT_LISTS[1]);
				batch.end();

			// Second scene - actual game, kitchenscene
			} else if (gameController.getWorldController().getSceneID() == 1) {
				// Switches the places of the players in the array (if multiplayer mode is on) so that their drawing order is updated
				if (WorldController.isMultiplayerOn() && PLAYERS[0].getPosition().y < PLAYERS[1].getPosition().y) {
					Player help = PLAYERS[1];
					PLAYERS[1] = PLAYERS[0];
					PLAYERS[0] = help;
				}

				batch.begin();
				batch.setProjectionMatrix(camera.combined);
				drawFromWorldObjectList(STATIC_OBJECT_LISTS[0]);
				drawFromPlayersArray();
				drawFromWorldObjectList(STATIC_OBJECT_LISTS[1]);

				font.getData().setScale(2.5f);
				font.draw(batch, GameController.getGame().getPayTotal() + " $", 250 - (float) (GameController.getGame().getPayTotal() + " $").length() / 2 * 32f, 1380);
				batch.end();

				drawFromRectanglesList();
			}

			// The "transitionRectangle" of the worldController is drawn last as it has to be in front of all other objects
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
	 * Iterates over all elements in a list of type List<WorldObject> and draws them.
	 * If they are currently holding an object, that object is drawn as well.
	 * <p>
	 * @param list the list whose elements are to be drawn
	 */
	private void drawFromWorldObjectList(List<WorldObject> list) {
		list.toFirst();
		while (list.hasAccess()) {
			WorldObject current = list.getContent();
			drawWorldObject(current);

			// Retrieve the possible Holdable object that the current kitchenCounter may have
			WorldObject ingredientOnCurrent = null;
			if (current instanceof Workbench) ingredientOnCurrent = (WorldObject) ((Workbench) current).getCurrentHoldable();
			else if (current instanceof Processable) ingredientOnCurrent = ((Processable) current).getCurrentIngredient();

			// if the kitchenCounter possesses a current Holdable then it is drawn
			if (ingredientOnCurrent != null) {
				drawWorldObject(ingredientOnCurrent);
				// checks if the current Holdable is a plate. And if so, then all the objects are drawn in reverse order
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

	/** Iterates over the players int the players array and draws them as well as the object they are holding */
	private void drawFromPlayersArray() {
		for (Player worldObject : Main.PLAYERS) {
			if (worldObject != null) {
				// If the player texture is looking at the screen then the object they are holding is drawn after the player
				if (!worldObject.getDirection().equals(new Vector2(0, 1))) drawWorldObject(worldObject);
				if (worldObject.getHand() != null) drawWorldObject((WorldObject) worldObject.getHand());
				// checks if the current Holdable is a plate. And if so, then all the objects are drawn in reverse order
				if (worldObject.getHand() instanceof Plate) {
					Stack<Ingredient> copy = Utilities.invertStack(((Plate) worldObject.getHand()).getIngredients());
					while (!copy.isEmpty()) {
						drawWorldObject(copy.top());
						copy.pop();
					}
				}
				// If the player texture is looking away from the screen then the object they are holding is drawn before the player
				if (worldObject.getDirection().equals(new Vector2(0, 1))) drawWorldObject(worldObject);
			}
		}
	}

	/**
	 * Draws a given WorldObject using either its texture or its animation
	 * <p>
	 * @param object the WorldObject that is to be drawn
	 */
	private void drawWorldObject(WorldObject object) {
		if (object.isAnimation()) {
			TextureRegion currentFrame = object.getAnimation().getKeyFrame(stateTime, true);
			batch.draw(currentFrame, object.getPosition().x, object.getPosition().y, object.getSize().x, object.getSize().y);
		} else {
			batch.draw(
					object.getTexture(),
					object.getPosition().x, object.getPosition().y,
					object.getSize().x, object.getSize().y
			);
		}
	}

	/**
	 * Draws all coloured rectangles in the list allRectangles
	 */
	private void drawFromRectanglesList() {
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Main.ALL_RECTANGLES.toFirst();
		while (Main.ALL_RECTANGLES.hasAccess()) {
			RectangleColored current = Main.ALL_RECTANGLES.getContent();
			shapeRenderer.begin(current.getShapeType());
			shapeRenderer.setProjectionMatrix(camera.combined);
			shapeRenderer.setColor(current.getColor());
			shapeRenderer.rect(current.x, current.y, current.width, current.height);
			shapeRenderer.end();
			Main.ALL_RECTANGLES.next();
		}
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}

	/**
	 * Takes a list of type List<WorldObject> and disposes of all textures inside the object as well as the
	 * holdable Object they may be currently holding
	 * <p>
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
				// checks if the current Holdable is a plate. And if so, then all the objects as well as their Textures are deleted
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
	 * Takes the players Array and disposes of the object's texture as well as the texture(s) of the object(s)
	 * it may be holding
	 */
	private void disposeOfTexturesFromPlayers() {
		for (Player player : Main.PLAYERS) {
			if (player != null) {
				player.getTexture().dispose();
				if (player.getHand() != null) {
					((WorldObject) player.getHand()).getTexture().dispose();
					// checks if the current Holdable is a plate. And if so, then all the objects as well as their Textures are deleted
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

	/**
	 * This method is called once after the program has finished executing. It makes sure that every object
	 * that was created throughout the game, as well as their textures are being properly discarded
	 */
	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		shapeRenderer.dispose();
		font.dispose();

		Main.getAllRectangles().toFirst();
		while (!Main.getAllRectangles().isEmpty()) Main.getAllRectangles().remove();

		// Dispose of all Textures
		disposeOfTexturesInList(STATIC_OBJECT_LISTS[0]);
		disposeOfTexturesInList(STATIC_OBJECT_LISTS[1]);
		disposeOfTexturesFromPlayers();
	}

	/**
	 * This method is called whenever the window is being resized. It updates the viewport's size
	 * <p>
	 * @param width the new width in pixels
	 * @param height the new height in pixels
	 */
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	// All Getters
	public static List<WorldObject>[] getStaticObjectLists() {
		return STATIC_OBJECT_LISTS;
	}
	public static List<RectangleColored> getAllRectangles() {
		return ALL_RECTANGLES;
	}
	public static Player[] getPlayers() {
		return PLAYERS;
	}
	public static GameController getGameController() {
		return gameController;
	}
	public static Vector3 getMousePosition() {
		return MOUSE_POSITION;
	}
	public static OrthographicCamera getCamera() {
		return camera;
	}
	public static Music getMusic() {
		return music;
	}
}