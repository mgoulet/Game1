package com.example.game1;

import java.util.HashMap;
import java.util.Map;

import org.andengine.entity.scene.Scene;

public class SceneManager {
	
	private static SceneManager instance;
	
	public static synchronized SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
    //Scene types
    public enum SceneType
    {
    	NONE,
    	SPLASH,
    	MENU,
    	OPTIONS,
    	GAME    	
    }
    
    //Our scene container
    private Map<SceneType, Scene> sceneMap;

    public void addScene(SceneType sceneType, Scene scene) {
    	sceneMap.put(sceneType, scene);
    }
    
    //Constructor
    private SceneManager() {
    	sceneMap = new HashMap<SceneType, Scene>();
    }
    
    public Scene getScene(SceneType sceneType) {
    	return sceneMap.get(sceneType);
    }
	
}



