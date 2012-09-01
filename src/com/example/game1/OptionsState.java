package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;

import android.view.KeyEvent;

import com.example.game1.SceneManager.SceneType;
import com.example.game1.StateManager.StateType;

public class OptionsState extends State {
	
	public OptionsState(MainActivity activityReference) {
		super(activityReference);
		
		//options scene
        Scene optionsScene = new Scene();
        optionsScene.setBackground(new Background(0.0f, 0.0f, 1.0f));
        SceneManager.getInstance().addScene(SceneType.OPTIONS, optionsScene);
        
	}
	
	@Override
	public void initialize() {
		this.sceneType = SceneType.OPTIONS;
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
		StateManager.getInstance().switchState(StateType.GAME_INTRO);
    }
	
    @Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		return false;
	}
}
