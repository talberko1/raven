package org.afterblue.raven.graphics;

import org.afterblue.raven.interfaces.Displayable;

import java.awt.Graphics2D;

import javax.swing.Timer;

public class Animation implements Displayable {
	private Texture[] frames;
	private Timer timer;
	private int current;

	public Animation(Texture[] frames, int interval) {
		this.frames = frames;
		timer = new Timer(interval, action -> {
			tick();
		});
	}
	
	public Animation(String formatPath, int amount, int interval) {
		this(load(formatPath, amount), interval);
	}

	public void run() {
		timer.start();
	}

	public void display(Graphics2D g, double x, double y) {
		frames[current].display(g, x, y);
	}
	
	private static Texture[] load(String formatPath, int amount) {
		Texture[] textures = new Texture[amount];
		for (int i = 0; i < amount; i++) {
			Texture texture = new Texture(String.format(formatPath, i));
			if (texture.getImage() != null);
				textures[i] = texture;
		}
		return textures;
	}
	
	public void resize(int width, int height) {
		for (Texture texture : frames)
			texture.resize(width, height);
	}

	@Override
	public void init() {

	}

	@Override
	public void tick() {
		current++;
		current %= frames.length;
	}

	@Override
	public void display(Graphics2D g) {

	}
}
