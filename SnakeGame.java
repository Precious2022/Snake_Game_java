import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class SnakeGame extends JFrame implements KeyListener {
    private static final int BOX_SIZE = 20;
    private static final int GRID_WIDTH = 20;
    private static final int GRID_HEIGHT = 20;
    private static final int INITIAL_GAME_SPEED = 150;
    private static final int SPEED_INCREMENT = 5;
    private static final int MAX_LEVEL = 10;
    private static final int SPECIAL_FOOD_SCORE_INCREMENT = 50;


    private ArrayList<Point> snake;
    private Point food;
    private int direction;
    private Timer timer;
    private int score;
    private int gameSpeed;
    private int level;
    private boolean passThroughBorders;
    private boolean specialFoodActive;
    private Point specialFood;
     // Add a new constant for special food score increment
    //  private static final int SPECIAL_FOOD_SCORE_INCREMENT = 50;

    public SnakeGame() {
        setTitle("Snake Game");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set the window to full screen
        setUndecorated(true); // Hide window decorations (title bar, close button, etc.)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setTitle("Snake Game");
        setSize(BOX_SIZE * GRID_WIDTH, BOX_SIZE * GRID_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(this);
        

        snake = new ArrayList<>();
        snake.add(new Point(GRID_WIDTH / 2, GRID_HEIGHT / 2));
        generateFood();
        direction = KeyEvent.VK_RIGHT; // Start moving right
        timer = new Timer(INITIAL_GAME_SPEED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
                checkCollision();
                checkFood();
                checkLevel();
                repaint();

                
            }
        });
        timer.start();

        score = 0;
        gameSpeed = INITIAL_GAME_SPEED;
        level = 1;

        
    }



    private void generateFood() {
        Random random = new Random();
        int x = random.nextInt(GRID_WIDTH);
        int y = random.nextInt(GRID_HEIGHT);
        food = new Point(x, y);
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead;
        switch (direction) {
            case KeyEvent.VK_UP:
                newHead = new Point(head.x, head.y - 1);
                break;
            case KeyEvent.VK_DOWN:
                newHead = new Point(head.x, head.y + 1);
                break;
            case KeyEvent.VK_LEFT:
                newHead = new Point(head.x - 1, head.y);
                break;
            case KeyEvent.VK_RIGHT:
                newHead = new Point(head.x + 1, head.y);
                break;
            default:
                return;
        }
        snake.add(0, newHead);
        if (!newHead.equals(food)) {
            snake.remove(snake.size() - 1);
        } else {
            score += 10;
            generateFood();
            gameSpeed -= SPEED_INCREMENT;
            if (gameSpeed < 50) {
                gameSpeed = 50;
            }
        }
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= GRID_WIDTH || head.y < 0 || head.y >= GRID_HEIGHT) {
            gameOver();
            return;
        }
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver();
                return;
            }
        }
    }

    private void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            score += 10;
            generateFood();
        } else if (specialFoodActive && head.equals(specialFood)) {
            score += SPECIAL_FOOD_SCORE_INCREMENT;
            specialFoodActive = false;
        }
    }

    private void checkLevel() {
        if (level < MAX_LEVEL && score >= level * 100) {
            level++;
            JOptionPane.showMessageDialog(this, "Level Up: Level " + level);
        }
    }

    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Game Over. Your score: " + score);
        snake.clear();
        snake.add(new Point(GRID_WIDTH / 2, GRID_HEIGHT / 2));
        generateFood();
        direction = KeyEvent.VK_RIGHT;
        score = 0;
        gameSpeed = INITIAL_GAME_SPEED;
        level = 1;
        timer.setDelay(INITIAL_GAME_SPEED);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_SIZE * GRID_WIDTH, BOX_SIZE * GRID_HEIGHT);

        g.setColor(Color.GREEN);
        for (Point point : snake) {
            g.fillRect(point.x * BOX_SIZE, point.y * BOX_SIZE, BOX_SIZE, BOX_SIZE);
        }
        

        g.setColor(Color.RED);
        g.fillRect(food.x * BOX_SIZE, food.y * BOX_SIZE, BOX_SIZE, BOX_SIZE);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
        g.drawString("Level: " + level, 10, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int newDirection = e.getKeyCode();
        if ((newDirection == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) ||
                (newDirection == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) ||
                (newDirection == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) ||
                (newDirection == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT)) {
            direction = newDirection;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGame().setVisible(true);
            }
        });
    }
}



