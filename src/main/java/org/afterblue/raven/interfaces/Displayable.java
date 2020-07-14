package org.afterblue.raven.interfaces;

import java.awt.Graphics2D;

public interface Displayable {
    public void init();
    public void tick();
    public void display(Graphics2D g);
}