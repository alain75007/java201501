package com.myschool.game.core;

import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import org.json.JSONException;
import org.json.JSONObject;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class Game {

	private static final String gameName = "Victoria"; // Constante
	private static Socket socket;
	private static  CountDownLatch latch;

	
	public static void main(String[] args) {
		System.out.println("Le jeu " + gameName + " démarre...");
		
		try {
			socket = IO.socket("http://localhost:3001");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... arg0) {
				System.out.println("Connecté");
				// TODO Auto-generated method stub	
				JSONObject json = new JSONObject();
				socket.emit("add user", "Alain");
			}
		}).on("login", new Emitter.Listener() {
			@Override
			public void call(Object... arg0) {
				System.out.println("login done");
			}
		}).on("user joined", new Emitter.Listener() {
			@Override
			public void call(Object... arg0) {
				System.out.println("user join:" + arg0[0]);
			}
		}).on("new message", new Emitter.Listener() {
			@Override
			public void call(Object... arg0) {
				System.out.println("new message:" + arg0[0] + " " + arg0[1]);
			}
		}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			@Override
			public void call(Object... arg0) {
				// TODO Auto-generated method stub
				System.out.println("Déconnecté");
				latch.countDown();
	
			}	
		});
		socket.connect();
		latch = new CountDownLatch(1);
		  try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Knight("Maxime");	
		new Archer("Alpha", 11);
		Character.getCharacter(0).setLiveScore(11);
		
		System.out.println("Nomber de personnages: " + Character.getCharacterCount());
		System.out.println("Fin du programme");

		//character1.disBonjour();
		/*
		Character.getCharacter(1).disBonjour(Character.getCharacter(0));
		do {
			((Archer) Character.getCharacter(1)).useWeapon(Character.getCharacter(0));
			((Knight) Character.getCharacter(0)).useWeapon(Character.getCharacter(1));
		} while (Character.getCharacter(0).getLivePoint() > 0 && Character.getCharacter(1).getLivePoint() > 0);
		*/
	}
}
