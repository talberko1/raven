package org.afterblue.raven.graphics;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Animation {
	private Texture[] frames;
	private Timer timer;
	private int current;

	public Animation(Texture[] frames, int interval) {
		this.frames = frames;
		timer = new Timer(interval, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				update();
			}

		});
	}
	
	public Animation(String formatPath, int amount, int interval) {
		this(load(formatPath, amount), interval);
	}

	public void update() {
		current++;
		current %= frames.length;
	}

	public void run() {
		timer.start();
	}

	public void render(Graphics2D g, double x, double y) {
		frames[current].render(g, x, y);
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
}
