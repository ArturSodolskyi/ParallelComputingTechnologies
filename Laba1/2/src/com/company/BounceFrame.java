package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {

    private Canvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new Canvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        addPockets(content);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas);
                canvas.add(ball);
                BallThread thread = ball.thread;
                thread.start();
                System.out.println("Thread name = " + thread.getName());
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }

        });


        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);

        JLabel scorePanel = new JLabel("Score: 0");
        buttonPanel.add(scorePanel);

        content.add(buttonPanel, BorderLayout.SOUTH);

        refreshScore(500, scorePanel);
    }

    private void addPockets(Container content) {
        Pocket topLeft = new Pocket(0, 0);
        Pocket topRight = new Pocket(418, 0);
        Pocket downLeft = new Pocket(0, 260);
        Pocket downRight = new Pocket(418, 260);
        canvas.add(topLeft);
        canvas.add(topRight);
        canvas.add(downLeft);
        canvas.add(downRight);
    }

    private void refreshScore(int delay, JLabel scorePanel) {
        ActionListener l1 = new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                scorePanel.setText("Score: " + canvas.getBallsInPocket());
            }
        };

        Timer t1 = new Timer(delay, l1);
        t1.start();
    }
}
