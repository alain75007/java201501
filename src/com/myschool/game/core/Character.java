package com.myschool.game.core;

import java.util.ArrayList;

public abstract class  Character {

	// private static int characterCount; // Variable de classe 
	private String name; // Variable d'instance
	private int livePoint = 10; // Variable d'instance
	private static final ArrayList<Character> characters = new ArrayList<Character>();
	
	public String getName() {
		return name;
	}	
	public Character(String name) { // Constructeur
		this.initialize(name);
	}

	public Character(String name, int livePoint) { // Constructeur
		setLivePoint(livePoint);
		this.initialize(name);
	}
	
	private void initialize(String name) {
		synchronized(characters) {
			characters.add(this);
		}
		this.name = name;
		//characterCount++;
		this.disBonjour();
	}
	
	public int getLivePoint() { // Méthode d'instance (getter)
		return livePoint;
	}
	public void setLivePoint(int livePoint) { // Méthode d'instance (setter)
		this.livePoint = livePoint;
	}
	public void setLiveScore(int livePoint) { // Méthode d'instance (setter)
		setLivePoint(livePoint);
	}
	
	public void disBonjour() { // Méthode d'instance
		this.say("Bonjour tout le monde!");
	}
	public boolean disBonjour(Character character) { // Métthode d'instance
		this.say("Bonjour, " + character.name + "!");
		return true;
	}
	public static int getCharacterCount() { // Méthode de classe (getter)
		return characters.size();
	}
	public void inflictDammage(int dammage) {
		this.livePoint -= dammage;
		if (this.livePoint <= 0 ) {
			this.say("Je suis mort :(");
		}
		else {
			this.say("Il me reste " + this.livePoint + " points de vies (-" + dammage + ")");
		}
	}
	public static Character getCharacter(int i) {
		return characters.get(i);
	
	}

	public void say(String string) {
		System.out.println(this.getName() + "(" + livePoint + ") dit: " + string);
		
	}

}
