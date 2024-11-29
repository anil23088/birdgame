package com.angrybird;


import com.badlogic.gdx.graphics.Texture;

public class Wood extends Material {
    public Wood(MainLevel level, float posX, float posY, float rotation) {
        super(level, posX, posY, rotation);
    }

    @Override
    protected void initializeTexture() {
        texture = new Texture("woodblock.png");
        width = 204;  // Width for Wood material
        height = 20; // Height for Wood material
    }

    @Override
    protected void initializeHitPoints() {
        hitPoints = 2;
    }
}
