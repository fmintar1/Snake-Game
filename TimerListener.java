import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerListener implements ActionListener {
        
        private final Snake3Game view;
        
        private final Snake3Model model;

        public TimerListener(Snake3Game view, Snake3Model model) {
            this.view = view;
            this.model = model;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            moveSnake();
            checkApple();
            model.checkCollisions();
            if (model.isGameOver()) {
                Timer timer = (Timer) event.getSource();
                timer.stop();
                model.setRunning(false);
                view.getRestartButton().setEnabled(true);
            }
            view.repaint();
        }
        
        private void moveSnake() {
            Snake snake = model.getSnake();
            Point head = (Point) snake.getHead().clone();
            
            switch (snake.getDirection()) {
            case 'U':
                head.y--;
                break;
            case 'D':
                head.y++;
                break;
            case 'L':
                head.x--;
                break;
            case 'R':
                head.x++;
                break;
            }
            
            snake.removeTail();
            snake.addHead(head);
            
//          System.out.println(Arrays.toString(cells.toArray()));
        }
        
        private void checkApple() {
            Point appleLocation = model.getAppleLocation();
            Snake snake = model.getSnake();
            Point head = snake.getHead();
            Point tail = (Point) snake.getTail().clone();
            
            if (head.x == appleLocation.x && head.y == appleLocation.y) {
                model.incrementApplesEaten();
                snake.addTail(tail);
                model.generateRandomAppleLocation();
            }
        }
        
    }
    