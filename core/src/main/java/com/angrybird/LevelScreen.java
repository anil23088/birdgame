package com.angrybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelScreen implements Screen {
    final MyAngryBird game;
    OrthographicCamera camera;
    SpriteBatch batch;
    private final Texture background;
    private Texture level1Button;
    private Texture level2Button;
    private Texture level3Button;

    public LevelScreen(MyAngryBird game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 800);
        batch = new SpriteBatch();

        // Load textures
        background = new Texture("levelbkg.png"); // Your level background image
        level1Button = new Texture("level1.png");   // Button for Level 1
        level2Button = new Texture("level2.png");   // Button for Level 2
        level3Button = new Texture("level3.png");   // Button for Level 3
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 1500, 800); // Draw background
        batch.draw(level1Button, 600, 500, 400, 100); // Draw Level 1 button
        batch.draw(level2Button, 600, 350, 400, 100); // Draw Level 2 button
        batch.draw(level3Button, 600, 200, 400, 100); // Draw Level 3 button
        batch.end();

        // Handle input for level selection
        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX();
            float y = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert y-coordinate

            // Check if Level 1 button is clicked
            if (x >= 100 && x <= 600 && y >= 500 && y <= 600) {
                game.setScreen(new GameScreen(game, 1)); // Go to GameScreen for Level 1
                dispose();
            }

            // Check if Level 2 button is clicked
            if (x >= 100 && x <= 600 && y >= 350 && y <= 450) {
                game.setScreen(new GameScreen(game, 2)); // Go to GameScreen for Level 2
                dispose();
            }

            // Check if Level 3 button is clicked
            if (x >= 100 && x <= 600 && y >= 200 && y <= 300) {
                game.setScreen(new GameScreen(game, 3)); // Go to GameScreen for Level 3
                dispose();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        level1Button.dispose();
        level2Button.dispose();
        level3Button.dispose();
    }

    @Override
    public void show()
    {

    }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }
    @Override
    public void hide() {

    }
}

