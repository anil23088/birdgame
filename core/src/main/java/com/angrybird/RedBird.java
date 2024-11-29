package com.angrybird;
import com.badlogic.gdx.graphics.Texture;


public class RedBird extends Bird {
    public RedBird(MainLevel level, float xpos, float ypos) {
        super(level, xpos, ypos);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("red_bird[1].png");
        textureWidth = 47;
        textureHeight = 47;
        rad = 20;
    }
}
