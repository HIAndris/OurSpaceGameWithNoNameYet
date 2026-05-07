package bif.hia;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public static AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();

        setScreen(new FirstScreen());
    }
}
