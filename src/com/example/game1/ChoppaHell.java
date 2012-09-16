package com.example.game1;

import org.andengine.engine.handler.physics.PhysicsHandler;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.AnimatedSprite;

public class ChoppaHell extends Choppa {

	public ChoppaHell(int x, int y) {

		this.animatedSprite = new AnimatedSprite(x, y,
				ResourceManager.getInstance().mChopperHellTextureRegion,
				ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager());
		this.animatedSprite.animate(new long[] {0, 100, 100, 0}, 0, 3, true);

		this.chopperModifier = new ScaleModifier(1, 10.0f, 1.5f);
		this.animatedSprite.registerEntityModifier(chopperModifier);

		physicsHandler = new PhysicsHandler(this.animatedSprite);
		this.animatedSprite.registerUpdateHandler(physicsHandler);
	}
}
