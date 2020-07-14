package org.afterblue.raven;

import org.afterblue.raven.interfaces.Displayable;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class RavenApp extends Canvas implements Runnable, Displayable {

    private static final String FPS_FORMAT = "FPS: %d | TPS: %d\n";
    public static final String THREAD_EXT = "-Thread";
    private final Thread thread;
    private JFrame frame;
    private final String title;
    private boolean running;

    public RavenApp(String threadName, String title) {
        this.title = title;
        thread = new Thread(this, threadName + THREAD_EXT);
        running = false;
        createWindow(title);
    }

    public void run() {
        requestFocus();
        double nsPerTick = 1000000000 / 60.0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        int fps = 0;
        int tps = 0;
        boolean canRender = false;
        init();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            if (delta >= 1) {
                tick();
                delta--;
                tps++;
                canRender = true;
            } else
                canRender = false;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (canRender) {
                render();
                fps++;
            }

            if (System.currentTimeMillis() - 1000 > timer) {
                timer += 1000;
                frame.setTitle(title + " - " + String.format(FPS_FORMAT, fps, tps));
                fps = tps = 0;
            }

        }
        stop();
    }

    private void createWindow(String title) {
        frame = new JFrame(title);
        frame.add(this);
        configureFrame(frame);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void configureFrame(JFrame frame) {

    }

    public void enableFPS() {

    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        display(g);
        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
