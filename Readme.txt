=====================================
X Project Fall 2012: Android Game
=====================================

General Todos:
-Persistent options?
-splashcreen: timer to change state to menu
-rename MainActivity to something useful
-rename ScreenManager -> SceneContainer
-add score text in game (HUD)
-get choppa displayed with analog stick

August 29th: (3 hrs)
-Installed environment (new eclipse, droid sdk, etc..)
-Get sample running
-Setup Github with source tree
-SceneManager
-Resource manager

August 31st: (5 hrs)
-Got music working
-Seperate manager construction from initialization (build in ctor, init in proper methods)
-Create a state manager (scenes will eventually be contained in states...)
	-create simple states hierarchy (State, SplashState, MenuState, GameStates, etc...) 
-SceneManager -> singleton
-ResourceManager -> singleton
-state manager -> singleton
-switch scene from Application to state system
-formalize states with begin/end
-Splashscreen contents (background + text)
-Built EngineOptionsManager -> singleton

September 1st: (4 hrs + 2)
-Change all managers to reference the application instead of subcomponents...
-Implement menu system
-clean menu system
-Add activityReference to states
-push listeners into the states as opposed to the application
-dropped scene control into states (init & links etc...)
-replaced bitmap menu with text menu
-add parallax background to menu
-fix parallax background in menu (reset fixes backgrouhd, breaks menu options...)
-fix crash on app restart (proper resource release)
-fix menu music replay chose
-drop in tiled textures in game scene



