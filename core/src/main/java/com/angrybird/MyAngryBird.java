package com.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MyAngryBird extends Game {
    OrthographicCamera camera;
    SpriteBatch batch;

    // Declare textures as instance variables
    private Texture catapult;
    private Texture redBird, blackBird, blueBird;
    private Texture background;
    private Texture pig;
    private Texture pause;
    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1500, 800);

        batch = new SpriteBatch();

        // Assign textures to instance variables
        //catapult = new Texture("catapult2.png");
        redBird = new Texture("ang.png");
        blueBird = new Texture("bb.png");
        blackBird = new Texture("black2.png");
        background=new Texture("background.png");
        pig=new Texture("bgd.png");
        pause=new Texture("pause.png");


    }
    @Override
    public void render() {
        // Clear the screen
        ScreenUtils.clear(1, 1, 1, 1);

        // Update camera
        camera.update();

        // Set the projection matrix for the batch
        batch.setProjectionMatrix(camera.combined);

        // Begin drawing
        batch.begin();
        batch.draw(background,0,0,1500,800);

        //batch.draw(catapult, 120,200,200,150);
        batch.draw(redBird, 180,220,200,200);
        batch.draw(blackBird, 50, 220,70,85);
        batch.draw(blueBird, 120, 220,80,80);
        batch.draw(pig,750,158,750,640);
        batch.draw(pause,0,730,100,60);
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        catapult.dispose();
        redBird.dispose();
        blueBird.dispose();
        blackBird.dispose();
    }
}

