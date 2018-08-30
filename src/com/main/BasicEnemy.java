package com.main;

import java.awt.*;
import java.util.*;

public class BasicEnemy extends GameObject {
    private Random r = new Random();
    private Color[] cols = {Color.red, Color.BLUE, Color.green, Color.orange, Color.PINK};

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    private Color colour = cols[r.nextInt(cols.length)];
    Handler handler = new Handler();
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        velX = r.nextInt(5) + 5;
        velY = r.nextInt(5) + 5;
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
        for (GameObject i : handler.object){
            if (i.getId() == ID.Player){
                ;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(colour);
        g.fillRect(x, y, 16, 16);
    }
}
