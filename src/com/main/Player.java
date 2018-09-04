package com.main;

import java.awt.*;

public class Player extends GameObject {
    Handler handler;
    private static int Health = 100;
    private boolean blink = false;
    private long hitTime;
    private Color[] bcols = {Color.white, Color.red};
    private int dcol = 1;
    private boolean checked = false;
    private int oldX = 0;
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
        if (blink){
            if (blinkTime(hitTime) >= 2){
                blink = false;
            }
        }
//        if (collision() && !blink){
        if (collision()){
            blink = true;
            hitTime = System.currentTimeMillis();
        }

    }

    private boolean collision() {
        for (GameObject i : handler.object) {
            if (i.getId() == ID.BasicEnemy || i.getId() == ID.RoundEnemy) {
                if (getBounds().intersects(i.getBounds()) && !blink) {
                    updateHealth(i.getDmg());
//                    if (Health <= 0){
                    if (true){
                        long end = System.currentTimeMillis();
                        double time = (end - Game.start) / 1000f;
                        System.out.println("FINAL SCORE: " + Math.floor(time * 53));
                        System.exit(1);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    private void updateHealth(int damage){
        Health -= damage;
        System.out.println("DAMAGE: " + damage);
        System.out.println("Health: " + Health);
    }
    public void render(Graphics g) {
        if (blink){
            int X = (int) Math.floor(blinkTime(hitTime));
            System.out.println(X);
            if (X != oldX) checked = false;
            if ((X % 1) == 0 && !checked){
                oldX = X;
                dcol *= -1;
                checked = true;
            }
        }
        if (dcol == 1){
            g.setColor(bcols[1]);
        }else{
            g.setColor(bcols[0]);
        }
        g.fillRect(x, y, 32, 32);

    }
    private long blinkTime(long hitTime){
        long time = System.currentTimeMillis();
        long timeDiff = (time - hitTime) / 1000;
        return timeDiff;
    }
}
