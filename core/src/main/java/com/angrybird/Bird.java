package com.angrybird;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.utils.Array;

public abstract class Bird {
    protected MainLevel level;
    protected Texture texture;
    public Body body;
    protected float textureWidth;
    protected float textureHeight;
    protected BodyDef cDef;
    protected float xpos;
    protected float ypos;
    protected float rad;
    public boolean isDragging = false;
    private Vector2 initialTouchPos;
    private boolean trajectoryVisible = false;
    private Array<Vector2> trajectoryPoints;
    private float stationaryTime = 0;
    public boolean hasLaunched = false;
    private float activityTime = 0;

    public Bird(MainLevel level, float xpos, float ypos) {
        this.level = level;
        this.xpos = xpos;
        this.ypos = ypos;
        initializeTexture();
        createCircle();
        trajectoryPoints = new Array<>();
        initialTouchPos = new Vector2(203,375);

        body.setUserData(this);
    }

    // Abstract method for subclasses to specify their texture
    protected abstract void initializeTexture();

    // Create Box2D body with circle shape
    protected void createCircle() {
        cDef = new BodyDef();
        cDef.type = BodyDef.BodyType.StaticBody;  // Start as static for dragging
        cDef.position.set(203 / MainLevel.ppm, 375 / MainLevel.ppm);

        body = level.world.createBody(cDef);

        CircleShape cShape = new CircleShape();
        cShape.setRadius(rad / MainLevel.ppm);

        body.createFixture(cShape, 1.0f);
        cShape.dispose();
    }

    // Render the bird texture at the body's position and rotation
    public void render(float delta) {
        level.game.getBatch().begin();

        Vector2 position = body.getPosition();
        float angle = body.getAngle() * MathUtils.radiansToDegrees;

        level.game.getBatch().draw(
            texture,
            position.x * level.ppm - textureWidth / 2,
            position.y * level.ppm - textureHeight / 2,
            textureWidth / 2, textureHeight / 2,
            textureWidth, textureHeight,
            1, 1,
            angle,
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false
        );

        level.game.getBatch().end();

        if (trajectoryVisible) {
            level.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            level.shapeRenderer.setColor(Color.RED);
            for (Vector2 point : trajectoryPoints) {
                level.shapeRenderer.circle(point.x * MainLevel.ppm, point.y * MainLevel.ppm, 5);
            }
            level.shapeRenderer.end();
        }

        Vector2 velocity = body.getLinearVelocity();

        if (hasLaunched) {
            activityTime += delta;
            // Only count stationary time if the bird has been launched
            if (velocity.len() < 0.1f) { // Bird is nearly stationary
                stationaryTime += delta;
                if (stationaryTime >= 1f) { // Destroy after 1 second of being stationary
                    level.world.destroyBody(body);
                    level.birds.removeValue(this, true);
                }
            } else {
                stationaryTime = 0; // Reset timer if the bird moves
            }

            if (activityTime > 8f) { // Destroy after 1 second of being stationary
                level.world.destroyBody(body);
                level.birds.removeValue(this, true);
            }
        }
    }

    public void startDrag(Vector2 cursorPosition) {
        Vector2 launchPosition = new Vector2(535 / MainLevel.ppm, 375/ MainLevel.ppm);
        Vector2 bodyPosition = body.getPosition();

        if (bodyPosition.epsilonEquals(launchPosition, 0.1f) && isCursorTouchingBody(cursorPosition)) {
            isDragging = true;
            body.setType(BodyDef.BodyType.StaticBody);
            trajectoryVisible = true;
        }
    }


    public void drag(Vector2 cursorPosition) {
        if (isDragging) {
            body.setTransform(cursorPosition.x, cursorPosition.y, body.getAngle());
            calculateTrajectory(cursorPosition.x, cursorPosition.y);
        }
    }

    public void release() {
        if (isDragging) {
            isDragging = false;
            hasLaunched = true;
            body.setType(BodyDef.BodyType.DynamicBody);
            trajectoryVisible = false; // Hide trajectory when released
            body.setLinearVelocity(calculateInitialVelocity());
        }
    }

    // Calculate initial velocity based on touch release
    private Vector2 calculateInitialVelocity() {
        Vector2 currentPos = body.getPosition().scl(MainLevel.ppm); // Convert to pixels
        Vector2 dragVector = initialTouchPos.cpy().sub(currentPos); // Drag vector
        float distance = dragVector.len(); // Distance from catapult center

        float maxSpeed = 20f; // Scale this for maximum speed
        float speed = Math.min(distance / 20f, maxSpeed); // Adjust scaling factor for desired feel
        Vector2 tempPosition = body.getPosition();

        return dragVector.nor().scl(speed).scl(1); // Scale and reverse direction
    }

    private void calculateTrajectory(float endX, float endY) {
        trajectoryPoints.clear();
        Vector2 startPos = body.getPosition();
        Vector2 initialVelocity = calculateInitialVelocity();

        float timeStep = 0.1f; // Interval for trajectory calculation
        for (int i = 0; i < 30; i++) { // Adjust the number of points for trajectory
            float time = i * timeStep;
            float gravity = level.world.getGravity().y; // Box2D gravity in meters/sec²

            Vector2 position = new Vector2(
                startPos.x + initialVelocity.x * time,
                startPos.y + initialVelocity.y * time + 0.5f * gravity * time * time
            );
            trajectoryPoints.add(position);
        }
    }


    public boolean isCursorTouchingBody(Vector2 cursorPos) {
        for (var fixture : body.getFixtureList()) {
            if (fixture.testPoint(cursorPos.x, cursorPos.y)) {
                return true;
            }
        }
        return false;
    }

    public boolean isReadyForRemoval() {
        return hasLaunched && stationaryTime >= 1f;
    }
}
