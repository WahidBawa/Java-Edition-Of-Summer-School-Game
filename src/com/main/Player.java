package com.main;

import java.awt.*;

public class Player extends GameObject {
    Handler handler;
    public static int Health = 100;
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id, 0);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        if (x > Game.WIDTH - 32 || x < -6) {
            x += -velX;
        }
        if (y > Game.HEIGHT - 64 || y < 0) {
            y += -velY;
        }
        collision();
    }

    public void collision() {
        for (GameObject i : handler.object) {
            if (i.getId() == ID.BasicEnemy || i.getId() == ID.RoundEnemy) {
                if (getBounds().intersects(i.getBounds())) {
                    updateHealth(10);
                    if (Health <= 0){
                        long end = System.currentTimeMillis();
                        double time = (end - Game.start) / 1000f;
                        System.out.println("FINAL SCORE: " + Math.floor(time * 53));
                        System.exit(1);
                    }
                }
            }
        }
    }
    private void updateHealth(int damage){
        Health -= damage;
        System.out.println(Health);
    }
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);

    }
}
