package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private int ballsInPocket = 0;
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pocket> pockets = new ArrayList<>();

    public void add(Ball b) {
        this.balls.add(b);
    }
    public void add(Pocket p) {
        this.pockets.add(p);
    }

    @Override
    public void paintComponent(Graphics g) {
        checkCollision();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            b.draw(g2);
        }

        for (int i = 0; i < pockets.size(); i++) {
            Pocket p = pockets.get(i);
            p.draw(g2);
        }
    }

    private void checkCollision() {
        for (int i = 0; i < pockets.size(); i++) {
            Pocket p = pockets.get(i);
            for (int j = 0; j < balls.size(); j++) {
                Ball b = balls.get(j);
                if (p.intersects(b)) {
                    balls.remove(j);
                    System.out.println(b.thread.getName() + " was interrupted.");
                    b.thread.interrupt();
                    this.ballsInPocket++;
                }
            }
        }
    }

    public int getBallsInPocket() {
        return this.ballsInPocket;
    }
}
