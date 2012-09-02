package com.example.game1;


import android.view.KeyEvent;
import android.view.Menu;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class MainActivity extends SimpleBaseGameActivity {
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public EngineOptions onCreateEngineOptions()
    {
    	EngineOptionsManager.initializeEngineOptionsManager(mEngine);
    	
        return EngineOptionsManager.getInstance().getEngineOptions();
    }

    @Override
    protected void onCreateResources()
    {
    	ResourceManager.initializeResourceManager(this);

    }

    @Override
    protected Scene onCreateScene()
    {
    	//return our current scene
    	StateManager.getInstance().initializeStateManager(this);
        return StateManager.getInstance().getCurrentState().getScene();

    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	//send key events to the current state
    	return StateManager.getInstance().getCurrentState().onKeyDown(keyCode, event);   	
	}
    
}
