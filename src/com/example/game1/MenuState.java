package com.example.game1;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.AutoParallaxBackground;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.ParallaxBackground.ParallaxEntity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import android.opengl.GLES20;
import android.view.KeyEvent;

import com.example.game1.SceneManager.SceneType;
import com.example.game1.StateManager.StateType;

public class MenuState extends State {

    //Menu indices
    public static final int MENU_PLAY = 0;
	public static final int MENU_QUIT = MENU_PLAY + 1;
	
	public MenuState(MainActivity activityReference) {
		super(activityReference);
		
		activityReference.getEngine().registerUpdateHandler(new FPSLogger());

		//menu scene
    	Scene menuScene = new Scene();
    	
    	//menu options
    	MenuScene menuOptionsScene = createMenuOptionsScene();
    	menuScene.setChildScene(menuOptionsScene, false, true, true);
    	
    	//parallax background
    	final AutoParallaxBackground autoParallaxBackground = new AutoParallaxBackground(0, 0, 0, 5);
		final VertexBufferObjectManager vertexBufferObjectManager = activityReference.getVertexBufferObjectManager();
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(0.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerBack.getHeight(), ResourceManager.getInstance().mParallaxLayerBack, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-5.0f, new Sprite(0, 80, ResourceManager.getInstance().mParallaxLayerMid, vertexBufferObjectManager)));
		autoParallaxBackground.attachParallaxEntity(new ParallaxEntity(-10.0f, new Sprite(0, EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mParallaxLayerFront.getHeight(), ResourceManager.getInstance().mParallaxLayerFront, vertexBufferObjectManager)));
		
		//add the parallax background to the MenuOptionsScene (not just the menu, otherwise this causes conflict)
		menuOptionsScene.setBackground(autoParallaxBackground);
		
		SceneManager.getInstance().addScene(SceneType.MENU, menuScene);
	}
	
	private MenuScene createMenuOptionsScene() {
		MenuScene menuOptionsScene = new MenuScene(EngineOptionsManager.getInstance().getCamera());
		
		final IMenuItem playMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_PLAY, ResourceManager.getInstance().mMenuFont, "PLAY", activityReference.getVertexBufferObjectManager()), new Color(1,0,0), new Color(1,1,1));
		playMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		menuOptionsScene.addMenuItem(playMenuItem);

		final IMenuItem quitMenuItem = new ColorMenuItemDecorator(new TextMenuItem(MENU_QUIT, ResourceManager.getInstance().mMenuFont, "QUIT", activityReference.getVertexBufferObjectManager()), new Color(1,0,0), new Color(1,1,1));
		quitMenuItem.setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		menuOptionsScene.addMenuItem(quitMenuItem);

		menuOptionsScene.buildAnimations();
		menuOptionsScene.setOnMenuItemClickListener(this);
		
		return menuOptionsScene;
	}
	
	@Override
	public void initialize() {
		this.sceneType = SceneType.MENU;
	}
	
	@Override
	public void begin() {
		ResourceManager.getInstance().playMenuMusic();
		
    	//trigger scene change at the engine level
    	//this.getScene().reset();
    	activityReference.getEngine().setScene(getScene());
		
	}
	
	@Override
	public void end() {
		ResourceManager.getInstance().stopMenuMusic();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
    	{
    		processBackButton();	
    	}
		
		return true;
	}
	
	private void processBackButton() {

		StateManager.getInstance().switchState(StateType.OPTIONS);

    }

    @Override
	public boolean onMenuItemClicked(final MenuScene pMenuScene, final IMenuItem pMenuItem, final float pMenuItemLocalX, final float pMenuItemLocalY) {
		
    	switch(pMenuItem.getID()) {
			case MENU_PLAY:
				// Restart the animation.
				/*
				this.mMainScene.reset();

				// Remove the menu and reset it.
				this.mMainScene.clearChildScene();
				this.mMenuScene.reset();
				*/
				return true;
			case MENU_QUIT:
				// End Activity.
				activityReference.finish();
				return true;
			default:
				return false;
		}
    }
	
}


