package org.afterblue.raven.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RavenKeyboard implements KeyListener {

	public static final int AMOUNT = 256;
	private boolean[] keys;
	private boolean[] previous;

	public RavenKeyboard() {
		keys = new boolean[AMOUNT];
		previous = new boolean[AMOUNT];
		for (int i = 0; i < AMOUNT; i++) {
			previous[i] = keys[i] = false;
		}
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void update() {
		for (int i = 0; i < AMOUNT; i++) {
			previous[i] = keys[i];
		}
	}

	public boolean isDown(int code) {
		return keys[code];
	}

	public boolean wasPressed(int code) {
		return isDown(code) && !previous[code];
	}

	public boolean wasReleased(int code) {
		return !isDown(code) && previous[code];
	}

}
