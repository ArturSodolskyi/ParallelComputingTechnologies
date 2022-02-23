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

        JButton addRedBallButton = new JButton("Add red ball");
        JButton addBlueBallButton = new JButton("Add blue ball");
        JButton buttonStop = new JButton("Stop");

        addRedBallButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ball ball = new Ball(canvas, Color.red, Thread.MAX_PRIORITY);
                addBall(ball);
            }
        });

        addBlueBallButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                Ball ball = new Ball(canvas, Color.blue, Thread.MIN_PRIORITY);
//                addBall(ball);

//                oneRedAndOneBlueBallExperiment();
                minPriorityBlueBallsExperiment(100);
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonPanel.add(addRedBallButton);
        buttonPanel.add(addBlueBallButton);
        buttonPanel.add(buttonStop);

        JLabel scorePanel = new JLabel("Score: 0");
        buttonPanel.add(scorePanel);

        content.add(buttonPanel, BorderLayout.SOUTH);

        refreshScore(500, scorePanel);
    }

    private void oneRedAndOneBlueBallExperiment() {
        var blueBallThread = createBalls(Color.blue, Thread.NORM_PRIORITY, 1)[0];
        var redBallThread = createBalls(Color.red, Thread.MAX_PRIORITY, 1)[0];
        blueBallThread.start();
        redBallThread.start();
        System.out.println("Thread name = " + blueBallThread.getName());
        System.out.println("Thread name = " + redBallThread.getName());
    }

    private void minPriorityBlueBallsExperiment(int count) {
        var blueBallThreads = createBalls(Color.blue, Thread.MIN_PRIORITY, count);
        var redBallThread = createBalls(Color.red, Thread.MAX_PRIORITY, 1)[0];
        redBallThread.start();
        for (BallThread thread : blueBallThreads) {
            thread.start();
        }
    }

    private BallThread[] createBalls(Color color, int priority, int count) {
        BallThread[] result = new BallThread[count];
        for (int i = 0; i < count; i++) {
            Ball ball = new Ball(canvas, color, priority);
            canvas.add(ball);
            result[i] = ball.thread;
        }

        return result;
    }

    private void addBall(Ball ball) {
        canvas.add(ball);
        BallThread thread = ball.thread;
        thread.start();
        System.out.println("Thread name = " + thread.getName());
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
