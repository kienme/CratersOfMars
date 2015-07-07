package potatopeeler.cratersofmars;

import com.badlogic.gdx.Game;

/**
 * Main class. All stuff begins here
 *
 * */


public class CratersOfMars extends Game {

	@Override
	public void create () {
        AssetLoader.load();
		setScreen(new GameScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
