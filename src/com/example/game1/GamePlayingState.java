package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;

import android.view.KeyEvent;

import com.example.game1.SceneManager.SceneType;
import com.example.game1.StateManager.StateType;

public class GamePlayingState extends State {

	public GamePlayingState(MainActivity activityReference) {
		super(activityReference);
		
		//game scene already created...should have guard here
        
	}
	
	@Override
	public void initialize() {
		this.sceneType = SceneType.GAME;
	}
	
	@Override
	public void begin() {
		activityReference.getEngine().setScene(getScene());
	}
	
	@Override
	public void end() {

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
    	{
    		processBackButton();	
    	}
		
		return true;
	}
	
	private void processBackButton() {
		StateManager.getInstance().switchState(StateType.GAME_HIGHSCORES);
    }
	
    @Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		return false;
	}
}
