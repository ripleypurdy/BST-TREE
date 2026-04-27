import java.awt.Graphics;
public class DrawInfo extends Node implements Comparable<DrawInfo> {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private int x;
    private int y;
    private int width;
    private int height;
    public int level;
    public int rowOrdinal;
    public Node node;
    public DrawInfo(Node node) {
        this.node = node;
    }

    public void setLocation(int level, int rowOrdinal) {
        this.level = level;
        this.rowOrdinal = rowOrdinal;
        this.setPixelLocation(rowOrdinal * 40, level * 60 + 10);
    }

    private void setPixelLocation(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
    }

    public void draw(Graphics g) {
        // draw self only if we are not null
        if (this.node == null) {
            return;
        }
        g.drawOval(x, y, WIDTH, HEIGHT);
        String display = node.getNodeDisplay().trim();
        if (display.length() > 0 && display.length() < 3) {
            int centeringOffset = 4;
            g.drawString(display, x + centeringOffset, y + HEIGHT - centeringOffset);
        }
        // draw arrows to children
        for (Node child : this.getChildren()) {
            DrawInfo diChild = (DrawInfo) child;
            // don't draw arrows to children that are too far out
            if (diChild.rowOrdinal <= TreePanel.MAX_ROW_COUNT) {
                drawArrow(g, diChild.node, x + WIDTH / 2, y + HEIGHT, diChild.x + WIDTH
                    / 2, diChild.y - 3);
            }
        }
    }

    public String toString() {
        return this.node == null ? "<null>" : this.node.toString();
    }

    public void drawArrow(Graphics g, Node child, int x1, int y1, int x2, int y2) {
        // draw arrow shaft
        g.drawLine(x1, y1, x2, y2 - 5);
        if (child == null) {
            // draw an X
            g.drawLine(x2 - 5, y2 - 5, x2 + 5, y2);
            g.drawLine(x2 - 5, y2, x2 + 5, y2 - 5);
        } else {
            // draw the arrow head
            g.drawLine(x2 - 5, y2 - 5, x2 + 5, y2 - 5);
            g.drawLine(x2 + 5, y2 - 5, x2, y2);
            g.drawLine(x2 - 5, y2 - 5, x2, y2);
        }
    }

    public boolean inside(int x, int y) {
        if (x > this.x && y > this.y) {
            return (x <= this.x + this.width && y <= this.y + this.height);
        }
        return false;
    }

    @Override
    public int compareTo(DrawInfo o) {
        // use x & y as equality
        if (this.x == o.x && this.y == o.y) {
            return 0;
        }
        if (this.x < o.x) {
            return -1;
        } else if (this.x > o.x) {
            return 1;
        }
        return Integer.compare(this.y, o.y);
}
}
