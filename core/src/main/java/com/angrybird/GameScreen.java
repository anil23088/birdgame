package com.angrybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final MyAngryBird game;
    OrthographicCamera camera;
    SpriteBatch batch;

    private Texture redBird, blackBird, blueBird;
    private Texture background;
    private Texture pig;
    private Texture pause;

    public GameScreen(MyAngryBird game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 800);

        batch = new SpriteBatch();

        // Load all textures
        redBird = new Texture("ang.png");
        blueBird = new Texture("bb.png");
        blackBird = new Texture("black2.png");
        background = new Texture("background.png");
        pig = new Texture("bgd.png");
        pause = new Texture("pause.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 1500, 800);
        batch.draw(redBird, 180, 220, 200, 200);
        batch.draw(blackBird, 50, 220, 70, 85);
        batch.draw(blueBird, 120, 220, 80, 80);
        batch.draw(pig, 750, 158, 750, 640);
        batch.draw(pause, 0, 730, 100, 60);
        batch.end();

        // Handle pause button click
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check pause button bounds
            if (x >= 0 && x <= 100 && y >= 730 && y <= 790) {
                game.setScreen(new FirstScreen(game));
                dispose();
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        redBird.dispose();
        blueBird.dispose();
        blackBird.dispose();
        pig.dispose();
        pause.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
