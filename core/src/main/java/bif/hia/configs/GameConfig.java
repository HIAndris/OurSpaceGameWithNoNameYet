package bif.hia.configs;

import bif.hia.graphics.View;
import com.badlogic.gdx.graphics.g3d.shaders.DepthShader;
import com.badlogic.gdx.math.Vector3;
import net.mgsx.gltf.scene3d.scene.SceneManager;
import net.mgsx.gltf.scene3d.shaders.PBRDepthShaderProvider;
import net.mgsx.gltf.scene3d.shaders.PBRShaderConfig;
import net.mgsx.gltf.scene3d.shaders.PBRShaderProvider;

public class GameConfig {
    public static final int VIEWPORT_MIN_WORLD_WIDTH = 600;
    public static final int VIEWPORT_MIN_WORLD_HEIGHT = 400;
    public static final View.CameraMode DEFAULT_CAMERA_MODE = View.CameraMode.TOP_DOWN;

    // CAMERA_MODE = TOP_DOWN
    public static final int FOV_TD = 70;
    public static final Vector3 CAMERA_TD_POSITION = new Vector3(0, 10, 20);
    public static final Vector3 CAMERA_TD_LOOK_AT = new Vector3(0, 0, 0);
    public static final float CAMERA_TD_NEAR = 0.1f;
    public static final float CAMERA_TD_FAR = 100f;

    // CAMERA_MODE = INSPECT
    public static final int FOV_INS = 60;
    public static final Vector3 CAMERA_INS_POSITION = new Vector3(0, 5, 0);
    public static final Vector3 CAMERA_INS_LOOK_AT = new Vector3(0, 0, 0);
    public static final float CAMERA_INS_NEAR = 0.1f;
    public static final float CAMERA_INS_FAR = 100f;

    public static final int NUM_BONES = 60;

    private GameConfig() {}

    public static SceneManager configureSceneManager() {
        PBRShaderConfig config = PBRShaderProvider.createDefaultConfig();
        DepthShader.Config depthConfig = PBRDepthShaderProvider.createDefaultConfig();

        config.numBones = NUM_BONES;
        depthConfig.numBones = NUM_BONES;

        return new SceneManager(
            new PBRShaderProvider(config),
            new PBRDepthShaderProvider(depthConfig)
        );
    }
}
