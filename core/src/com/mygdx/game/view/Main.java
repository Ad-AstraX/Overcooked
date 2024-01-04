package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.control.GameController;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.WorldObject;
import com.mygdx.game.model.datastructures.List;
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
	private Viewport viewport;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	private static final Player[] players = new Player[2];
	private static final List<WorldObject>[] staticObjectLists = new List[] {new List<WorldObject>(), new List<WorldObject>()};
	private static final List<Rectangle> allProgressBars = new List<>();
	private GameController gameController;
	private Music music;
	private BitmapFont font;

	float stateTime;

	@Override
	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("Sound/Lynn Music Boulangerie - Gaming Background Music (HD).mp3"));
		music.play();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1950, 1425);
		viewport = new FitViewport(1950, 1425, camera);

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();

		gameController = new GameController(120f, 60, 1f);
		stateTime = 0f;
	}

	@Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();
		gameController.mainLoop(Gdx.graphics.getDeltaTime());

		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();

		if (players[0].getPosition().y < players[1].getPosition().y) {
			Player help = players[1];
			players[1] = players[0];
			players[0] = help;
		}

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		drawFromWorldObjectList(staticObjectLists[0]);
		drawFromPlayersArray();
		drawFromWorldObjectList(staticObjectLists[1]);


		font.getData().setScale(5f);
		font.draw(batch, GameController.getGame().getPayTotal() + " $", 200 - (float) (GameController.getGame().getPayGoal() + "$").length()/2 * 50f,1375);
		batch.end();

		allProgressBars.toFirst();
		while (allProgressBars.hasAccess()) {
			Rectangle current = allProgressBars.getContent();
			drawRectangle(ShapeRenderer.ShapeType.Filled, Color.WHITE, current.x, current.y, 100, current.height);
			drawRectangle(ShapeRenderer.ShapeType.Filled, new Color(current.width/100f, 1-current.width/100f, 0, 1),
						  current.x, current.y, current.width, current.height);
			drawRectangle(ShapeRenderer.ShapeType.Line, Color.BLACK, current.x, current.y, 100, current.height);
			allProgressBars.next();
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
	 * Takes a list of type List<WorldObject> and disposes of all textures inside
	 * @param list the given list
	 */
	private void disposeOfTexturesInList(List<WorldObject> list) {
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
			list.next();
		}
	}

	/**
	 * Takes an Array of Player objects and disposes of all textures inside
	 * @param players the given array of players
	 */
	private void disposeOfTexturesFromPlayers(Player[] players) {
		for (Player player : players) {
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

	@Override
	public void dispose() {
		batch.dispose();
		music.dispose();
		shapeRenderer.dispose();

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
	public static List<Rectangle> getAllProgressBars() {
		return allProgressBars;
	}
	public static Player[] getPlayers() {
		return players;
	}
}