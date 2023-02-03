import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel {

        private static final long serialVersionUID = 1L;
        
        private final int margin, scoreAreaHeight, unitSize;
        
        private final Random random;
        
        private final Snake3Model model;
        
        public GamePanel(Snake3Model model) {
            this.model = model;
            this.margin = 10;
            this.unitSize = 25;
            this.scoreAreaHeight = 36 + margin;
            this.random = new Random();
            this.setBackground(Color.black);
            
            Dimension gameArea = model.getGameArea();
            int width = gameArea.width * unitSize + 2 * margin;
            int height = gameArea.height * unitSize + 2 * margin + scoreAreaHeight;
            this.setPreferredSize(new Dimension(width, height));
            setKeyBindings();
        }
        
        private void setKeyBindings() {
            InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = this.getActionMap();
            
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
            
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
            
            actionMap.put("up", new MovementAction(model, 'U', 'D'));
            actionMap.put("down", new MovementAction(model, 'D', 'U'));
            actionMap.put("left", new MovementAction(model, 'L', 'R'));
            actionMap.put("right", new MovementAction(model, 'R', 'L'));
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Dimension gameArea = model.getGameArea();
//            drawHorizontalGridLines(g, gameArea);
//            drawVerticalGridLines(g, gameArea);
            drawSnake(g);
            drawScore(g, gameArea);
            
            if (model.isGameOver()) {
                drawGameOver(g, gameArea);
            } else {
                drawApple(g);
            }
        }

        private void drawHorizontalGridLines(Graphics g, Dimension gameArea) {
            int y1 = scoreAreaHeight + margin;
            int y2 = y1 + gameArea.height * unitSize;
            int x = margin;
            for (int index = 0; index <= gameArea.width; index++) {
                g.drawLine(x, y1, x, y2);
                x += unitSize;
            }
        }

        private void drawVerticalGridLines(Graphics g, Dimension gameArea) {
            int x1 = margin;
            int x2 = x1 + gameArea.width * unitSize;
            int y = margin + scoreAreaHeight;
            for (int index = 0; index <= gameArea.height; index++) {
                g.drawLine(x1, y, x2, y);
                y += unitSize;
            }
        }

        private void drawApple(Graphics g) {
            // Draw apple
            g.setColor(Color.red);
            Point point = model.getAppleLocation();
            if (point != null) {
                int a = point.x * unitSize + margin + 1;
                int b = point.y * unitSize + margin + scoreAreaHeight + 1;
                g.fillOval(a, b, unitSize - 2, unitSize - 2);
            }
        }

        private void drawScore(Graphics g, Dimension gameArea) {
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 36));
            FontMetrics metrics = getFontMetrics(g.getFont());
            int width = 2 * margin + gameArea.width * unitSize;
            String text = "SCORE: " + model.getApplesEaten();
            int textWidth = metrics.stringWidth(text);
            g.drawString(text, (width - textWidth) / 2, g.getFont().getSize());
        }
        
        private void drawSnake(Graphics g) {
            // Draw snake
            Snake snake = model.getSnake();
            List<Point> cells = snake.getCells();
            Point cell = cells.get(0);
            drawSnakeCell(g, cell, Color.green);
            for (int index = 1; index < cells.size(); index++) {
//              Color color = new Color(45, 180, 0);
                // random color
                Color color = new Color(getColorValue(), getColorValue(), 
                        getColorValue());
                cell = cells.get(index);
                drawSnakeCell(g, cell, color);
            }
        }
        
        private void drawSnakeCell(Graphics g, Point point, Color color) {
            int x = margin + point.x * unitSize;
            int y = margin + scoreAreaHeight + point.y * unitSize;
            if (point.y >= 0) {
                g.setColor(color);
                g.fillRect(x, y, unitSize, unitSize);
            }
        }
        
        private int getColorValue() {
            // White has color values of 255
            return random.nextInt(64) + 191;
        }
        
        private void drawGameOver(Graphics g, Dimension gameArea) {
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 72));
            FontMetrics metrics = getFontMetrics(g.getFont());
            String text = "Game Over";
            int textWidth = metrics.stringWidth(text);
            g.drawString(text, (getWidth() - textWidth) / 2, getHeight() / 2);
        }
        
    }
 