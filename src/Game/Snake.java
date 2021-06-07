package Game;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Snake {


    public enum Direction {
	    UP, DOWN, LEFT, RIGHT
    }

    public static final int XSIZE = 20;
	public static final int YSIZE = 20;
	
	private Field field;
	private Score score;
	private List<Rectangle2D.Double> snakeParts;
	private Direction direction;

	private Rectangle2D.Double temp;
	private Rectangle2D.Double back;

	private boolean gameOver = false;

    private void initDefaults() {
		snakeParts = Collections
				.synchronizedList(new ArrayList<>());
		snakeParts.add(new Rectangle2D.Double(260, 260, 20, 20));
		snakeParts.add(new Rectangle2D.Double(260, 280, 20, 20));
		snakeParts.add(new Rectangle2D.Double(260, 300, 20, 20));
		snakeParts.add(new Rectangle2D.Double(260, 320, 20, 20));
	}

    

    public Snake(Field field, Score score) {
		this.field = field;
		this.score = score;
		initDefaults();
	}

    public boolean isGameOver() {
		return gameOver;
	}

    public void changeDirection(Direction direction) {
		this.direction = direction;
	}

    public List<Rectangle2D.Double> getParts() {
		return snakeParts;
	}

    private void moveBody() {
		for (int i = snakeParts.size() - 1; i > 0; i--) {
			if (i == snakeParts.size() - 1) {
				back = (Rectangle2D.Double) snakeParts.get(i).clone();
			}
			temp = (Rectangle2D.Double) snakeParts.get(i - 1).clone();
			snakeParts.set(i, temp);
		}
	}

    public void check() {
		Rectangle2D.Double head = snakeParts.get(0);
		Food apple = field.getFood();
		
		// Collision with itself
		for (int i = 1; i < snakeParts.size(); i++) {
			if (head.getMinX() == snakeParts.get(i).getMinX()
					&& head.getMinY() == snakeParts.get(i).getMinY()) {
				gameOver = true;
				return;
			}
		}

		// new food
		if (head.getMinX() == apple.getShape().getMinX()
				&& head.getMinY() == apple.getShape().getMinY()) {
			score.addPoints(10);
			apple.newPosition(this);
			snakeParts.add(back);
		}
	}

    public void move() {
		switch (direction) {
		case UP:
			moveBody();
			// head
			snakeParts.set(0, new Rectangle2D.Double(snakeParts.get(0).getMinX(),
					snakeParts.get(0).getMinY() - 20, XSIZE, YSIZE));
			if (snakeParts.get(0).getMinY() < 0) {
				gameOver = true;
			}
			break;

		case DOWN:
			moveBody();
			// head
			snakeParts.set(0, new Rectangle2D.Double(snakeParts.get(0).getMinX(),
					snakeParts.get(0).getMinY() + 20, XSIZE, YSIZE));
			if (snakeParts.get(0).getMaxY() > field.getBounds().getMaxY()) {
				gameOver = true;
			}
			break;

		case LEFT:
			moveBody();
			// head
			snakeParts.set(0, new Rectangle2D.Double(
					snakeParts.get(0).getMinX() - 20, snakeParts.get(0)
							.getMinY(), XSIZE, YSIZE));
			if (snakeParts.get(0).getMinX() < 0) {
				gameOver = true;
			}
			break;

		case RIGHT:
			moveBody();
			// head
			snakeParts.set(0, new Rectangle2D.Double(
					snakeParts.get(0).getMinX() + 20, snakeParts.get(0)
							.getMinY(), XSIZE, YSIZE));
			if (snakeParts.get(0).getMaxX() > field.getBounds().getMaxX()) {
				gameOver = true;
			}
			break;

		default:
			new Exception("Wrong Direction value").printStackTrace();
			break;
		}
	}


}
