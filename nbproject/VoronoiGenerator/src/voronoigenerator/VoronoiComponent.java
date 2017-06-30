package voronoigenerator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * Mistakes that were corrected from previous submission: 
 * 1. Spelling of the the default constructor 
 * 2. Remove 'void' from default constructor(very careless of me) 
 * 3. Completed the mouse listener 
 * 4. Completed the getIndexOfCloseSeed method 
 * 5. Added 2 Utility Methods
 * 6. Decided to use MousePressed rather then clicked in order to attempt the
 * dragged portion of the test.
 * 
 * Things still not complete:
 * 1. Could not get the MouseDragged portion to work.
 *
 * @author Sadan Mallhi
 * @version Apr 24, 2014
 */
public class VoronoiComponent extends JComponent {

    final ArrayList<coordinate> points;
    final ArrayList<Color> colors;
    public int useEuclidean;
    int x;
    int y;
    Color BGColor = Color.BLACK;

    public VoronoiComponent() {
        this.points = new ArrayList();
        this.colors = new ArrayList();
        this.useEuclidean = 1;

        class MyMouseListener extends MouseAdapter {

//            @Override
//            public void mouseClicked(MouseEvent e) {
//                float r = (float) Math.random();
//                float g = (float) Math.random();
//                float b = (float) Math.random();
//                //test
//                //System.out.println("[" + e.getX() + "," + e.getY() + "]");
//                points.add(new coordinate(e.getX(), e.getY()));
//                colors.add(new Color(r, g, b));
//                x = e.getX();
//                y = e.getY();
//                repaint();
//            }
            @Override
            public void mouseReleased(MouseEvent e) {
                float r = (float) Math.random();
                float g = (float) Math.random();
                float b = (float) Math.random();
                //test
                //System.out.println("[" + e.getX() + "," + e.getY() + "]");
                points.add(new coordinate(e.getX(), e.getY()));
                colors.add(new Color(r, g, b));
                x = e.getX();
                y = e.getY();
                repaint();
            }

//            @Override
//            public void mouseDragged(MouseEvent e) {
//                points.add(new coordinate(e.getX(), e.getY()));
//                colors.add(Color.RED);
//                x = e.getX();
//                y = e.getY();
//                repaint();
//            }
        }

        MyMouseListener listener = new MyMouseListener();
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(listener);
    }

    /**
     *
     * @param g the current graphics content
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        // AntiAliasing  (Causes the program to lag and repond slower)
//        g2.setRenderingHint(
//                RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
        // Initial Background color
        g2.setColor(BGColor);
        g2.fill(new Rectangle(getWidth(), getHeight()));

        // draw new rectangle
        if (!points.isEmpty()) {
            for (x = 0; x < w; x++) {
                for (y = 0; y < h; y++) {
                    int i = getIndexOfClosestSeed(x, y);
                    g2.setColor(colors.get(i));
                    g2.fill(new Rectangle(x, y, 1, 1));
                }
            }
        }
    }

    private int getIndexOfClosestSeed(int x, int y) {
        int currentseedX;
        int currentseedY;
        int distance;
        int min = 1000000;
        int indexOfClosest = 0;

        if (useEuclidean == 1) {
            // Euclidean Distance
            for (int i = 0; i < points.size(); i++) {
                currentseedX = points.get(i).seedX;
                currentseedY = points.get(i).seedY;
                distance = (int) Math.sqrt((int) Math.pow(x - currentseedX, 2)
                        + (int) Math.pow(y - currentseedY, 2));
                if (distance < min) {
                    min = distance;
                    indexOfClosest = i;
                }

            }

        }
        if (useEuclidean == 0) {
            // Manhatan Distance
            for (int i = 0; i < points.size(); i++) {
                currentseedX = points.get(i).seedX;
                currentseedY = points.get(i).seedY;
                distance = Math.abs((x - currentseedX))
                        + (Math.abs(y - currentseedY));
                if (distance < min) {
                    min = distance;
                    indexOfClosest = i;
                }

            }
        }

        return indexOfClosest;
    }

    public void setUseEuclidean(int useEuclidean) {
        this.useEuclidean = useEuclidean;
        repaint();
    }

    void clear() {
        points.clear();
        colors.clear();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new VoronoiComponent());

        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}

class coordinate {

    int seedX;
    int seedY;

    public int getX() {
        return seedX;
    }

    public int getY() {
        return seedY;
    }

    public coordinate(int x, int y) {
        this.seedX = x;
        this.seedY = y;
    }
}
