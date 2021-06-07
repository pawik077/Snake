package Game;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.horstmann.corejava.GBC;
import javax.swing.*;


public class SnakeFrame extends JFrame {
    private Score score;
    private Field field;
    private Thread thread;
    private Snake snake;
    private Snake.Direction direction = Snake.Direction.UP;
    private boolean started = false;

    public SnakeFrame() {
        initComps();
        initGame();
        initFrame();
    }

    private void initComps() {
        setLayout(new GridBagLayout());
        addKeyListener(new KeyboardHandler());

        score = new Score();
        add(score, new GBC(0,8,8,1));

        field = new Field();
        add(field, new GBC(0,0,8,8));
    }

    private void initGame() {
        snake = new Snake(field, score);
        Runnable runnable = new MakeGame(field, snake, this);
        thread = new Thread(runnable);
    }

    private void initFrame() {
        pack();
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public void newGame() {
        started = true;
        thread.start();
    }

    public void gameOver() {
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "YOU DIED!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        switch (option) {
            case JOptionPane.OK_OPTION:
                direction = Snake.Direction.UP;
                started = false;
                snake = new Snake(field, score);
                score.clear();
                field.init();
                score.repaint();
                field.repaint();
                Runnable runnable = new MakeGame(field, snake, this);
                thread = null;
                thread = new Thread(runnable);
                break;
            case JOptionPane.CANCEL_OPTION:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(getParent(), "FATAL ERROR!\nPlease restart app");
                break;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
            new SnakeFrame();
           }
        });
    }

    private class KeyboardHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (direction == Snake.Direction.DOWN) return;
                if (!started) newGame();
                if (snake != null) {
                    snake.changeDirection(Snake.Direction.UP);
                    direction = Snake.Direction.UP;
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (direction == Snake.Direction.UP) return;
                if (!started) newGame();
            	if (snake != null) {
            	    snake.changeDirection(Snake.Direction.DOWN);
            		direction = Snake.Direction.DOWN;
            	}
            }

            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            	if (direction == Snake.Direction.RIGHT) return;
            	if (!started) newGame();
            	if (snake != null) {
            		snake.changeDirection(Snake.Direction.LEFT);
            		direction = Snake.Direction.LEFT;
            	}
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            	if (direction == Snake.Direction.LEFT) return;
                if (!started) newGame();
            	if (snake != null) {
            		snake.changeDirection(Snake.Direction.RIGHT);
            		direction = Snake.Direction.RIGHT;
            	}
            }
        }
    }
}
