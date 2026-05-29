package bif.hia.graphics;

import bif.hia.configs.GameConfig;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.EnumMap;
import java.util.Map;

public class View {
    public PerspectiveCamera camera;
    public Viewport viewport;

    private final Map<CameraMode, Runnable> applyCameraMode;

    private CameraMode cameraMode = CameraMode.TOP_DOWN;

    private float rotationDistance;

    public View() {
        applyCameraMode = new EnumMap<>(CameraMode.class);
        applyCameraMode.put(CameraMode.TOP_DOWN, this::applyTopDownCamera);
        applyCameraMode.put(CameraMode.INSPECT, this::applyInspectCamera);

        camera = new PerspectiveCamera();
        setCameraMode(GameConfig.DEFAULT_CAMERA_MODE);
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        viewport = new ExtendViewport(GameConfig.VIEWPORT_MIN_WORLD_WIDTH, GameConfig.VIEWPORT_MIN_WORLD_HEIGHT, camera);
    }

    public View(CameraMode srartCameraMode) {
        applyCameraMode = new EnumMap<>(CameraMode.class);
        applyCameraMode.put(CameraMode.TOP_DOWN, this::applyTopDownCamera);
        applyCameraMode.put(CameraMode.INSPECT, this::applyInspectCamera);

        camera = new PerspectiveCamera();
        setCameraMode(srartCameraMode);
        camera.viewportWidth = Gdx.graphics.getWidth();
        camera.viewportHeight = Gdx.graphics.getHeight();
        viewport = new ExtendViewport(GameConfig.VIEWPORT_MIN_WORLD_WIDTH, GameConfig.VIEWPORT_MIN_WORLD_HEIGHT, camera);
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public void update() {
        camera.update();
    }

    public CameraMode getCameraMode() {
        return cameraMode;
    }

    public void setCameraMode(CameraMode cameraMode) {
        if (cameraMode != null) {
            this.cameraMode = cameraMode;
            applyCameraMode.get(cameraMode).run();
        }
    }

    public enum CameraMode {
        TOP_DOWN,
        INSPECT
    }

    private void applyTopDownCamera() {
        rotationDistance = GameConfig.CAMERA_TD_POSITION.z;
        camera.fieldOfView = GameConfig.FOV_TD;
        camera.position.set(GameConfig.CAMERA_TD_POSITION);
        camera.lookAt(GameConfig.CAMERA_TD_LOOK_AT);
        camera.near = GameConfig.CAMERA_TD_NEAR;
        camera.far = GameConfig.CAMERA_TD_FAR;
        camera.update();
    }

    private void applyInspectCamera() {
        rotationDistance = GameConfig.CAMERA_INS_POSITION.z;
        camera.fieldOfView = GameConfig.FOV_INS;
        camera.position.set(GameConfig.CAMERA_INS_POSITION);
        camera.lookAt(GameConfig.CAMERA_INS_LOOK_AT);
        camera.near = GameConfig.CAMERA_INS_NEAR;
        camera.far = GameConfig.CAMERA_INS_FAR;
        camera.update();
    }

    public void rotateCamera(float deg) {
        camera.position.z = MathUtils.sinDeg(deg) * rotationDistance;
        camera.position.x = MathUtils.cosDeg(deg) * rotationDistance;
    }
}
