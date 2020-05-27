package org.afterblue.raven.camera;

public abstract class RavenCamera {
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public RavenCamera(int x, int y) {
		this(x, y, 0, 0);
	}
	
	public RavenCamera(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
