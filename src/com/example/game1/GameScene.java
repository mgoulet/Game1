package com.example.game1;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class GameScene extends Scene{

	MainActivity activityReference;
	
	public Choppa choppaJoe;
	public ChoppaControlla choppaJoeControlla;
	public AnalogOnScreenControl analogOnScreenControl;
	
	public GameHUD gameHud;
	
	public AutoParallaxBackground autoParallaxBackground;
	public VertexBufferObjectManager vertexBufferObjectManager;
	
	public GameScene(MainActivity activityReference) {
		super();
		
		this.activityReference = activityReference; 
		
		//parallax background
    	autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		vertexBufferObjectManager = activityReference.getVertexBufferObjectManager();
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerBack.getHeight(), ResourceManager.getInstance().mParallaxLayerBack, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, 80, ResourceManager.getInstance().mParallaxLayerMid, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-20.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerFront.getHeight(), ResourceManager.getInstance().mParallaxLayerFront, vertexBufferObjectManager)));

		this.setBackground(autoParallaxBackground);
		
		choppaJoe = new ChoppaJoe(360, 240);
		
		choppaJoeControlla = new ChoppaControlla(choppaJoe);
		
		gameHud = new GameHUD();
		
	}
	
	public void prepareSceneForIntro() {
		//add countdown text
		this.attachChild(gameHud.getIntroText());
		gameHud.getIntroTextModifier().reset();		
	}
	
	public void cleanupAfterIntro() {
		//remove intro text
		this.detachChild(gameHud.getIntroText());
	}
	
	public void prepareSceneForPlay() {
		this.attachChild(choppaJoe.getAnimatedSprite());
		this.setChildScene(choppaJoeControlla.getAnalogOnScreenControl());		
		EngineOptionsManager.getInstance().getCamera().setHUD(gameHud.getHud());
		
	}
	
	public void cleanupAfterPlay() {
		this.detachChildren();		
		this.clearChildScene();
		choppaJoe.getScaleModifier().reset();
		EngineOptionsManager.getInstance().getCamera().setHUD(null);
	}
	
}




