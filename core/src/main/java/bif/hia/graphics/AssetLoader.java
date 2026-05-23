package bif.hia.graphics;

import com.badlogic.gdx.assets.AssetManager;
import net.mgsx.gltf.loaders.glb.GLBAssetLoader;
import net.mgsx.gltf.loaders.gltf.GLTFAssetLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class AssetLoader {
    private static AssetManager assetManager;

    private AssetLoader() {}

    public static void init() {
        assetManager = new AssetManager();

        assetManager.setLoader(SceneAsset.class, ".glb", new GLBAssetLoader());
        assetManager.setLoader(SceneAsset.class, ".gltf", new GLTFAssetLoader());
    }

    public static void dispose() {
        if (assetManager != null) {
            assetManager.dispose();
            assetManager = null;
        }
    }

    public static void queueModel(String[] paths) {
        for (String path : paths) {
            assetManager.load(path, SceneAsset.class);
        }
    }

    public static void queueModel(String path) {
        assetManager.load(path, SceneAsset.class);
    }

    public static void finishLoading() {
        assetManager.finishLoading();
    }

    public static boolean update() {
        return assetManager.update();
    }

    public static SceneAsset getModel(String path) {
        return assetManager.get(path, SceneAsset.class);
    }
}
