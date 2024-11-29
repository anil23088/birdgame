package com.angrybird;

import com.badlogic.gdx.graphics.Texture;

public class Rock extends Material {
    public Rock(MainLevel level, float posX, float posY, float rotation) {
        super(level, posX, posY, rotation);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("steelblock.png");
        width = 204;  // Width for Rock material
        height = 20; // Height for Rock material (square)
    }

    @Override
    protected void initializeHitPoints() {
        hitPoints = 3;
    }
}
