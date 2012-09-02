X Project Fall 2012: Android Game
=====================================
GAME TODOS (cool/fun stuff):
=====================================
-splashcreen: timer to change state to menu
-add BRUTAL explosions to gamestate (left screen)
-add score text in game (HUD) (crazy timer & such)
-change menu parallax

STRUCTURAL TODOS:
=====================================
-rename MainActivity to something useful
-move choppa automatically to match parallax
-push composite resources into ResourceManager (from state sandboxes)

DEV LOG:
=====================================
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

September 1st: (6 hrs)
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

September 2nd: (2.5 hrs)
-rendered CHOPPA
-render analog stick
-control CHOPPA with analog stick
-push parallax background to game state
-proper release of scene children on state switches
-flip choppa
-refactor/cleanup resources name and such...
-rename ScreenManager -> SceneContainer

