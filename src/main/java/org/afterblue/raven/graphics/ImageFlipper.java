package org.afterblue.raven.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFlipper {
	public static void flip(String input, String output, String format) {
		try {
			BufferedImage image = ImageIO.read(new File(input));
			int width = image.getWidth();
			int height = image.getHeight();
			BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					flipped.setRGB((width - 1) - j, i, image.getRGB(j, i));
			ImageIO.write(flipped, format, new File(output));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void flipAnimation(String inputStringFormat, String outputStringFormat, String imageFormat, int length) {
		for (int i = 0; i < length; i++)
			flip(String.format(inputStringFormat, i), String.format(outputStringFormat, i), imageFormat);
	}
}
