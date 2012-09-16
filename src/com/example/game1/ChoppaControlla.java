package com.example.game1;

import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;

import android.opengl.GLES20;

public class ChoppaControlla {

	protected Choppa choppaReference;
	public AnalogOnScreenControl analogOnScreenControl;
	
	public ChoppaControlla(Choppa choppa) {
		
		this.choppaReference = choppa;
		
		//setup analog controller
		this.analogOnScreenControl = new AnalogOnScreenControl(0, 
				EngineOptionsManager.getInstance().CAMERA_HEIGHT - ResourceManager.getInstance().mOnScreenControlBaseTextureRegion.getHeight(),
				EngineOptionsManager.getInstance().camera,ResourceManager.getInstance().mOnScreenControlBaseTextureRegion,
				ResourceManager.getInstance().mOnScreenControlKnobTextureRegion,
				0.1f, 200, ResourceManager.getInstance().getActivityReference().getVertexBufferObjectManager(), new IAnalogOnScreenControlListener() {
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				choppaReference.getPhysicsHandler().setVelocity(pValueX * 150, pValueY * 150);
			}

			@Override
			public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
				//do nothing for now
			}
		});
		
		analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();
		
	}
	
	public AnalogOnScreenControl getAnalogOnScreenControl() {
		return this.analogOnScreenControl;
	}

	//no setter for AnalogOnScreenControl to avoid problems.
}


