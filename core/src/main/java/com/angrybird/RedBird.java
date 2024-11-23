package com.angrybird;

import com.badlogic.gdx.math.Vector2;

public class RedBird extends Bird {

    public RedBird(Vector2 position) {
        super(position, 50, "red_bird[1].png"); // Impact = 50
    }

    @Override
    public void handleCollision(Object target) {
        if (target instanceof Block) {
            ((Block) target).takeDamage(impact); // Deal damage
        } else if (target instanceof Pig) {
            ((Pig) target).takeDamage(impact); // Damage a pig
        }
    }
}

