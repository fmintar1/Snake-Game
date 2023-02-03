import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Snake {
        
	private char direction;
        
        private List<Point> cells;
        
        public Snake() {
            this.cells = new ArrayList<>();
            initialize();
        }
        
        public void initialize() {
            this.direction = 'R';
            cells.clear();
            for (int x = 5; x >= 0; x--) {
                cells.add(new Point(x, 0));
            }
        }

        public void addHead(Point head) {
            cells.add(0, head);
        }
        
        public void addTail(Point tail) {
            cells.add(tail);
        }
        
        public void removeTail() {
            cells.remove(cells.size() - 1);
        }
        
        public Point getHead() {
            return cells.get(0);
        }
        
        public Point getTail() {
            return cells.get(cells.size() - 1);
        }

        public char getDirection() {
            return direction;
        }

        public void setDirection(char direction) {
            this.direction = direction;
        }

        public List<Point> getCells() {
            return cells;
        }
        
    }