package Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Field extends JPanel {
    public static final int FIELD_WIDTH = 600;
    public static final int FIELD_HEIGHT = 600;
    private List<Rectangle2D.Double> snakeParts;
    private Food food;

    public Field() {
        setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
        setBackground(Color.BLACK);
        init();
    }
    public void init() {
        snakeParts = new ArrayList<>();
        snakeParts.add(new Rectangle2D.Double(260,260,20,20));
        snakeParts.add(new Rectangle2D.Double(260,280,20,20));
        snakeParts.add(new Rectangle2D.Double(260,300,20,20));
        snakeParts.add(new Rectangle2D.Double(260,320,20,20));
    }
    public void setSnakeParts(List<Rectangle2D.Double> snakeParts) {
        this.snakeParts = snakeParts;
    }
    public void setFood(Food food) {
        this.food = food;
    }
    public Food getFood() {
        return food;
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setPaint(Color.RED);
        g.fillRect((int)food.getShape().getMinX() + 5,(int)food.getShape().getMinY() + 5, 10,10);
        g.setPaint(Color.GREEN);
        for(Rectangle2D part : snakeParts)
            g.fillRect((int)part.getMinX() + 2, (int)part.getMinY() + 2, 16, 16);
        g.setPaint(Color.YELLOW);
        g.fillRect((int)snakeParts.get(0).getMinX() + 2, (int)snakeParts.get(0).getMinY() + 2, 16, 16);
    }

}