package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Score extends JPanel {

	public static final int PANEL_WIDTH = 500;
	public static final int PANEL_HEIGHT = 100;

    private final Font FONT;
	private final String SCORE_LABEL = "SCORE:";
	private String score;

	public Score() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setBackground(Color.GRAY);

		score = "0";
		FONT = new Font("SansSerif", Font.BOLD, 25);
	}

    public void addPoints(int newPoints) {
		int oldScore = Integer.parseInt(score);
		int newScore = oldScore + newPoints;
		score = oldScore + "";
		repaint();
	}

    public void clear() {
		score = "0";
	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		g.setFont(FONT);
		g.setPaint(Color.GREEN);
		g.drawString(SCORE_LABEL, 15, 32);
		g.setPaint(Color.BLUE);
		g.drawString(score, 105, 32);
	}
}