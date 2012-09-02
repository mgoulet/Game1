package com.example.game1;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.opengl.font.BitmapFont;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.ITextureRegion;

public class ResourceManager {

	private static ResourceManager instance;
	
	public static void initializeResourceManager(MainActivity activityReference) {
		if (instance == null) {
			instance = new ResourceManager(activityReference);
		}	
	}

	public static synchronized ResourceManager getInstance() {
		return instance;
	}

	//Application reference
	private MainActivity activityReference;
	
	//Resources
	public Music menuMusic;
	
	//Fonts
	public BitmapFont splashBitmapFont;
	
	//Textures
	public BitmapTextureAtlas splashBitmapTextureAtlas;
	public ITextureRegion splashFaceTextureRegion;
	
	//Menu stuff
	public BitmapTextureAtlas mMenuTexture;
	public ITextureRegion mMenuPlayTextureRegion;
	public ITextureRegion mMenuQuitTextureRegion;
	
	//Text menu font
	public Font mMenuFont;
	ITexture fontTexture;
		
	//AutoParallax stuff for menu
	public BitmapTextureAtlas mAutoParallaxBackgroundTexture;

	public ITextureRegion mParallaxLayerBack;
	public ITextureRegion mParallaxLayerMid;
	public ITextureRegion mParallaxLayerFront;
	
	//game scene stuff
	public BitmapTextureAtlas mGameBitmapTextureAtlas;
	public RepeatingSpriteBackground mStoneBackground;
	
	//constructor
	private ResourceManager(MainActivity activityReference) {
		this.activityReference = activityReference;
		initializeResources();
	}
	
	//access the application
	public MainActivity getActivityReference() {
		return this.activityReference;
	}
	
	private boolean initializeResources() {

		//Textures
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashBitmapTextureAtlas  = new BitmapTextureAtlas(activityReference.getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		splashFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashBitmapTextureAtlas, activityReference, "face_box.png", 0, 0);
		splashBitmapTextureAtlas.load();
		
		//Menu stuff
		this.mMenuTexture = new BitmapTextureAtlas(activityReference.getTextureManager(), 256, 128, TextureOptions.BILINEAR);
		this.mMenuPlayTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, activityReference, "menu_play.png", 0, 0);
		this.mMenuQuitTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mMenuTexture, activityReference, "menu_quit.png", 0, 50);
		this.mMenuTexture.load();
		
		//Bitmap Fonts for splash screen...
		this.splashBitmapFont = new BitmapFont(activityReference.getTextureManager(), activityReference.getAssets(), "font/BitmapFont.fnt");
		this.splashBitmapFont.load();
		
		//Menu font
		FontFactory.setAssetBasePath("font/");
		fontTexture = new BitmapTextureAtlas(activityReference.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		this.mMenuFont = FontFactory.createFromAsset(activityReference.getFontManager(), fontTexture, activityReference.getAssets(), "Plok.ttf", 48, true, android.graphics.Color.WHITE);
		this.mMenuFont.load();
		
		//Auto parallax background stuff
		this.mAutoParallaxBackgroundTexture = new BitmapTextureAtlas(activityReference.getTextureManager(), 1024, 1024);
		this.mParallaxLayerFront = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, activityReference, "parallax_background_layer_front.png", 0, 0);
		this.mParallaxLayerBack = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, activityReference, "parallax_background_layer_back.png", 0, 188);
		this.mParallaxLayerMid = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mAutoParallaxBackgroundTexture, activityReference, "parallax_background_layer_mid.png", 0, 669);
		this.mAutoParallaxBackgroundTexture.load();
		
		//game scene background stuff
		mGameBitmapTextureAtlas = new BitmapTextureAtlas(activityReference.getTextureManager(), 128, 128);
		mStoneBackground = new RepeatingSpriteBackground(EngineOptionsManager.getInstance().CAMERA_WIDTH, EngineOptionsManager.getInstance().CAMERA_HEIGHT, activityReference.getTextureManager(), AssetBitmapTextureAtlasSource.create(activityReference.getAssets(), "gfx/stone1.png"), activityReference.getVertexBufferObjectManager());
		mGameBitmapTextureAtlas.load();
		
		//Music
		MusicFactory.setAssetBasePath("mfx/");
    	try {
    		menuMusic = MusicFactory.createMusicFromAsset(this.activityReference.getEngine().getMusicManager(), activityReference, "wagner_the_ride_of_the_valkyries.ogg");
    	} catch (IOException e)
    	{
    		e.printStackTrace();
    	}

		return false;
	}
	
	public void unloadResources() {
		splashBitmapTextureAtlas.unload();
		mMenuTexture.unload();
		mAutoParallaxBackgroundTexture.unload();
		fontTexture.unload();
		
	}
	
	public void playMenuMusic() {
		if(!menuMusic.isPlaying()) {
			menuMusic.play();
		}
	}
	
	public void stopMenuMusic() {
		if(menuMusic.isPlaying()) {
			menuMusic.pause();
		}
	}

}


