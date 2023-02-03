import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.Random;

public class Snake3Model {
        
        private boolean isGameOver, isRunning;
        
        private int applesEaten;
        
        private Dimension gameArea;
        
        private Point appleLocation;
        
        private Random random;
        
        private Snake snake;
        
        public Snake3Model() {
            this.random = new Random();
            this.snake = new Snake();
            this.gameArea = new Dimension(24, 24);
        }
        
        public void initialize() {
            this.isRunning = true;
            this.isGameOver = false;
            this.snake.initialize();
            this.applesEaten = 0;
            
            Point point = generateRandomAppleLocation();
            // Make sure first apple isn't under snake
            int y = (point.y == 0) ? 1 : point.y;
            this.appleLocation = new Point(point.x, y);
        }
        
        public void checkCollisions() {
            Point head = snake.getHead();
            
            // Check for snake going out of the game area
            if (head.x < 0 || head.x > gameArea.width) {
                isGameOver = true;
                return;
            }
            
            if (head.y < 0 || head.y > gameArea.height) {
                isGameOver = true;
                return;
            }
            
            // Check for snake touching itself
            List<Point> cells = snake.getCells();
            for (int index = 1; index < cells.size(); index++) {
                Point cell = cells.get(index);
                if (head.x == cell.x && head.y == cell.y) {
                    isGameOver = true;
                    return;
                }
            }
        }
        
        public Point generateRandomAppleLocation() {
            int x = random.nextInt(gameArea.width);
            int y = random.nextInt(gameArea.height);
            this.appleLocation = new Point(x, y);
            return getAppleLocation();
        }
        
        public void incrementApplesEaten() {
            this.applesEaten++;
        }
        
        public boolean isRunning() {
            return isRunning;
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }

        public boolean isGameOver() {
            return isGameOver;
        }

        public void setGameOver(boolean isGameOver) {
            this.isGameOver = isGameOver;
        }

        public Dimension getGameArea() {
            return gameArea;
        }

        public int getApplesEaten() {
            return applesEaten;
        }

        public Point getAppleLocation() {
            return appleLocation;
        }

        public Snake getSnake() {
            return snake;
        }
        
    }
    