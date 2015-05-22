package com.myschool.game.core;

public abstract class Warrior extends Character {
	
	private String weapon;
	private String warriorType;
	protected boolean weaponIsOut;
	protected boolean hasWeapon = true;
	private int weaponPower;
	
	public String getWarriorType() {
		return warriorType;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public Warrior(String name, String warriorType, String weapon, int weaponPower) {
		super(name);
		this.weapon = weapon;
		this.warriorType = warriorType;
		this.weaponPower = weaponPower;
		this.say("je suis un " + warriorType + " attention à mon " + weapon);
	}

	@Override
	public void disBonjour() {
		super.disBonjour();
		//System.out.println(this.getName() + " dit: je suis un guerrier");
	}
	
	protected void resolveFightAction(Character character) {
		int dammage = (int )(Math.random() * weaponPower);
		character.inflictDammage(dammage);
	}
}
