package Game;

public class MakeGame implements Runnable {
    public static final int DELAY = 200;
    private SnakeFrame snakeFrame;
    private Field field;
    private Snake snake;
    private Food food;

    public MakeGame(Field field, Snake snake, SnakeFrame snakeFrame) {
        food = new Food(Food.generateRandom(),Food.generateRandom());
        this.snakeFrame = snakeFrame;
        this.snake = snake;
        this.field = field;
        this.field.setSnakeParts(snake.getParts());
        this.field.setFood(food);
    }

    @Override
    public void run() {
        try {
            while(true) {
                snake.move();
                snake.check();
                if(snake.isGameOver())
                    Thread.currentThread().interrupt();
                if(!Thread.currentThread().isInterrupted())
                    field.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException ex) {
            snakeFrame.gameOver();
        }
    }
}