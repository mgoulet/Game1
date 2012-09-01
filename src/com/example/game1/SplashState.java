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

import com.example.game1.SceneManager.SceneType;
import com.example.game1.StateManager.StateType;

public class SplashState extends State {

	public SplashState(MainActivity activityReference) {
		super(activityReference);
		
		//create our splash scene
    	Scene splashScene = new Scene();
    	splashScene.setBackground(new Background(1.0f, 0.0f, 0.0f));
    	SceneManager.getInstance().addScene(SceneType.SPLASH, splashScene);
    	
	}
	
	@Override
	public void initialize() {
		this.sceneType = SceneType.SPLASH;
	}
	
	@Override
	public void begin() {
		
		//setup textures
		final float x = (720 - ResourceManager.getInstance().splashFaceTextureRegion.getWidth()) * MathUtils.RANDOM.nextFloat();
		final float y = (480 - ResourceManager.getInstance().splashFaceTextureRegion.getHeight()) * MathUtils.RANDOM.nextFloat();
		final Sprite clickToUnload = new Sprite(x, y, ResourceManager.getInstance().splashFaceTextureRegion, ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager());
		getScene().attachChild(clickToUnload);
		
		///setup font
		final Text bitmapText = new Text(160, 230,
				ResourceManager.getInstance().splashBitmapFont,
				"WELCOME MUFFUGA!",
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



