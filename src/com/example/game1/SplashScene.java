package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.util.HorizontalAlign;

public class SplashScene extends Scene{

	MainActivity activityReference;
	
	//components
	protected Text mainSplashTextLabel;
	
	public SplashScene(MainActivity activityReference) {
		super();
		
		this.activityReference = activityReference;
		
		mainSplashTextLabel = new Text(160, 230,
				ResourceManager.getInstance().splashBitmapFont,
				"SPLASH SCREEN.",
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager());

		this.setBackground(new Background(0.0f, 0.0f, 0.0f));
	}
	
	public void prepareSceneForSplash() {
		
		
		this.attachChild(mainSplashTextLabel);
	}
	
}
