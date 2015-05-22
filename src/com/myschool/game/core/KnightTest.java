package com.myschool.game.core;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KnightTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private CharacterTest characterTest = new CharacterTest();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testKnight() {
		int playerCount = Character.getCharacterCount();
		Knight knight = new Knight("Alain");
		assertEquals("Alain", knight.getName());
		assertEquals(10, knight.getLivePoint());
	    assertEquals("Alain(10) dit: Bonjour tout le monde!\nAlain(10) dit: je suis un Chevalier attention à mon épée\n", outContent.toString());

	    int newPlayerCount = Character.getCharacterCount();
	    assertEquals("Should be added in character list", newPlayerCount, playerCount + 1);
		assertEquals("Should be same object", knight, Character.getCharacter(newPlayerCount - 1) );
	    assertSame("Character should be in character list", knight, Character.getCharacter(newPlayerCount - 1));
	}

	@Test
	public void testUseWeapon() {
		Knight knight1 = new Knight("Maïa");
		Knight knight2 = new Knight("Mélanie");
	    outContent.reset();;
	    knight1.hasWeapon = false;
	    knight1.useWeapon(knight2);
	    assertTrue("Should run away if not weapon but got : " + outContent.toString(), outContent.toString().matches("Maïa(.*) dit: Je m'enfuie!!!\n"));

	    outContent.reset();;
	    knight1.hasWeapon = true;
	    knight1.useWeapon(knight2);
	    assertTrue("Should take his weapon but got : " + outContent.toString(), outContent.toString().matches("Maïa(.*) dit: Je prends mon épée\n"));
	    assertTrue("L'épée doit être sortie", knight1.weaponIsOut);
	    
	    outContent.reset();;
	    knight1.useWeapon(knight2);
	    String[] lines = outContent.toString().split("\n");
	    assertEquals(2, lines.length);
	    assertTrue("Should use his weapon but got : "  + lines[0], lines[0].matches("Maïa\\([0-9]*\\) dit: Je donne un coup d'épée à Mélanie"));
	    assertTrue("Should hit Mélanie but got : "  + lines[1], lines[1].matches("Mélanie\\([0-9]*\\) dit: Il me reste [0-9]* points de vies \\(-[0-9]*\\)"));
	}

	@Test
	public void testInflictDammage() {		
		Knight character = new Knight("Mélanie");
		characterTest.testInflictDammage(character, outContent);
	}
	
	@Test
	public void testFigthAction() {		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testSay() {
		Knight knight = new Knight("Alain");
		characterTest.testSay(knight, outContent);
	}
	/*
	@Test
	public void testSay() {
		Knigh knight = new Child("Alain");
	    outContent.reset();;
		knight.say("Hello");
		assertEquals("Alain(10) dit: Hello\n", outContent.toString());
	}
	*/

}
