import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Snake3Game implements Runnable {

    public static void main(String arg[]) {
        SwingUtilities.invokeLater(new Snake3Game());
    }
    
    private final GamePanel gamePanel;
    
    private final JButton restartButton;
    
    private final Snake3Model model;
    
    public Snake3Game() {
        this.model = new Snake3Model();
        this.restartButton = new JButton("Start Game");
        this.gamePanel = new GamePanel(model);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.black);
        
        restartButton.addActionListener(new ButtonListener(this, model));
        panel.add(restartButton);
        
        return panel;
    }
    
    public JButton getRestartButton() {
        return restartButton;
    }

    public void repaint() {
        gamePanel.repaint();
    }
}