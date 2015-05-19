package com.myschool.game.core;

public class Game {

	private static final String gameName = "Victoria"; // Constante

	
	public static void main(String[] args) {
		System.out.println("Le jeu " + gameName + " démarre...");
		new Knight("Maxime");	
		new Archer("Alpha", 11);
		Character.getCharacter(0).setLiveScore(11);
		
		System.out.println("Nomber de personnages: " + Character.getCharacterCount());

		//character1.disBonjour();
		Character.getCharacter(1).disBonjour(Character.getCharacter(0));
		do {
			((Archer) Character.getCharacter(1)).useWeapon(Character.getCharacter(0));
			((Knight) Character.getCharacter(0)).useWeapon(Character.getCharacter(1));
		} while (Character.getCharacter(0).getLivePoint() > 0 && Character.getCharacter(1).getLivePoint() > 0);
	}
}
