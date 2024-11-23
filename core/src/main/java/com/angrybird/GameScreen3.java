package com.angrybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen3 implements Screen {
    final MyAngryBird game;
    OrthographicCamera camera;
    SpriteBatch batch;

    private Texture redBird, yellowBird, blueBird;
    private Texture background;
    private Texture pig;
    private Texture pause;
    private Texture woodblock;
    private Texture catapult;
    private Texture glassblock;
    private Texture steelblock;
    // New variable to determine the level
    private int level;

    public GameScreen3(MyAngryBird game, int level) {
        this.game = game;
        this.level = level; // Store the level
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 800);

        batch = new SpriteBatch();

        // Load all textures
        redBird = new Texture("red_bird[1].png");
        catapult=new Texture("catp[1].png");
        blueBird = new Texture("blue_bird[1].png");
        yellowBird = new Texture("yellow_bird[1].png");
        background = new Texture("background.png"); // You might want to use different backgrounds per level
        pig = new Texture("big pig.png");
        woodblock=new Texture("woodblock.png");
        glassblock=new Texture("glassblock.png");
        steelblock=new Texture("steelblock.png");
        pause = new Texture("pause.png");
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 1500, 800);
        batch.draw(redBird, 210, 350, 50, 75);
        batch.draw(catapult, 200, 225, 80, 170);
        batch.draw(yellowBird, 50, 230, 50, 70);
        batch.draw(blueBird, 140, 230, 50, 70);
        batch.draw(pig, 820, 420, 45, 40);
        batch.draw(pig, 1160, 420, 45, 40);
        batch.draw(pig, 1000, 420, 45, 40);
        batch.draw(pig, 850, 260, 45, 40);
        batch.draw(pig, 1135, 260, 45, 40);
        batch.draw(pig, 820, 485, 45, 40);
        batch.draw(pig, 1160, 485, 45, 40);

        // Draw steel blocks (previously wood blocks)
        batch.draw(steelblock, 800, 210, 45, 150);
        batch.draw(steelblock, 1185, 210, 45, 150);
        batch.draw(steelblock, 1000, 210, 45, 150);

        // Draw glass blocks
        batch.draw(glassblock, 820, 360, 200, 40);
        batch.draw(glassblock, 1010, 360, 200, 40);
        batch.draw(glassblock, 800, 360, 20, 100);
        batch.draw(glassblock, 810, 460, 410, 25);
        batch.draw(glassblock, 1210, 360, 20, 100);

        // Add triangle of glass blocks above
        batch.draw(glassblock, 1000, 485, 50, 20); // Top of the triangle
        batch.draw(glassblock, 975, 460, 25, 25);  // Left edge of triangle
        batch.draw(glassblock, 1025, 460, 25, 25); // Right edge of triangle

        // Draw wood blocks (previously steel blocks)
        batch.draw(woodblock, 820, 390, 45, 30);
        batch.draw(woodblock, 1160, 390, 45, 30);
        batch.draw(woodblock, 1000, 390, 45, 30);
        batch.draw(woodblock, 850, 220, 45, 40);
        batch.draw(woodblock, 1135, 220, 45, 40);

        // Draw pause button
        batch.draw(pause, 0, 730, 100, 60);
        batch.end();

        // Handle pause button click
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check pause button bounds
            if (x >= 0 && x <= 100 && y >= 730 && y <= 790) {
                game.setScreen(new FirstScreen(game)); // Return to first screen
                dispose();
            }
        }
    }
    @Override
    public void show() {

    }
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        redBird.dispose();
        blueBird.dispose();
        pig.dispose();
        background.dispose();
    }
}
