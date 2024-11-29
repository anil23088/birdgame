package com.angrybird;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LoadingScreen implements Screen {

    private final MyAngryBird game;
    private float progress;  // Progress percentage
    private float elapsedTime;  // To track how much time has passed
    private Texture backgroundTexture;  // The loading background texture
    private BitmapFont font; // Font for the loading text
    private GlyphLayout layout; // For measuring text size
    private ShapeRenderer shapeRenderer;
    private Viewport viewport;
    private OrthographicCamera camera;

    public LoadingScreen(MyAngryBird game) {
        this.game = game;
        this.progress = 0;  // Start progress at 0%
        this.elapsedTime = 0;  // Start the timer at 0
        camera = new OrthographicCamera();
        viewport = new StretchViewport(800, 480, camera); // Set the virtual width and height
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        shapeRenderer = new ShapeRenderer();
        layout = new GlyphLayout();
    }

    @Override
    public void show() {
        backgroundTexture = new Texture("load1.png");  // The loading screen background

        // Initialize BitmapFont
        font = new BitmapFont(Gdx.files.internal("ui/font.fnt")); // Replace with your font file path
        font.getData().setScale(1.5f); // Scale the font size if necessary
    }

    @Override
    public void render(float delta) {
        // Update elapsed time
        elapsedTime += delta;

        // Update progress based on elapsed time
        float loadingDuration = 2f; // Loading duration in seconds
        progress = (elapsedTime / loadingDuration) * 100;

        // Ensure progress doesn't exceed 100%
        if (progress > 100) {
            progress = 100;
        }

        // Clear the screen
        ScreenUtils.clear(0, 0, 0, 1);  // Set the background color to black

        // Update camera and viewport
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        // Begin rendering
        game.getBatch().begin();

        // Draw the background to fill the screen using the viewport's dimensions
        game.getBatch().draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        game.getBatch().end(); // End batch for textures

        // Draw the shapes for the progress bar
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled); // Start filling shapes

        // Progress bar dimensions
        float progressBarWidth = 600;  // Total width of the progress bar
        float progressBarHeight = 30;   // Height of the progress bar
        float progressBarX = (viewport.getWorldWidth() - progressBarWidth) / 2;  // Center the progress bar horizontally
        float progressBarY = viewport.getWorldHeight() * 0.05f;
        float cornerRadius = 15; // Set the corner radius (half of the height can work well)

        // Draw the background of the progress bar
        shapeRenderer.setColor(Color.DARK_GRAY); // Progress bar background color
        drawRoundedRect(shapeRenderer, progressBarX, progressBarY, progressBarWidth, progressBarHeight, cornerRadius);

        // Draw the filled part of the progress bar
        float filledWidth = progressBarWidth * (progress / 100f);  // Calculate the filled width based on progress
        shapeRenderer.setColor(Color.GREEN); // Filled part color
        drawRoundedRect(shapeRenderer, progressBarX, progressBarY, filledWidth, progressBarHeight, cornerRadius);

        shapeRenderer.end(); // End drawing shapes

        // Draw the loading text above the progress bar
        game.getBatch().begin();
        font.getData().setScale(1f);
        layout.setText(font, "Loading...");
        float textX = (viewport.getWorldWidth() - layout.width) / 2; // Center the text
        float textY = progressBarY + progressBarHeight + 40; // Position above the progress bar
        font.draw(game.getBatch(), "Loading...", textX, textY);
        game.getBatch().end();

        // After the loading duration, switch to the next screen
        if (elapsedTime >= loadingDuration) {
            game.setScreen(new FirstScreen(game));  // Change to your main menu or next screen
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        // Dispose of textures to avoid memory leaks
        backgroundTexture.dispose();
        font.dispose(); // Dispose of the font
        shapeRenderer.dispose(); // Dispose of the ShapeRenderer
    }

    private void drawRoundedRect(ShapeRenderer shapeRenderer, float x, float y, float width, float height, float radius) {
        // Draw the rectangle with rounded corners
        shapeRenderer.rect(x + radius, y, width - 2 * radius, height); // Center rectangle
        shapeRenderer.arc(x + radius, y + radius, radius, 180, 90); // Top left corner
        shapeRenderer.arc(x + width - radius, y + radius, radius, 270, 90); // Top right corner
        shapeRenderer.rect(x, y + radius, radius, height - 2 * radius); // Left side
        shapeRenderer.rect(x + width - radius, y + radius, radius, height - 2 * radius); // Right side
        shapeRenderer.arc(x + radius, y + height - radius, radius, 90, 90); // Bottom left corner
        shapeRenderer.arc(x + width - radius, y + height - radius, radius, 0, 90); // Bottom right corner
    }
}

