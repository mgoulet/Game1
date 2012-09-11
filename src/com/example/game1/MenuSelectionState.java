package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import android.opengl.GLES20;
import android.view.KeyEvent;

import com.example.game1.SceneContainer.SceneType;
import com.example.game1.StateManager.StateType;

public class MenuSelectionState extends State implements IOnMenuItemClickListener {

	//Our scene
	MenuSelectionScene menuSelectionScene;
	
	public MenuSelectionState(MainActivity activityReference) {
		super(activityReference);
		
		activityReference.getEngine().registerUpdateHandler(new FPSLogger());

	}
	
	@Override
	public Scene getScene() {
		return this.menuSelectionScene;
	}
	
	@Override
	public void setScene(Scene scene) {
		this.menuSelectionScene = (MenuSelectionScene)scene;
	}	

	@Override
	public void begin() {
		ResourceManager.getInstance().playMenuMusic();
		
    	//trigger scene change at the engine level
    	activityReference.getEngine().setScene(menuSelectionScene);
    	
    	menuSelectionScene.prepareSceneForMenu();
    	menuSelectionScene.menuOptionsScene.setOnMenuItemClickListener(this);
    	
	}
	
	@Override
	public void end() {
		ResourceManager.getInstance().stopMenuMusic();
		
		menuSelectionScene.cleanupAfterMenu();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
    	{
    		System.exit(0);	
    	}
		
		return true;
	}

    @Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		
    	switch(pMenuItem.getID()) {
			case MenuSelectionScene.MENU_PLAY:
				StateManager.getInstance().switchState(StateType.GAME_INTRO);
				return true;
			case MenuSelectionScene.MENU_QUIT:
				// End Activity.
				System.exit(0);
				return true;
			default:
				return false;
		}
    }
	
}


