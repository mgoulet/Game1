package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import android.view.KeyEvent;

import com.example.game1.SceneContainer.SceneType;

public abstract class State  implements IOnMenuItemClickListener {
	
	protected MainActivity activityReference;
	
	protected SceneType sceneType;
	
	public State(MainActivity activityReference) {
		this.activityReference = activityReference;
	}
	
	abstract public void initialize();

	abstract public void begin();
	
	abstract public void end();

	abstract public boolean onKeyDown(int keyCode, KeyEvent event);
	
	abstract public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY);

	public final SceneType getSceneType() {
		return this.sceneType;
	}
	
	public final Scene getScene() {
		return (SceneContainer.getInstance().getScene(this.sceneType));
	}
}


