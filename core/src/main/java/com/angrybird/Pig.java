package com.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
//import levels.MainLevel;

public class Pig {
    private MainLevel level;
    private Texture texture;
    private Body body;
    private int health;
    private boolean isDead;

    public Pig(MainLevel level, float xpos, float ypos, int health) {
        this.level = level;
        this.health = health;
        this.isDead = false;

        texture = new Texture("big pig.png");
        createPigBody(xpos, ypos);
    }

    private void createPigBody(float xpos, float ypos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(xpos / MainLevel.ppm, ypos / MainLevel.ppm);

        body = level.world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
        shape.dispose();

        body.setUserData(this);
    }

    public void takeDamage(int damage) {
        if (!isDead) {
            health -= damage;
            if (health <= 0) {
                isDead = true;
                die();
            }
        }
    }

    private void die() {
        level.world.destroyBody(body);
    }

    public boolean isDead() {
        return isDead;
    }

    public void render() {
        if (!isDead) {
            level.gameBatch.begin();
            level.gameBatch.draw(texture, body.getPosition().x * MainLevel.ppm - texture.getWidth() / 2,
                body.getPosition().y * MainLevel.ppm - texture.getHeight() / 2);
            level.gameBatch.end();
        }
    }

    public void dispose() {
        texture.dispose();
    }
}
