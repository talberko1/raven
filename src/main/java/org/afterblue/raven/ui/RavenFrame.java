package org.afterblue.raven.ui;

import javax.swing.JFrame;

public abstract class RavenFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RavenFrame(String title) {
		super(title);
	}

	public abstract void configure();
}
