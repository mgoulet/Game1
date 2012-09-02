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

	public SplashState(MainActivity activityReference) {
		super(activityReference);
		
		//create our splash scene
    	Scene splashScene = new Scene();
    	splashScene.setBackground(new Background(0.0f, 0.0f, 0.0f));
    	SceneContainer.getInstance().addScene(SceneType.SPLASH, splashScene);
    	
	}
	
	@Override
	public void initialize() {
		this.sceneType = SceneType.SPLASH;
	}
	
	@Override
	public void begin() {
		
		///setup font
		final Text bitmapText = new Text(160, 230,
				ResourceManager.getInstance().splashBitmapFont,
				"SPLASH SCREEN.",
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager());

		getScene().attachChild(bitmapText);
		
		activityReference.getEngine().setScene(getScene());
	}
	
	@Override
	public void end() {
		//empty
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
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		return false;
	}
    
}



