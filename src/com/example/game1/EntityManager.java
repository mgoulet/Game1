package com.example.game1;

public class EntityManager {

	private static EntityManager instance;
	
	public static synchronized EntityManager getInstance() {
		if (instance == null) {
			instance = new EntityManager();
		}	
		return instance;
	}

}




