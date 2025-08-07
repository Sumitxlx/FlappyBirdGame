import javax.swing.*;
import java.awt.*;

public class FlappyBird {
    public static void main(String[] args) {
        // Set up the game window
        JFrame frame = new JFrame("Flappy Bird Clone");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        
        // Create and add the game panel
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        // Make the frame visible
        frame.setVisible(true);
        
        // Start the game
        gamePanel.startGame();
    }
} 