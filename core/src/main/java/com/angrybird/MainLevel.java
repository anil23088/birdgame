package com.angrybird;

//import Sprites.birds.Bird;
//import Sprites.pigs.Pig;
import com.angrybird.FirstScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class MainLevel implements ApplicationListener {

    public static final float ppm = 100f; // pixels per meter (for Box2D scaling)
    public World world;
    public SpriteBatch gameBatch;
    public OrthographicCamera camera;
    public Array<Bird> birds;
    public Array<Pig> pigs;
    public Texture backgroundTexture;
    public FirstScreen game;
    private int currentBirdIndex = 0;
    ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        world = new World(new Vector2(0, -9.8f), true); // Gravity (in this case, downward)
        gameBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer = new ShapeRenderer();
        // Load background and other static elements
        backgroundTexture = new Texture("background.png");

        // Initialize the birds and pigs for this level
        birds = new Array<>();
        pigs = new Array<>();

        // Add birds to the level (example)
        birds.add(new RedBird(this, 100, 300)); // RedBird is a subclass of Bird

        // Add pigs to the level
        pigs.add(new Pig(this, 500, 300, 2)); // Pig with 2 health
        pigs.add(new Pig(this, 600, 300, 1)); // Pig with 1 health
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1); // Gray background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        gameBatch.setProjectionMatrix(camera.combined);

        gameBatch.begin();
        gameBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameBatch.end();

        // Render pigs
        for (Pig pig : pigs) {
            pig.render();
        }

        // Render birds
        for (Bird bird : birds) {
            bird.render(Gdx.graphics.getDeltaTime());
        }

        // Render shapes using ShapeRenderer
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Example: Drawing a line from a bird's position
        if (!birds.isEmpty()) {
            Bird currentBird = birds.get(currentBirdIndex);
            if (!currentBird.hasLaunched) {
                shapeRenderer.setColor(Color.RED);  // Set color for the line
                shapeRenderer.line(currentBird.position.x, currentBird.position.y,
                    Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()); // Drag line
            }
        }

        shapeRenderer.end();

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        if (birds.size > currentBirdIndex) {
            Bird currentBird = birds.get(currentBirdIndex);
            if (!currentBird.hasLaunched) {
                if (Gdx.input.isTouched()) {
                    currentBird.startDrag(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
                } else {
                    currentBird.release();
                    currentBirdIndex++;
                }
            }
        }

        for (Pig pig : pigs) {
            if (!pig.isDead()) {
                for (Bird bird : birds) {
                    if (bird.hasLaunched) {
                        bird.impactPig();  // Apply impact on pigs
                    }
                }
            }
        }

        checkLevelStatus();
    }

    private void checkLevelStatus() {
        boolean allPigsDead = true;
        for (Pig pig : pigs) {
            if (!pig.isDead()) {
                allPigsDead = false;
                break;
            }
        }

        if (allPigsDead) {
            System.out.println("Level completed!");
        }

        boolean allBirdsUsed = true;
        for (Bird bird : birds) {
            if (!bird.hasLaunched) {
                allBirdsUsed = false;
                break;
            }
        }

        if (allBirdsUsed) {
            System.out.println("Game Over!");
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    //@Override
    //public void hide() {}
    @Override
    public void dispose() {
        world.dispose();
        gameBatch.dispose();
        backgroundTexture.dispose();
        for (Bird bird : birds) {
            bird.texture.dispose();
        }
        for (Pig pig : pigs) {
            pig.dispose();
        }
        shapeRenderer.dispose();
    }
}
