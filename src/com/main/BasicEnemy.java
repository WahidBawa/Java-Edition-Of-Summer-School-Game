package com.main;

import java.awt.*;
import java.util.*;

public class BasicEnemy extends GameObject {
    private Random r = new Random();
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = r.nextInt(9) + 1;
        velY = r.nextInt(9) + 1;
    }

    public void tick() {
        x += velX;
        y += velY;
        if (x > Game.WIDTH - 16|| x < 0){
            velX *= -1;
        }
        if (y > Game.HEIGHT - 48 || y < 0){
            velY *= -1;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
