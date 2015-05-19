package com.myschool.game.core;

public class Knight extends Warrior implements Fight {

	public Knight(String name) {
		super(name, "Chevalier", "épée", 3);
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
				this.say("Je prends mon épée");
				this.weaponIsOut = true;
			}
		}
		else {
			this.say("Je m'enfuie!!!");
		}
	}

	@Override
	public void figthAction(Character character) {
		this.say("Je donne un coup d'épée à " + character.getName());
		this.resolveFightAction(character);
	}

}
