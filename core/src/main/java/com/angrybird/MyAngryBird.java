package com.angrybird;

import com.badlogic.gdx.Game;

public class MyAngryBird extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen(this));  // Start with FirstScreen
    }
}
