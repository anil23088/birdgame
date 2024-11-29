package com.angrybird;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class level_3 extends MainLevel {
    private Texture background;

    public level_3(MyAngryBird game) {
        super(game);

        background = new Texture("level 3 bg.jpg");
        Array<Bird> birds = new Array<>();
        birds.add(new RedBird(this, 450, 225));
        birds.add(new YellowBird(this, 375, 230));
        birds.add(new BlueBird(this, 300, 220));

        Array<Glass> glassBlocks = new Array<>();
        Array<Wood> woodBlocks = new Array<>();
        Array<Pig> pigs = new Array<>();

        float groundY = 203;

        createStructure(glassBlocks, woodBlocks, pigs, 900, groundY);

        setupLevel(birds, mergeMaterials(glassBlocks, woodBlocks), pigs);
    }

    private void createStructure(Array<Glass> glassBlocks, Array<Wood> woodBlocks, Array<Pig> pigs, float startX, float groundY) {
        // Base of the hut: Three vertical wooden blocks forming a simple frame
        woodBlocks.add(new Wood(this, startX, groundY + 102, 90));  // Left vertical wooden block
        woodBlocks.add(new Wood(this, startX + 92, groundY + 102, 90));  // Center vertical wooden block
        woodBlocks.add(new Wood(this, startX + 184, groundY + 102, 90));  // Right vertical wooden block

        // Horizontal wooden block at the top of the vertical blocks
        woodBlocks.add(new Wood(this, startX + 92, groundY + 215, 0));  // Top horizontal wooden block

        // Pig inside the hut's rectangular base
        pigs.add(new MediumPig(this, startX + 100, groundY + 100));

        // Roof: Unique glass setup with four glass blocks, creating a small pyramid-like structure
        glassBlocks.add(new Glass(this, startX + 40, groundY + 315, 45));  // Left sloped glass block
        glassBlocks.add(new Glass(this, startX + 140, groundY + 315, -45));  // Right sloped glass block

        // Adding additional glass blocks forming a triangular roof
        glassBlocks.add(new Glass(this, startX + 70, groundY + 370, 0));  // Center top glass block
        glassBlocks.add(new Glass(this, startX + 110, groundY + 370, 0));  // Another center glass block

        // Pig inside the roof section
        pigs.add(new MediumPig(this, startX + 100, groundY + 250));
    }

    private Array<Material> mergeMaterials(Array<Glass> glassBlocks, Array<Wood> woodBlocks) {
        Array<Material> allBlocks = new Array<>();
        allBlocks.addAll(glassBlocks);
        allBlocks.addAll(woodBlocks);
        return allBlocks;
    }

    @Override
    public void render(float delta) {
        super.game.getBatch().begin();
        super.game.getBatch().draw(background, 0, 0, MyAngryBird.WIDTH, MyAngryBird.HEIGHT);
        super.game.getBatch().end();

        super.render(delta);
    }
}
