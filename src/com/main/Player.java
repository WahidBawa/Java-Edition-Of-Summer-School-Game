package com.main;

import java.awt.*;

public class Player extends GameObject {
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public void tick() {
//        if (x > 1000) {
//            this.setX(0);
//        } else if (x < -32) {
//            this.setX(1000);
//        }
//        if (y > 750) {
//            this.setY(0);
//        } else if (y < -32) {
//            this.setY(750);
//        }
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
            if (i.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(i.getBounds())) {
                    long end = System.currentTimeMillis();
                    double time = (end - Game.start) / 1000f;
                    System.out.println("FINAL SCORE: " + Math.floor(time * 53));
                    System.exit(1);
                }
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);

    }
}
