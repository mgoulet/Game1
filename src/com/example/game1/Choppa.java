package com.example.game1;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

abstract public class Choppa {
	
	protected AnimatedSprite animatedSprite;
	protected ScaleModifier chopperModifier;
	protected PhysicsHandler physicsHandler;
	
	public AnimatedSprite getAnimatedSprite() {
		return this.animatedSprite;
	}
	
	public void setAnimatedSprite(AnimatedSprite animatedSprite) {
		this.animatedSprite = animatedSprite;
	}
	
	public ScaleModifier getScaleModifier() {
		return this.chopperModifier;
	}
	
	public void setScaleModifier(ScaleModifier scaleModifier) {
		this.chopperModifier = scaleModifier;
	}
	
	public PhysicsHandler getPhysicsHandler() {
		return this.physicsHandler;
	}
	
	public void setPhysicsHandler(PhysicsHandler physicsHandler) {
		this.physicsHandler = physicsHandler;
	}

}


