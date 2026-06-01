package bif.hia.screens;

import bif.hia.configs.GameConfig;
import bif.hia.graphics.AssetLoader;
import bif.hia.graphics.View;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import net.mgsx.gltf.scene3d.scene.SceneManager;

public class GameScreen extends ScreenAdapter {
    private SceneManager sceneManager;
    private View view;

    private DirectionalLight sun;
    private Vector3 sunDirection = GameConfig.DEFAULT_SUN_DIRECTION;

    @Override
    public void show() {
        sceneManager = GameConfig.configureSceneManager();

        view = new View();

        AssetLoader.init();
        // AssetLoader.queueModel();
        AssetLoader.finishLoading();

        sceneManager.setCamera(view.getCamera());

        sun = new DirectionalLight();
        sun.set(1f, 1f, 1f, sunDirection);
        sceneManager.environment.add(sun);

        sceneManager.setAmbientLight(0.2f);
    }
}
