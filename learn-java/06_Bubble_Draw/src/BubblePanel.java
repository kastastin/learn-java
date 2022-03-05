import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Random;

public class BubblePanel extends JPanel {
    Random rnd = new Random();
    ArrayList<Bubble> bubbleList;
    int size = 25;
    boolean isBubble = false;

    BubblePanel() {
        bubbleList = new ArrayList<Bubble>();
        setBackground(Color.BLACK);
        addMouseListener(new BubbleListener());
        addMouseMotionListener(new BubbleListener());
        addMouseWheelListener(new BubbleListener());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Bubble b : bubbleList) b.draw(g);
    }

    private class Bubble {
        private int x;
        private int y;
        private int size;
        private Color color;

        Bubble(int x, int y, int size, boolean isBubble) {
            if (isBubble) {
                this.x = x;
                this.y = y;
            } else {
                this.x = (x / size) * size + size / 2;
                this.y = (y / size) * size + size / 2;
            }
            this.size = size;
            this.color = new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillOval(x - size / 2, y - size / 2, size, size);
        }
    }

    private class BubbleListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            bubbleList.add(new Bubble(e.getX(), e.getY(), size, isBubble));
            repaint();
        }
        public void mouseDragged(MouseEvent e) {
            bubbleList.add(new Bubble(e.getX(), e.getY(), size, isBubble));
            repaint();
        }
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (System.getProperty("os.name").startsWith("Mac")) {
                size += e.getUnitsToScroll();
            } else {
                size -= e.getUnitsToScroll();
            }
            if (size < 3) size = 3;
        }
    }
}

