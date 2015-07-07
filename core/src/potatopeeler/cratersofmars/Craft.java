package potatopeeler.cratersofmars;


import com.badlogic.gdx.math.Vector2;

/**
 * Created by kienme on 10/2/15.
 *
 * The hero.
 *
 */

public class Craft {

    private Vector2 speed;
    private Terrain terrain;

    private int craftY;
    private float rotation;

    public Craft(Terrain t) {

        speed=new Vector2(/*200*/500, 0);
        terrain=t;
        craftY=300;
        rotation=-45f;
    }

    public void update(float delta) {
        updateCraftY();
        rotateCraft();
    }

    private void updateCraftY() {
        if(terrain.craftInCrater(Constants.CRAFT_X)!=-1)
            craftY=terrain.generateBoundary(terrain.craftInCrater(Constants.CRAFT_X), Constants.CRAFT_X)-Constants.HOVER_HEIGHT-Constants.CRAFT_HEIGHT;

        else
            craftY=(Constants.TERRAIN_Y_LEVEL-Constants.HOVER_HEIGHT-Constants.CRAFT_HEIGHT);
    }

    private void rotateCraft() {

        if(terrain.craftInCrater(Constants.CRAFT_X)!=-1) {
            rotation=-90;
            while((Constants.HOVER_HEIGHT+craftY+(Constants.CRAFT_WIDTH*Math.sin(rotation*0.01745)))<(terrain.generateBoundary(terrain.craftInCrater(Constants.CRAFT_X), (int)(Constants.CRAFT_X+(Constants.CRAFT_WIDTH*Math.cos(rotation*0.01745))))))
                ++rotation;

            rotation-=28;
        }

        else if(terrain.craftInCrater(Constants.CRAFT_X+Constants.CRAFT_WIDTH)!=-1) {
            rotation=-90;
            while((Constants.HOVER_HEIGHT+craftY+(Constants.CRAFT_WIDTH*Math.sin(rotation*0.01745)))<(terrain.generateBoundary(terrain.craftInCrater(Constants.CRAFT_X+Constants.CRAFT_WIDTH), (int)(Constants.CRAFT_X+(Constants.CRAFT_WIDTH*Math.cos(rotation*0.01745))))))
                ++rotation;

            rotation-=28;
        }

        else rotation=0;
    }

    public float getSpeedX() {
        return speed.x;
    }

    public int getCraftY() {
        return craftY;
    }

    public float getRotation() {
        return rotation;
    }
}
