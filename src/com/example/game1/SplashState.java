package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.math.MathUtils;

import android.view.KeyEvent;

import com.example.game1.SceneContainer.SceneType;
import com.example.game1.StateManager.StateType;

public class SplashState extends State {

	//Our scene
	protected SplashScene splashScene;
	
	public SplashState(MainActivity activityReference) {
		super(activityReference);
   	
	}
	
	@Override
	public Scene getScene() {
		return this.splashScene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.splashScene = (SplashScene)scene;
	}
	
	@Override
	public void begin() {
		
		splashScene.prepareSceneForSplash();
		
		activityReference.getEngine().setScene(splashScene);
	}
	
	@Override
	public void end() {
		//Do nothing
		
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
    	{
    		StateManager.getInstance().switchState(StateType.MENU);	
    	}
		
		return true;
	}
    
}



