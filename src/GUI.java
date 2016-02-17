// As created by Bastiaan Wuisman on 23-2-2015
// Created using IntelliJ IDEA


import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Math.*;

public class GUI extends JPanel implements KeyListener {
    JFrame frame = new JFrame();
    JScrollPane scrollPane = new JScrollPane(this);
    Algorithm alg;
    double multiplier = 1.0;    //used for zoom

    //initializing the frame (weird scroll pane stuff, don't worry, cause I don't understand it either)
    GUI(Algorithm algIn) {
        alg = algIn;
        frame.addKeyListener(this);

        frame.setSize(1080, 720);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel temp = new JPanel(null);
        temp.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
        frame.add(temp);
        frame.setVisible(true);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, frame.getWidth() - 15, frame.getHeight() - 45);
        temp.add(scrollPane);

        setPreferredSize(new Dimension(alg.n, scrollPane.getHeight() - 45));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        frame.setTitle("n = " + alg.n + "   steps: " + alg.steps);
        scrollPane.setBounds(0, 0, frame.getWidth() - 15, frame.getHeight() - 45);
        scrollPane.revalidate();
        switch (alg.type) {
            //array
            case 0:
                double barWidth = max(scrollPane.getWidth() / (double) alg.n, 1) * multiplier;
                setPreferredSize(new Dimension((int) (barWidth * alg.n), scrollPane.getHeight() - 45));
                revalidate();

                //draws the array
                g.setColor(Color.BLACK);
                for (int i = 0; i < alg.n; i++) {
                    g.fillRect((int) floor(i * barWidth), (int) (getHeight() - (alg.A.a[i] / (double) alg.A.max) *
                            getHeight()), (int) (((i + 1) * barWidth) - floor(i * barWidth)), getHeight());
                }

                //draws the pointers
                g.setColor(Color.RED);
                for (int pointer : alg.pointers) {
                    if (pointer >= 0) {
                        g.fillRect((int) (pointer * barWidth), (int) (getHeight() - (alg.A.a[pointer] / (double) alg.A
                                .max) * getHeight()), (int) ceil(barWidth), getHeight());
                    }
                }
                break;

            //max heap
            case 1:
                int circleSize = (int) (150 * multiplier);
                setPreferredSize(new Dimension((int) (pow(2, log2fl(alg.n)) * circleSize), (int) ((log2fl(alg.n) + 1)
                        * circleSize)));
                revalidate();

                g.setColor(Color.BLACK);
                for (int i = 0; i < alg.n; i++) {
                    g.fillOval(
                            //x
                            (int) (getWidth() * (i + 1.5) / pow(2, log2fl(i + 1)) - getWidth() - 0.45 * alg.MH.a[i] *
                                    circleSize / (double) alg.MH.max - 0.05 * circleSize),

                            //y
                            (int) ((getHeight() - circleSize) * log2fl(i + 1) / log2fl(alg.n) + 0.45 * (circleSize -
                                    alg.MH.a[i] / (double) alg.MH.max * circleSize)),

                            //width
                            (int) (0.9 * alg.MH.a[i] / alg.MH.max * circleSize + 0.1 * circleSize),

                            //height
                            (int) (0.9 * alg.MH.a[i] / alg.MH.max * circleSize + 0.1 * circleSize));
                    if (i > 0) {
                        g.drawLine(
                                //x1
                                (int) (getWidth() * (i + 1.5) / pow(2, log2fl(i + 1)) - getWidth()),

                                //y1
                                (int) ((getHeight() - circleSize) * log2fl(i + 1) / log2fl(alg.n) + 0.5 * circleSize),

                                //x2
                                (int) (getWidth() * ((i + 1) / 2 + 0.5) / pow(2, log2fl((i + 1) / 2)) - getWidth()),

                                //y2
                                (int) ((getHeight() - circleSize) * log2fl((i + 1) / 2) / log2fl(alg.n) + 0.5 *
                                        circleSize));
                    }
                }
                break;
        }
    }

    double log2fl(double x) {
        return floor(log(x) / log(2));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //zooming
        if (e.getKeyCode() == KeyEvent.VK_UP) multiplier += 0.2;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) multiplier -= 0.2;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}