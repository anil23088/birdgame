package com.angrybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class Block {
    protected Texture texture;
    protected Vector2 position;
    protected int durability; // Number of hits the block can take
    protected float width;    // Optional: width of the block
    protected float height;   // Optional: height of the block

    public Block(Vector2 position, int durability, String texturePath) {
        this.position = position;
        this.durability = durability;
        this.texture = new Texture(texturePath);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
    }

    // Take damage and reduce durability
    public void takeDamage(int damage) {
        durability -= damage;
        if (durability < 0) durability = 0;  // Ensure durability does not go below 0
    }

    // Check if the block is destroyed
    public boolean isDestroyed() {
        return durability <= 0;
    }

    // Render the block
    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);  // Adjust size based on texture
    }

    // Clean up resources
    public void dispose() {
        texture.dispose();
    }
}
