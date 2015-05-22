package com.myschool.game.core;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CharacterTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

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
	
	private class Child extends Character {
		public Child(String name) {
			super(name);
		}		
		public Child(String name, int livePoint) {
			super(name, livePoint);
		}	
	};
	
	@Test
	public void testCharacterString() {
		int characterCount = Character.getCharacterCount();
		Child child = new Child("Maïa");
		assertEquals(child.getName()+"(10) dit: Bonjour tout le monde!\n", outContent.toString());
	    int newCharacterCount = Character.getCharacterCount();
	    assertEquals("Should be added in character list", newCharacterCount, characterCount + 1);
		assertEquals("Should be same object", child, Character.getCharacter(newCharacterCount - 1) );
	    assertSame("Character should be in character list", child, Character.getCharacter(newCharacterCount - 1));
	}

	@Test
	public void testCharacterStringInt() {
		Child child = new Child("Maïa", 12);
		assertEquals("Maïa(12) dit: Bonjour tout le monde!\n", outContent.toString());
		assertEquals("Live point should equals 12", child.getLivePoint(), 12);
	}

	@Test
	public void testDisBonjourCharacter() {
		Child child = new Child("Maïa");
	    outContent.reset();;
	    child.disBonjour();
		assertEquals("Maïa(10) dit: Bonjour tout le monde!\n", outContent.toString());
		
		Child child2 = new Child("Mélanie");
	    outContent.reset();
	    child.disBonjour(child2);
		assertEquals("Maïa(10) dit: Bonjour, Mélanie!\n", outContent.toString());
	}

	@Test
	public void testInflictDammage() {
		Child child = new Child("Mélanie");
		this.testInflictDammage(child, outContent);
	}
	
	public void testInflictDammage(Character character, ByteArrayOutputStream outContent) {	
	    outContent.reset();;
	    
	    int livePoint;
	    livePoint = character.getLivePoint();
	    character.inflictDammage(0);
	    assertEquals("Should has no dammage", livePoint, character.getLivePoint());
	    
	    livePoint = character.getLivePoint();
	    character.inflictDammage(1);
	    assertEquals("Should lost 1 live point", livePoint - 1, character.getLivePoint());

	    outContent.reset();;
	    character.inflictDammage(20);
	    assertTrue("Should be dead but got : " + outContent.toString(), outContent.toString().matches(character.getName() + "\\(-?[0-9]*\\) dit: Je suis mort :\\(\n"));
	}
	
	@Test
	public void testSay() {
		Child child = new Child("Alain");
		this.testSay(child, outContent);
	}

	public void testSay(Character character, ByteArrayOutputStream outContent) {
	    outContent.reset();;
	    character.say("Hello");
		assertEquals(character.getName() + "(" + character.getLivePoint() + ") dit: Hello\n", outContent.toString());		
	}

}
