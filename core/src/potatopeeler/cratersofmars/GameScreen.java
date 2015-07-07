package potatopeeler.cratersofmars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * Created by kienme on 10/2/15.
 *
 * Update and render game.
 *
 */

public class GameScreen implements Screen{

    private GameWorld gameWorld;
    private GameRenderer gameRenderer;

    public GameScreen() {
        gameWorld=new GameWorld();
        gameRenderer=new GameRenderer(gameWorld);
        Gdx.input.setInputProcessor(new InputHandler());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        gameRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
