package potatopeeler.cratersofmars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by kienme on 10/2/15.
 *
 * Load and access assets.
 *
 */

public class AssetLoader {

    //replace with sprite sheet
    public static Texture texture;
    public static Texture craft_texture;

    public static TextureRegion bg;
    public static TextureRegion craft_image;

    public static void load() {
        //change to sprite sheet
        texture=new Texture(Gdx.files.internal("bg.png"));
        craft_texture=new Texture(Gdx.files.internal("craft.png"));

        bg=new TextureRegion(texture, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        bg.flip(false, true);
        craft_image=new TextureRegion(craft_texture, 0, 0, Constants.CRAFT_WIDTH, Constants.CRAFT_HEIGHT);
        craft_image.flip(false, true);
    }

    public static void dispose() {
        texture.dispose();
        craft_texture.dispose();
    }
}
