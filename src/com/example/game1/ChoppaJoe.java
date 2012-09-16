package com.example.game1;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

public class ChoppaJoe extends Choppa{

	public ChoppaJoe(int x, int y) {

		this.animatedSprite = new AnimatedSprite(x, y,
				ResourceManager.getInstance().mChopperJoeTextureRegion,
				ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager());
		this.animatedSprite.animate(new long[] {100, 0, 0, 100}, 0, 3, true);

		this.chopperModifier = new ScaleModifier(1, 10.0f, 1.5f);
		this.animatedSprite.registerEntityModifier(chopperModifier);

		physicsHandler = new PhysicsHandler(this.animatedSprite);
		this.animatedSprite.registerUpdateHandler(physicsHandler);
	}



}
