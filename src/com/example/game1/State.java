package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import android.view.KeyEvent;

import com.example.game1.SceneContainer.SceneType;

public abstract class State {
	
	protected MainActivity activityReference;

	public State(MainActivity activityReference) {
		this.activityReference = activityReference;
	}

	abstract public void begin();
	
	abstract public void end();

	abstract public boolean onKeyDown(int keyCode, KeyEvent event);

	abstract public Scene getScene();
	
	abstract public void setScene(Scene scene);
	
}


