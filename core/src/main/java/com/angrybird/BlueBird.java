package com.angrybird;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class BlueBird extends Bird {

    private static final int SPLIT_IMPACT = 20; // Impact power for each smaller bird after split
    private boolean hasSplit = false;

    // Constructor to initialize the Blue Bird with specific properties
    public BlueBird(MainLevel level, float xpos, float ypos) {
        super(level, xpos, ypos); // Pass the level, and position of the bird to the superclass
        textureWidth = 40; // Width of the Blue Bird texture
        textureHeight = 40; // Height of the Blue Bird texture
        impactPower = 40; // Impact power for the initial Blue Bird
        initializeTexture(); // Initialize the texture
    }

    // Override the abstract method to initialize the Blue Bird's texture
    @Override
    protected void initializeTexture() {
        texture = new Texture("blue_bird.png"); // Replace with the correct file path to the Blue Bird image
    }

    // Override the abstract method to get the Blue Bird's impact power
    @Override
    protected int getImpactPower() {
        return impactPower;
    }

    // Override the onImpactWithStructure method to handle impacts with blocks
    @Override
    protected void onImpactWithStructure() {
        impactStructure(); // Call the method from the parent class to handle the impact with a block
        if (!hasSplit) {
            splitIntoSmallerBirds(); // Split the Blue Bird into smaller birds after impact with a structure
            hasSplit = true; // Ensure that the bird doesn't split more than once
        }
    }

    // Override the onImpactWithPig method to handle impacts with pigs
    @Override
    protected void onImpactWithPig() {
        impactPig(); // Call the method from the parent class to handle the impact with a pig
        if (!hasSplit) {
            splitIntoSmallerBirds(); // Split the Blue Bird into smaller birds after impact with a pig
            hasSplit = true; // Ensure that the bird doesn't split more than once
        }
    }

    // Method to simulate the Blue Bird's unique behavior of splitting into smaller birds
    private void splitIntoSmallerBirds() {
        // Example: Create 3 smaller birds and assign each one the split impact power
        System.out.println("BlueBird splits into smaller birds!");

        // You can create 3 smaller birds and add them to the level. Adjust positions as needed.
        for (int i = 0; i < 3; i++) {
            // You can adjust the spawn position and velocity of the smaller birds
            Vector2 splitPosition = body.getPosition().cpy().add(new Vector2(MathUtils.random(-1f, 1f), MathUtils.random(-1f, 1f)));
            BlueBird smallerBird = new BlueBird(level, splitPosition.x * MainLevel.ppm, splitPosition.y * MainLevel.ppm);
            smallerBird.impactPower = SPLIT_IMPACT; // Set the impact power of the smaller birds
            level.birds.add(smallerBird); // Add the smaller bird to the level
        }
    }

    // Override the render method if you need to add custom behavior for rendering BlueBird
    @Override
    public void render(float delta) {
        super.render(delta); // Calls the render method from Bird class
    }

    // Override the impactPig method to handle impact with pigs
    @Override
    public void impactPig() {
        super.impactPig(); // Call the parent class method to apply damage
        if (!hasSplit) {
            splitIntoSmallerBirds(); // Trigger the splitting behavior when hitting a pig
            hasSplit = true; // Ensure the bird doesn't split again
        }
    }

    // Override the impactStructure method to handle impact with structures
    @Override
    public void impactStructure() {
        super.impactStructure(); // Call the parent class method to apply damage
        if (!hasSplit) {
            splitIntoSmallerBirds(); // Trigger the splitting behavior when hitting a block or structure
            hasSplit = true; // Ensure the bird doesn't split again
        }
    }
}
