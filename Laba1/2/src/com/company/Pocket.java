package com.company;

import java.awt.*;

public class Pocket extends Rectangle {

    public Pocket(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 20;
        this.height = 20;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fillRect(this.x, this.y, this.width, this.height);
    }
}
