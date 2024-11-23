package com.angrybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class FirstScreen implements Screen {
    final MyAngryBird game;
    OrthographicCamera camera;
    public SpriteBatch batch;
    private final Texture background;
    private Texture startButton;
    private Texture exit;

    public FirstScreen(MyAngryBird game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 800);
        batch = new SpriteBatch();

        background = new Texture("home.png");
        startButton = new Texture("playButton.png");
        exit = new Texture("exit.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 1500, 800);
        batch.draw(startButton, 170, 0, 400, 250);  // Play button
        batch.draw(exit, 1000, 100, 300, 80);       // Exit button
        batch.end();

        if (Gdx.input.justTouched()) {
            float x = Gdx.input.getX() * (1500f / Gdx.graphics.getWidth());
            float y = (800 - Gdx.input.getY() * (800f / Gdx.graphics.getHeight()));

            // Check play button bounds
            if (x >= 170 && x <= 570 && y >= 0 && y <= 250) {  // 170+400=570 for width
                game.setScreen(new LevelScreen(game)); // Change this line to open LevelScreen
                dispose();
            }

            // Check exit button bounds
            if (x >= 1000 && x <= 1300 && y >= 100 && y <= 180) {  // 1000+300=1300 for width, 100+80=180 for height
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        startButton.dispose();

        exit.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}


