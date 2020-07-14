package org.afterblue.raven.graphics;

import org.afterblue.raven.interfaces.Displayable;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Texture implements Displayable {
	private final static Map<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	private BufferedImage image;

	public Texture(String file) {
		BufferedImage previous = textures.get(file);
		if (previous != null)
			this.image = previous;
		else {
			try {
				this.image = ImageIO.read(new File(file));
				textures.put(file, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.printf("Failed loading %s\n", file);
			}
		}
	}

	public Texture(String file, int width, int height) {
		this(file);
		resize(width, height);
	}

	public void resize(int width, int height) {
		Image tmp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = img.createGraphics();
		g.drawImage(tmp, 0, 0, null);
		g.dispose();

		this.image = img;
	}

	public void display(Graphics2D g, double x, double y) {
		g.drawImage(image, (int) x, (int) y, null);
	}

	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void init() {

	}

	@Override
	public void tick() {

	}

	@Override
	public void display(Graphics2D g) {

	}
}
