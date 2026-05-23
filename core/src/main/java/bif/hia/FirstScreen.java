package bif.hia;

import bif.hia.configs.GameConfig;
import bif.hia.graphics.AssetLoader;

import bif.hia.graphics.View;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneAsset;
import net.mgsx.gltf.scene3d.scene.SceneManager;

public class FirstScreen implements Screen {
    private SceneManager sceneManager;
    private View view;

    private SceneAsset sceneAsset;
    private Scene scene;

    private Vector3 camPosition =  new Vector3(0f, 5f, 0f);

    private float roundCycle = 0f;
    private float distance = 10f;
    private float centerHeight = 2f;
    private float sunDirection = 0f;

    DirectionalLight sun;

    @Override
    public void show() {
        sceneManager = GameConfig.configureSceneManager();

        view = new View();

        AssetLoader.init();
        AssetLoader.queueModel("gleuxus.glb");
        AssetLoader.finishLoading();

        sceneManager.setCamera(view.getCamera());

        sceneAsset = AssetLoader.getModel("gleuxus.glb");
        scene = new Scene(sceneAsset.scene);
        sceneManager.addScene(scene);

        sun = new DirectionalLight();
        sun.set(1f, 1f, 1f, (float) Math.sin(sunDirection), -0.8f, (float) Math.cos(sunDirection));
        sceneManager.environment.add(sun);

        sceneManager.setAmbientLight(0.2f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f, true);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camPosition.y += 3 * delta;
            System.out.printf("camY: %f\n", camPosition.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camPosition.y -= 3 * delta;
            System.out.printf("camY: %f\n", camPosition.y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            distance += 3 * delta;
            System.out.printf("distance: %f\n", distance);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            distance -= 3 * delta;
            System.out.printf("distance: %f\n", distance);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            centerHeight += 3 * delta;
            System.out.printf("centerHeight: %f\n", centerHeight);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            centerHeight -= 3 * delta;
            System.out.printf("centerHeight: %f\n", centerHeight);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            sunDirection += 3 * delta;
            sun.set(1f, 1f, 1f, (float) Math.sin(sunDirection), -0.8f, (float) Math.cos(sunDirection));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            sunDirection -= 3 * delta;
            sun.set(1f, 1f, 1f, (float) Math.sin(sunDirection), -0.8f, (float) Math.cos(sunDirection));
        }

        roundCycle += delta / 2;
        camPosition.x = (float) Math.sin(roundCycle) * distance;
        camPosition.z = (float) Math.cos(roundCycle) * distance;

        view.camera.position.set(camPosition);
        view.camera.lookAt(0f, centerHeight, 0f);
        view.camera.up.set(0f, 1f, 0f);

        if (roundCycle > 1000f) {
            roundCycle = 0f;
        }

        view.update();
        sceneManager.update(delta);
        sceneManager.render();
    }

    @Override
    public void resize(int width, int height) {
        if(width <= 0 || height <= 0) return;

        Graphics.DisplayMode mode = Gdx.graphics.getDisplayMode();
        System.out.printf("Window width changed to: %d\n", mode.width);
        System.out.printf("Window height changed to: %d\n", mode.height);

        view.camera.viewportWidth = width;
        view.camera.viewportHeight = height;
        view.update();
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        if (sceneManager != null) sceneManager.dispose();
    }
}
