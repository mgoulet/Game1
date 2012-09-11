package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;

import android.view.KeyEvent;

import com.example.game1.SceneContainer.SceneType;
import com.example.game1.StateManager.StateType;

public class GameHighScoresState extends State{

	GameScene gameScene;
	
	public GameHighScoresState(MainActivity activityReference) {
		super(activityReference);
		
		//game scene already created...should have guard here
		
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

		StateManager.getInstance().switchState(StateType.MENU);

    }

	@Override
	public Scene getScene() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScene(Scene scene) {
		// TODO Auto-generated method stub
		
	}

}
