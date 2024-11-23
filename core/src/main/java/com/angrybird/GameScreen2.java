package com.angrybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen2 implements Screen {
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
    // New variable to determine the level
    private int level;

    public GameScreen2(MyAngryBird game, int level) {
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
        pause = new Texture("pause.png");
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0, 1500, 800);
        batch.draw(redBird,210,350,50,75);
        batch.draw(catapult, 200, 225, 80, 170);
        batch.draw(yellowBird, 50, 230, 50, 70);
        batch.draw(blueBird, 140, 230, 50, 70);
        batch.draw(woodblock, 40, 200, 250, 30);
      //  batch.draw(pig, 800, 375, 45, 40);
        batch.draw(pig, 820, 390, 45, 40);
        batch.draw(pig, 1160, 390, 45, 40);
        batch.draw(pig, 1000, 390, 45, 40);
        batch.draw(pig, 850, 220, 45, 40);
        batch.draw(pig, 1135, 220, 45, 40);
        //batch.draw(pig, 1000, 300, 45, 40);
        batch.draw(woodblock,800,210,45,150);
        batch.draw(woodblock,1185,210,45,150);
        batch.draw(woodblock,1000,210,45,150);
        batch.draw(glassblock,820,360,200,30);
        batch.draw(glassblock,1010,360,200,30);
        batch.draw(glassblock,800,360,20,100);
        batch.draw(glassblock,810,460,410,25);
        batch.draw(glassblock,1210,360,20,100);
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
