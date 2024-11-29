package com.angrybird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
public class MyAngryBird extends Game {
    public static final int WIDTH = 800;  // Screen width
    public static final int HEIGHT = 480; // Screen height
    public Screen currentLevel;

    private SpriteBatch batch;
    private BitmapFont font;
    private AssetManager assetManager;
    private Camera camera;
    private Viewport viewport;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        assetManager = new AssetManager();
        setScreen(new LoadingScreen(this));
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WIDTH, HEIGHT, camera);
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
