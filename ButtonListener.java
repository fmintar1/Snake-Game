import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class ButtonListener implements ActionListener {
        
        private final int delay;
        
        private final Snake3Game view;
        
        private final Snake3Model model;
        
        private final Timer timer;

        public ButtonListener(Snake3Game view, Snake3Model model) {
            this.view = view;
            this.model = model;
            this.delay = 75;
            this.timer = new Timer(delay, new TimerListener(view, model));
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();
            String text = button.getText();
            
            if (text.equals("Start Game")) {
                button.setText("Restart Game");
            } 
            
            button.setEnabled(false);
            model.initialize();
            timer.restart();
        }
        
    }
    