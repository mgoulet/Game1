package com.example.game1;

import java.util.HashMap;
import java.util.Map;

public class StateManager {

	private static StateManager instance;

	
	public static void initializeStateManager(MainActivity activityReference) {
		if (instance == null) {
			instance = new StateManager(activityReference);
		}	
	}
	
	public static synchronized StateManager getInstance() {
		return instance;
	}
	
	//Scene types
    public enum StateType
    {
    	SPLASH,
    	MENU,
    	OPTIONS,
    	GAME_INTRO,
    	GAME_PLAYING,
    	GAME_HIGHSCORES
    }
    
    private MainActivity activityReference;
    
	//access the application
	public MainActivity getActivityReference() {
		return this.activityReference;
	}
    
    //Our current state
    private StateType currentState = StateType.SPLASH;
    
    //Our scene container
    private Map<StateType, State> stateMap;
    
	private StateManager(MainActivity activityReference) {
		this.activityReference = activityReference;
		initializeStates();
	}
	
    private void initializeStates() {
    	
    	//splash state
    	SplashState splashState = new SplashState(activityReference);
    	splashState.initialize();
    	
    	//menu scene
    	MenuState menuState = new MenuState(activityReference);
    	menuState.initialize();
    	
        //options scene
        OptionsState optionsState = new OptionsState(activityReference);
        optionsState.initialize();
        
        //game intro state
        GameIntroState gameIntroState = new GameIntroState(activityReference);
        gameIntroState.initialize();
      
        //game playing state
        GamePlayingState gamePlayingState = new GamePlayingState(activityReference);
        gamePlayingState.initialize();
        
        //game high scores state
        GameHighScoresState gameHighScoresState = new GameHighScoresState(activityReference);
        gameHighScoresState.initialize();

        stateMap = new HashMap<StateType, State>();
        stateMap.put(StateType.SPLASH,  		splashState);
        stateMap.put(StateType.MENU,  			menuState);
        stateMap.put(StateType.OPTIONS,  		optionsState);
        stateMap.put(StateType.GAME_INTRO,  	gameIntroState);
        stateMap.put(StateType.GAME_PLAYING,  	gamePlayingState);
        stateMap.put(StateType.GAME_HIGHSCORES, gameHighScoresState);
        
        //force a switch state to the first splashscreen
        this.switchState(StateType.SPLASH);

    }
    
    public State getCurrentState() {
    	return this.stateMap.get(this.currentState);
    }
    
    public State getState(StateType stateType) {
    	return this.stateMap.get(stateType);
    }
    
    public void switchState(StateType newState) {
    	
    	//close off current state
    	this.stateMap.get(this.currentState).end();
    	//getCurrentState().getScene().dispose();
    	
    	//switch current state id
    	this.currentState = newState;
    	
    	//begin new state
    	getCurrentState().begin();
    	
    }
	
}
