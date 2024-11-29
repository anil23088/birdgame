package com.angrybird;


import com.badlogic.gdx.graphics.Texture;

public class Glass extends Material {
    public Glass(MainLevel level, float posX, float posY, float rotation) {
        super(level, posX, posY, rotation);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("glassblock.png");
        width = 204;  // Width for Glass material
        height = 20; // Height for Glass material
    }

    @Override
    protected void initializeHitPoints() {
        hitPoints = 1; // Glass requires 1 hit
    }
}

