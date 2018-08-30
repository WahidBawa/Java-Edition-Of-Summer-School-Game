package com.main;

import java.awt.*;

public class Player extends GameObject {
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {
        if (x > 1000) {
            this.setX(0);
        } else if (x < -32) {
            this.setX(1000);
        }
        if (y > 750) {
            this.setY(0);
        } else if (y < -32) {
            this.setY(750);
        }
        this.x += this.velX;
        this.y += this.velY;
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);

    }
}
