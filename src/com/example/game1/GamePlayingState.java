package com.example.game1;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.DelayModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.modifier.SkewXModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;
import android.view.KeyEvent;

import com.example.game1.SceneManager.SceneType;
import com.example.game1.StateManager.StateType;

public class GamePlayingState extends State {

	public GamePlayingState(MainActivity activityReference) {
		super(activityReference);
		
		//game scene
        Scene gameScene = new Scene();
        gameScene.setBackground(new Background(1.0f, 1.0f, 1.0f));
        SceneManager.getInstance().addScene(SceneType.GAME, gameScene);
        
	}
	
	@Override
	public void initialize() {
		this.sceneType = SceneType.GAME;
	}
	
	@Override
	public void begin() {
		activityReference.getEngine().setScene(getScene());
		
		//setup CHOPPA
		final AnimatedSprite helicopter = new AnimatedSprite(360, 240,
				ResourceManager.getInstance().mHelicopterTextureRegion,
				activityReference.getVertexBufferObjectManager());
		helicopter.animate(new long[] {100, 0, 0, 100}, 0, 3, true);
		getScene().attachChild(helicopter);
		
		//entity modifier
		ScaleModifier modifier = new ScaleModifier(1, 10.0f, 1.5f);
		helicopter.registerEntityModifier(modifier);
		
		final PhysicsHandler physicsHandler = new PhysicsHandler(helicopter);
		helicopter.registerUpdateHandler(physicsHandler);
		
		//setup analog controller
		final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl(0, 
				EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mOnScreenControlBaseTextureRegion.getHeight(),
				EngineOptionsManager.getInstance().camera,ResourceManager.getInstance().mOnScreenControlBaseTextureRegion,
				ResourceManager.getInstance().mOnScreenControlKnobTextureRegion,
				0.1f, 200, activityReference.getVertexBufferObjectManager(), new IAnalogOnScreenControlListener() {
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				physicsHandler.setVelocity(pValueX * 100, pValueY * 100);
			}

			@Override
			public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
				helicopter.registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f), new ScaleModifier(0.25f, 1.5f, 1)));
			}
		});
		analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();

		getScene().setChildScene(analogOnScreenControl);
		
		//parallax background
    	final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		final VertexBufferObjectManager vertexBufferObjectManager = activityReference.getVertexBufferObjectManager();
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerBack.getHeight(), ResourceManager.getInstance().mParallaxLayerBack, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, 80, ResourceManager.getInstance().mParallaxLayerMid, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerFront.getHeight(), ResourceManager.getInstance().mParallaxLayerFront, vertexBufferObjectManager)));
		
		//add the parallax background to the MenuOptionsScene (not just the menu, otherwise this causes conflict)
		getScene().setBackground(autoParallaxBackground);
		
	}
	
	@Override
	public void end() {

		//kill choppa
		//...
		
		
		getScene().detachChildren();
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
