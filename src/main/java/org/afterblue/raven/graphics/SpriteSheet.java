package org.afterblue.raven.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    private final BufferedImage[][] sprites;

    public SpriteSheet(String fileName, int rows, int columns, int width, int height) throws IOException {
        sprites = new BufferedImage[rows][columns];

        BufferedImage sheet = ImageIO.read(new File(fileName));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sprites[i][j] = sheet.getSubimage(j * width, i * height, width, height);
            }
        }
    }

    public Texture getTexture(int rowIndex, int columnIndex) {
        return new Texture(sprites[rowIndex][columnIndex]);
    }

    public Animation getAnimation(int rowIndex, int interval) {
        return new Animation(Animation.imagesToTextures(sprites[rowIndex]), interval);
    }
}
