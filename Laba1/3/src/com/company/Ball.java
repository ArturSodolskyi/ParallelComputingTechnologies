package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball extends Rectangle {
    private Component canvas;
    public BallThread thread;
    private int dx = 2;
    private int dy = 2;
    private Color color;

    public Ball(Component c, Color color, int priority) {
        this.canvas = c;
        if (Math.random() < 0.5) {
            this.x = 100;//new Random().nextInt(0, this.canvas.getWidth());
            this.y = 100;//0;
        } else {
            this.x = 100;//0;
            this.y = 100;//new Random().nextInt(0, this.canvas.getHeight());
        }

        this.width = 20;
        this.height = 20;
        this.color = color;
        this.thread = new BallThread(this);
        this.thread.setPriority(priority);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
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
