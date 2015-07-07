package potatopeeler.cratersofmars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by kienme on 10/2/15.
 *
 * Render game to screen.
 * The real hero. Does the dirty job. The real hero.
 *
 */

public class GameRenderer {

    private OrthographicCamera cam;

    //reference to the game world and its objects
    private GameWorld gameWorld;
    private Craft craft;
    private Terrain terrain;

    //image to render
    private TextureRegion bg;
    private TextureRegion craft_image;
    //renderer of image
    private SpriteBatch spriteBatch;
    //renders shapes
    private ShapeRenderer shapeRenderer;

    public GameRenderer(GameWorld gameWorld) {

        //game view
        cam=new OrthographicCamera();
        cam.setToOrtho(true, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

        //game world and objects used
        this.gameWorld=gameWorld;
        craft=gameWorld.getCraft();
        terrain=gameWorld.getTerrain();

        //rendering stuff
        bg=AssetLoader.bg;
        craft_image=AssetLoader.craft_image;
        shapeRenderer=new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        spriteBatch=new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderBg();
        renderCraters();
        renderCraft(delta);
    }

    private void renderBg() {

        spriteBatch.begin();
        spriteBatch.disableBlending();
        spriteBatch.draw(bg, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        spriteBatch.end();
    }

    private void renderCraters() {

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(160/255f, 60/255f, 80/255f, 1);

        for(int i=0; i<3; ++i) {
                for (int q=terrain.getCraterFrom(i); q<terrain.getCraterTo(i); ++q) {
                    if (q<((terrain.getCraterTo(i)-terrain.getCraterFrom(i))/2))
                        shapeRenderer.rect(q, terrain.generateBoundary(i, q),
                                1, Constants.GAME_HEIGHT-terrain.generateBoundary(i, q));
                    else
                        shapeRenderer.rect(q, terrain.generateBoundary(i, q+1),
                                1, Constants.GAME_HEIGHT-terrain.generateBoundary(i, q + 1));
                }

                shapeRenderer.rect(terrain.getCraterTo(i), Constants.TERRAIN_Y_LEVEL,
                        terrain.getCraterWidth(i)/4, Constants.GAME_HEIGHT-Constants.TERRAIN_Y_LEVEL);

                if(i>0)
                    shapeRenderer.rect(terrain.getCraterFrom(i), Constants.TERRAIN_Y_LEVEL,
                            -1*terrain.getCraterWidth(i-1)/2, Constants.GAME_HEIGHT-Constants.TERRAIN_Y_LEVEL);
                else
                    shapeRenderer.rect(terrain.getCraterFrom(i), Constants.TERRAIN_Y_LEVEL,
                            -terrain.getCraterWidth(2)/2, Constants.GAME_HEIGHT-Constants.TERRAIN_Y_LEVEL);
        }

        shapeRenderer.end();
    }

    private void renderCraft(float delta) {
        spriteBatch.begin();
        spriteBatch.disableBlending();


        //if(terrain.craftInCrater(Constants.CRAFT_X)!=-1)
            spriteBatch.draw(craft_image, Constants.CRAFT_X, craft.getCraftY(), 0, Constants.CRAFT_HEIGHT, Constants.CRAFT_WIDTH, Constants.CRAFT_HEIGHT, 1, 1, craft.getRotation());



        /*else {
            if(terrain.craftInCrater(Constants.CRAFT_X+Constants.CRAFT_WIDTH)!=-1)
                spriteBatch.draw(craft_image, Constants.CRAFT_X, craft.getCraftY(), Constants.CRAFT_WIDTH*0, Constants.CRAFT_HEIGHT, Constants.CRAFT_WIDTH, Constants.CRAFT_HEIGHT, 1, 1, *//*0.27f*(Constants.CRAFT_X+Constants.CRAFT_WIDTH-terrain.getCraterFrom(terrain.craftInCrater(Constants.CRAFT_X+Constants.CRAFT_WIDTH)))*//*craft.getRotation());

            else
                spriteBatch.draw(craft_image, Constants.CRAFT_X, craft.getCraftY(), Constants.CRAFT_WIDTH, Constants.CRAFT_HEIGHT);
        }
*/


        spriteBatch.end();
    }
}
