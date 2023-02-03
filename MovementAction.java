import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MovementAction extends AbstractAction {

        private static final long serialVersionUID = 1L;
        
        private final char newDirection, oppositeDirection;
        
        private final Snake3Model model;

        public MovementAction(Snake3Model model, char newDirection,
                char oppositeDirection) {
            this.model = model;
            this.newDirection = newDirection;
            this.oppositeDirection = oppositeDirection;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (model.isRunning()) {
                Snake snake = model.getSnake();
                char direction = snake.getDirection();
                if (direction != oppositeDirection && direction != newDirection) {
                    snake.setDirection(newDirection);
//                  System.out.println("New direction: " + newDirection);
                }
            }
        }
        
    }
