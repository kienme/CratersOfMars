package potatopeeler.cratersofmars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import potatopeeler.cratersofmars.CratersOfMars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=800;
        config.height=480;
        config.title="Craters Of Mars";
		new LwjglApplication(new CratersOfMars(), config);
	}
}
