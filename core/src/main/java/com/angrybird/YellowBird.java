package com.angrybird;

import com.badlogic.gdx.math.Vector2;

public class YellowBird extends Bird {

    private boolean hasAccelerated;

    public YellowBird(Vector2 position) {
        super(position, 70, "yellow_bird[1].png"); // Impact = 70
        this.hasAccelerated = false;
    }

    @Override
    public void launch(Vector2 start, Vector2 end) {
        super.launch(start, end);
        this.velocity.scl(1.2f); // Higher initial speed
    }

    public void accelerate() {
        if (!hasAccelerated && isFlying) {
            velocity.scl(1.5f); // Increase speed
            hasAccelerated = true;
            System.out.println("YellowBird accelerates!");
        }
    }

    @Override
    public void handleCollision(Object target) {
        if (target instanceof Block) {
            ((Block) target).takeDamage(impact);
        } else if (target instanceof Pig) {
            ((Pig) target).takeDamage(impact);
        }
    }
}
