package Game;

import java.awt.geom.Rectangle2D;

public class Food {
    public static final int XSIZE = 20;
	public static final int YSIZE = 20;

    private double x, y;

    public Food (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Rectangle2D.Double getShape() {
		return new Rectangle2D.Double(x, y, XSIZE, YSIZE);
	}

    public static double generateRandom() {
        double d = XSIZE * YSIZE + 1;
		while (d >= XSIZE * YSIZE || d % XSIZE != 0 || d % YSIZE != 0) {
			d = Math.random() * 1000;
			d = (int) d;
		}
		return d;
    }

    public void newPosition(Snake snake) {
		for (Rectangle2D.Double e : snake.getParts()) {
			while (x == e.getMinX() && y == e.getMinY()) {
				x = generateRandom();
				y = generateRandom();
			}
		}
	}
}