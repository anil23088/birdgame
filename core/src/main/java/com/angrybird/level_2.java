package com.angrybird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class level_2 extends MainLevel {
    private Texture background;
    public level_2(MyAngryBird game) {
        super(game);

        background = new Texture("level 2 bg.jpg");
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
        // Base of the hut: Two vertical wooden blocks
        woodBlocks.add(new Wood(this, startX, groundY +102 , 90)); // Left vertical wooden block
        woodBlocks.add(new Wood(this, startX + 184, groundY +102, 90)); // Right vertical wooden block

        // Horizontal wooden block on top of the vertical ones
        woodBlocks.add(new Wood(this, startX +92, groundY + 215, 0)); // Top horizontal wooden block

        // Pig inside the hut's rectangular base
        pigs.add(new MediumPig(this, startX + 100, groundY + 100));

        // Roof: Two glass blocks at 45-degree angles
        glassBlocks.add(new Glass(this, startX + 40, groundY + 315, 65)); // Left sloped glass block
        glassBlocks.add(new Glass(this, startX + 140, groundY + 315, -65)); // Right sloped glass block

        // Pig inside the triangular roof section
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
        super.game.getBatch().draw(background,0,0,MyAngryBird.WIDTH,MyAngryBird.HEIGHT);
        super.game.getBatch().end();

        super.render(delta);
    }
}
