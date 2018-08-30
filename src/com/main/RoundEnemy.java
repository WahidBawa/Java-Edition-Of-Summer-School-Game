package com.main;

import java.awt.*;
import java.util.*;

public class RoundEnemy extends GameObject {
    private Random r = new Random();
    private Color[] cols = {Color.red, Color.BLUE, Color.green, Color.orange, Color.PINK};

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    private Color colour = cols[r.nextInt(cols.length)];
    Handler handler = new Handler();

    public RoundEnemy(int x, int y, ID id) {
        super(x, y, id, 5);
        velX = r.nextInt(5) + 5;
        velY = r.nextInt(5) + 5;
    }

    public void tick() {
        x += velX;
        y += velY;
        if (x > Game.WIDTH - 16 || x < 0) {
            velX *= -1;
        }
        if (y > Game.HEIGHT - 48 || y < 0) {
            velY *= -1;
        }
    }

    public void render(Graphics g) {
        g.setColor(colour);
        g.drawOval(x, y, 16, 16);
    }
}
