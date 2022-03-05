import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class BubblePanel extends JPanel {
    Random rnd = new Random();
    ArrayList<Bubble> bubbleList;
    int size = 25;
    Timer timer;
    int delay = 33;
    JSlider slider;
    boolean isFlex = true;
    boolean isBubble = false;

    BubblePanel() {
        timer = new Timer(delay, new BubbleListener());
        bubbleList = new ArrayList<Bubble>();
        setBackground(Color.BLACK);
        JPanel panel = new JPanel();
        add(panel);
        JButton btnPause = new JButton("Pause");
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn.getText().equals("Pause")) {
                    timer.stop();
                    btn.setText("Start");
                } else {
                    timer.start();
                    btn.setText("Pause");
                }
            }
        });

        JLabel labelAnimationSpeed = new JLabel("Animation Speed:");
        panel.add(labelAnimationSpeed);

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int speed = slider.getValue() + 1;
                int delay = 1000 / speed;
                timer.setDelay(delay);
            }
        });
        slider.setValue(30);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(5);
        slider.setMaximum(120);
        slider.setMajorTickSpacing(30);
        panel.add(slider);
        panel.add(btnPause);
        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bubbleList = new ArrayList<Bubble>();
                repaint();
            }
        });
        panel.add(btnClear);
        addMouseListener(new BubbleListener());
        addMouseMotionListener(new BubbleListener());
        addMouseWheelListener(new BubbleListener());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Bubble b : bubbleList) b.draw(g, isBubble);
    }

    private class Bubble {
        private int x;
        private int y;
        private int size;
        private Color color;
        private int xSpeed, ySpeed;
        private final int MAX_SPEED = 5;

        Bubble(int x, int y, int size, boolean isFlex, boolean isBubble) {
            if (isBubble) {
                this.x = x;
                this.y = y;
            } else {
                this.x = (x / size) * size + size / 2;
                this.y = (y / size) * size + size / 2;
            }

            this.size = size;
            color = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            if (!isFlex) {
                xSpeed = rnd.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
                ySpeed = rnd.nextInt(MAX_SPEED * 2 + 1) - MAX_SPEED;
                if (xSpeed == 0) xSpeed = 1;
                if (ySpeed == 0) ySpeed = 1;
            } else {
                xSpeed = ySpeed = 2;
            }
        }

        public void draw(Graphics g, boolean isBubble) {
            g.setColor(color);
            if (isBubble) {
                g.fillOval(x - size / 2, y - size / 2, size, size);
            } else {
                g.fillRect(this.x - this.size / 2, this.y - this.size / 2, this.size, this.size);
            }
        }

        public void update() {
            x += xSpeed;
            y += ySpeed;
            if (x - size / 2 <= 0 || x + size / 2 >= getWidth())
                xSpeed = -xSpeed;
            if (y - size / 2 <= 0 || y + size / 2 >= getHeight())
                ySpeed = -ySpeed;
        }
    }

    private class BubbleListener extends MouseAdapter implements ActionListener {
        public void mousePressed(MouseEvent e) {
            bubbleList.add(new Bubble(e.getX(), e.getY(), size, isFlex, isBubble));
            repaint();
        }
        public void mouseDragged(MouseEvent e) {
            bubbleList.add(new Bubble(e.getX(), e.getY(), size, isFlex, isBubble));
            repaint();
        }
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (System.getProperty("os.name").startsWith("Mac"))
                size += e.getUnitsToScroll();
            else
                size -= e.getUnitsToScroll();
            if (size < 3) size = 3;
        }
        public void actionPerformed(ActionEvent e) {
            for (Bubble b : bubbleList) b.update();
            repaint();
        }
    }
}