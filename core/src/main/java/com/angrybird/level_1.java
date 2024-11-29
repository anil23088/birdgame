//package com.angrybird;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.utils.Array;
//
//public class level_1 extends MainLevel {
//    private Texture background;
//    public level_1(MyAngryBird game) {
//        super(game);
//
//        background = new Texture("level 1 bg.png");
//
//        Array<Bird> birds = new Array<>();
//        birds.add(new RedBird(this, 120, 225));
//        birds.add(new YellowBird(this, 100, 230));
//        birds.add(new BlueBird(this, 60, 220));
//
//        Array<Material> glassBlocks = new Array<>();
//        Array<Pig> pigs = new Array<>();
//
//        float groundY = 300;
//        createGlassSetup(glassBlocks, pigs, 900, groundY);
//        createGlassSetup(glassBlocks, pigs, 1300, groundY);
//
//        setupLevel(birds, glassBlocks, pigs);
//    }
//
//    private void createGlassSetup(Array<Material> glassBlocks, Array<Pig> pigs, float startX, float groundY) {
//        glassBlocks.add(new Glass(this, startX, groundY, 90));
//        glassBlocks.add(new Glass(this, startX + 200, groundY, 90));
//        pigs.add(new MediumPig(this, startX + 102, groundY + 20));
//        glassBlocks.add(new Glass(this, startX, groundY + 204, 0));
//        glassBlocks.add(new Glass(this, startX + 200, groundY + 204, 0));
//
//    }
//
//
//    @Override
//    public void render(float delta) {
//        super.game.getBatch().begin();
//        super.game.getBatch().draw(background,0,0,MyAngryBird.WIDTH,MyAngryBird.HEIGHT);
//        super.game.getBatch().end();
//
//        super.render(delta);
//    }
//}

package com.angrybird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class level_1 extends MainLevel {
    private Texture background;

    public level_1(MyAngryBird game) {
        super(game);

        background = new Texture("level 1 bg.png");

        Array<Bird> birds = new Array<>();
        birds.add(new RedBird(this, 120, 225));
        birds.add(new YellowBird(this, 100, 230));
        birds.add(new BlueBird(this, 60, 220));

        Array<Material> glassBlocks = new Array<>();
        Array<Pig> pigs = new Array<>();

        float groundY = 300;

        // Further reduced startX values for leftward movement
        createGlassSetup(glassBlocks, pigs, 400, groundY); // Moved from 900 to 600
        createGlassSetup(glassBlocks, pigs, 800, groundY); // Moved from 1300 to 1000

        setupLevel(birds, glassBlocks, pigs);
    }

    private void createGlassSetup(Array<Material> glassBlocks, Array<Pig> pigs, float startX, float groundY) {
        glassBlocks.add(new Glass(this, startX, groundY, 90));
        glassBlocks.add(new Glass(this, startX + 200, groundY, 90));
        pigs.add(new MediumPig(this, startX + 102, groundY + 20));
        glassBlocks.add(new Glass(this, startX, groundY + 204, 0));
        glassBlocks.add(new Glass(this, startX + 200, groundY + 204, 0));
    }

    @Override
    public void render(float delta) {
        super.game.getBatch().begin();
        super.game.getBatch().draw(background, 0, 0, MyAngryBird.WIDTH, MyAngryBird.HEIGHT);
        super.game.getBatch().end();

        super.render(delta);
    }
}
