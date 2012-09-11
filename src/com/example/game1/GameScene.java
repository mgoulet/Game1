package com.example.game1;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.AlphaModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;

import android.opengl.GLES20;

public class GameScene extends Scene{

	MainActivity activityReference;
	
	public HUD gameHud;
	public Text hudTimerTextLabel;
	public Text hudTimerTextValue;

	public Text introTextValue;
	public ParallelEntityModifier introTextModifier;
	public ParallelEntityModifier hudIntroModifier;
	
	public AnimatedSprite chopper;
	public ScaleModifier chopperModifier;
	public PhysicsHandler physicsHandler;
	public AnalogOnScreenControl analogOnScreenControl;
	
	public AutoParallaxBackground autoParallaxBackground;
	public VertexBufferObjectManager vertexBufferObjectManager;
	
	public GameScene(MainActivity activityReference) {
		super();
		
		this.activityReference = activityReference; 
		
		//parallax background
    	autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		vertexBufferObjectManager = activityReference.getVertexBufferObjectManager();
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerBack.getHeight(), ResourceManager.getInstance().mParallaxLayerBack, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, 80, ResourceManager.getInstance().mParallaxLayerMid, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-20.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerFront.getHeight(), ResourceManager.getInstance().mParallaxLayerFront, vertexBufferObjectManager)));

		this.setBackground(autoParallaxBackground);
		
		//setup CHOPPA
		chopper = new AnimatedSprite(360, 240,
				ResourceManager.getInstance().mChopperJoeTextureRegion,
				activityReference.getVertexBufferObjectManager());
		chopper.animate(new long[] {100, 0, 0, 100}, 0, 3, true);
		
		//entity modifier
		chopperModifier = new ScaleModifier(1, 10.0f, 1.5f);
		chopper.registerEntityModifier(chopperModifier);
		
		//physics
		physicsHandler = new PhysicsHandler(chopper);
		chopper.registerUpdateHandler(physicsHandler);
		
		//setup analog controller
		analogOnScreenControl = new AnalogOnScreenControl(0, 
				EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mOnScreenControlBaseTextureRegion.getHeight(),
				EngineOptionsManager.getInstance().camera,ResourceManager.getInstance().mOnScreenControlBaseTextureRegion,
				ResourceManager.getInstance().mOnScreenControlKnobTextureRegion,
				0.1f, 200, activityReference.getVertexBufferObjectManager(), new IAnalogOnScreenControlListener() {
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				physicsHandler.setVelocity(pValueX * 150, pValueY * 150);
			}

			@Override
			public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
				chopper.registerEntityModifier(new SequenceEntityModifier(new ScaleModifier(0.25f, 1, 1.5f), new ScaleModifier(0.25f, 1.5f, 1)));
			}
		});
		
		analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();
		
		gameHud = new HUD();
		hudTimerTextLabel = new Text(0, 0,
				ResourceManager.getInstance().splashBitmapFont,
				"TIME ROCKIN' THE CHOPPA:",
				new TextOptions(HorizontalAlign.CENTER),
				ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager());
		hudTimerTextValue = new Text(550, 0, ResourceManager.getInstance().mDroidFont, "0.0", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
		gameHud.attachChild(hudTimerTextLabel);
		gameHud.attachChild(hudTimerTextValue);
		
		//intro text & modifier
		introTextValue = new Text(220, 220, ResourceManager.getInstance().mDroidFont, "-GET READY-", new TextOptions(HorizontalAlign.CENTER), vertexBufferObjectManager);
		introTextModifier = new ParallelEntityModifier(
			new AlphaModifier(2, 1, 0),
			new ScaleModifier(2, 2, 0.5f)
		);
		introTextValue.registerEntityModifier(introTextModifier);
		
	}
	
	public void prepareSceneForIntro() {
		//add countdown text
		this.attachChild(introTextValue);
		introTextModifier.reset();
		
	}
	
	public void cleanupAfterIntro() {
		//remove intro text
		this.detachChild(introTextValue);

	}
	
	public void prepareSceneForPlay() {
		this.attachChild(chopper);
		this.setChildScene(analogOnScreenControl);		
		EngineOptionsManager.getInstance().getCamera().setHUD(gameHud);
		
	}
	
	public void cleanupAfterPlay() {
		this.detachChildren();		
		this.clearChildScene();
		chopperModifier.reset();
		EngineOptionsManager.getInstance().getCamera().setHUD(null);
	}
	
}




