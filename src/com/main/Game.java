package com.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class Game extends Canvas implements Runnable {

    public final static int WIDTH = 1000, HEIGHT = 750; //WIDTH / 12 * 9;

    private Thread thread;
    public boolean running = false;
    private Random r = new Random(); // this may be needded later for multiole enemies
    public static long start = System.currentTimeMillis();
    private Handler handler;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Generic Title", this);
        Player x = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler);
//        BasicEnemy o = new BasicEnemy(WIDTH / 2 - 16, HEIGHT / 2 - 16, ID.BasicEnemy);
        handler.addObject(x);
        int rnum = r.nextInt(10);
        for(int i = 0; i < rnum; i++){
            handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(100), ID.BasicEnemy));
        }
        for (int i = 0; i < 10 - rnum; i++){
            handler.addObject(new RoundEnemy(r.nextInt(WIDTH), r.nextInt(100), ID.RoundEnemy));
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

}
