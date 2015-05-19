package com.myschool.game.core;

public class Archer extends Warrior implements Fight {
	
	private int arrows = 15;

	public Archer(String name) {
		super(name, "Archer", "arc", 2);
	}

	public Archer(String name, int liveScore) {
		this(name);
	}

	@Override
	public void useWeapon(Character character) {
		if (this.getLivePoint() <= 0) {
			return;
		}
		if (this.hasWeapon) {
			if (this.weaponIsOut) {
				this.figthAction(character);
			}
			else {
				this.say("Je prends mon arc!");
				this.weaponIsOut = true;			}
		}
		else {
			this.say("Je m'enfuie!!!");
		}
	}

	@Override
	public void figthAction(Character character) {
		if (this.arrows > 0) {
			this.say("Je tire une flèche vers " + character.getName());
			this.arrows--;
			this.resolveFightAction(character);
		}
		else {
			this.say("Je jette mon arc");
			this.hasWeapon = false;
			this.weaponIsOut = false;
		}		
	}

}
