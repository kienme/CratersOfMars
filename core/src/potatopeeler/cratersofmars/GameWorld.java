package potatopeeler.cratersofmars;

/**
 * Created by kienme on 10/2/15.
 *
 * Handle update of game objects.
 *
 */

public class GameWorld {

    private Craft craft;
    private Terrain terrain;

    public GameWorld() {
        terrain=new Terrain();
        craft=new Craft(terrain);
    }

    public void update(float delta) {
        craft.update(delta);
        terrain.update(delta, craft.getSpeedX());
    }

    public Craft getCraft() {
        return craft;
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
