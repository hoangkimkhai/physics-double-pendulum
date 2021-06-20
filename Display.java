package doublepen;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    private int trans_x = 300;
    private int trans_y = 300;
    private double a1 = Math.PI /2;
    private double a2 = Math.PI / 2;
    private int l1 = 120;
    private int l2 = 120;
    private int r1 = 20;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double a1_v = 0;
    private double a2_v = 0;
    private double a1_a = 0.01;
    private double a2_a = -0.001;
    private int m1 = 20;
    private int m2 = 20;
    private static final double G = 9.8;

    public Display() {

    }

    public void display() {
        this.setSize(1280, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        MyDrawPanel mdp = new MyDrawPanel();
        this.getContentPane().add(mdp);
        this.setVisible(true);
        while (true) {

            double num1 = -G * (2 * m1 + m2) * Math.sin(a1);
            double num2 = -m2 * G * Math.sin(a1 - 2 * a2);
            double num3 = -2 * Math.sin(a1 - a2) * m2;
            double num4 = (a2_v * a2_v * l2 + a1_v * a1_v * l1 * Math.cos(a1 - a2));
            double num = num1 + num2 + num3 * num4;

            double den = l1 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));


            double num11 = 2 * Math.sin(a1 - a2);
            double num12 = (a1_v * a1_v * l1 * (m1 + m2));
            double num13 = G * (m1 + m2) * Math.cos(a1);
            double num14 = a2_v * a2_v * l2 * m2 * Math.cos(a1 - a2);

            double num10 = num11 * (num12 + num13 + num14);

            double den2 = l2 * (2 * m1 + m2 - m2 * Math.cos(2 * a1 - 2 * a2));

            a1_a = num / den;
            a2_a = num10 / den2;
            a1 += a1_v;
            a2 += a2_v;

            a1_v += a1_a;
            a2_v += a2_a;


            mdp.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class MyDrawPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g1) {
            Graphics2D g = (Graphics2D) g1;
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setStroke(new BasicStroke(3));
            g.setColor(Color.BLACK);


            x1 = trans_x + l1 * Math.sin(a1);
            y1 = trans_y + l1 * Math.cos(a1);
            int x11 = (int) x1;
            int y11 = (int) y1;


            x2 = x11 + l1 * Math.sin(a2);
            y2 = y11 + l1 * Math.cos(a2);
            int x22 = (int) x2;
            int y22 = (int) y2;
            g.drawLine(trans_x, trans_y, x11, y11);
            g.fillOval(x11 - r1 / 2, y11 - r1 / 2, r1, r1);
            g.drawLine(x11, y11, x22, y22);
            g.fillOval(x22 - r1 / 2, y22 - r1 / 2, r1, r1);
        }
    }
}
