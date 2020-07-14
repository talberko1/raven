package org.afterblue.raven.ui;

import java.awt.*;

public class RavenButton extends Button {

    private int x;
    private int y;
    private int width;
    private int height;
    private int border;
    private Color borderColor;
    private String text;
    private int fontSize;

    public RavenButton(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.fontSize = 20;
        setFont(new Font("Arial", Font.PLAIN, fontSize));
        this.border = 5;
        this.borderColor = Color.LIGHT_GRAY;
    }

    public void setBorder(int border) {
        this.border = border;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void display(Graphics2D g) {
        display(g, Color.GRAY);
    }

    public void display(Graphics2D g, Color background) {
        g.setColor(background);
        g.fillRect(x, y, width, height);
        g.drawString(text, x, y);
    }

    public void display(Graphics2D g, Image background) {
        g.drawImage(background, x, y, width, height, null);
    }




}
