package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Rectangle {
    private Component canvas;
    public BallThread thread;
    private int dx = 2;
    private int dy = 2;

    public Ball(Component c) {
        this.canvas = c;
        if (Math.random() < 0.5) {
            this.x = new Random().nextInt(0, this.canvas.getWidth());
            this.y = 0;
        } else {
            this.x = 0;
            this.y = new Random().nextInt(0, this.canvas.getHeight());
        }

        this.width = 20;
        this.height = 20;
        this.thread = new BallThread(this);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, this.width, this.height));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + this.width >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - this.width;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + this.height >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - this.height;
            dy = -dy;
        }
        this.canvas.repaint();
    }
}
