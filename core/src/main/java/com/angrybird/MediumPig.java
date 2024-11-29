package com.angrybird;
import com.badlogic.gdx.graphics.Texture;

public class MediumPig extends Pig {
    public MediumPig(MainLevel level, float posx, float posy) {
        super(level, posx, posy);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("medium pig.png");
        textureWidth = 80;  // Width for MediumPig
        textureHeight = 79; // Height for MediumPig
        rad = 37;
    }

    @Override
    protected void initializeHitPoints() {
        hitPoints = 2;
    }
}
