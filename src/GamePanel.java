import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    // Game constants
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GROUND_HEIGHT = 100;
    private static final int BIRD_SIZE = 20;
    private static final int PIPE_WIDTH = 80;
    private static final int PIPE_GAP = 150;
    private static final int PIPE_SPEED = 3;
    private static final double GRAVITY = 0.5;
    private static final double FLAP_STRENGTH = -8;
    private static final int FRAME_RATE = 60;
    
    // Game objects
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private GameManager gameManager;
    private Timer gameTimer;
    private Random random;
    
    // Game state
    private enum GameState {
        MENU, PLAYING, GAME_OVER
    }
    private GameState gameState;
    
    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(135, 206, 235)); // Sky blue
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        
        // Initialize game objects
        bird = new Bird(WIDTH / 4, HEIGHT / 2);
        pipes = new ArrayList<>();
        gameManager = new GameManager();
        random = new Random();
        gameState = GameState.MENU;
        
        // Set up game timer
        gameTimer = new Timer(1000 / FRAME_RATE, this);
    }
    
    public void startGame() {
        gameTimer.start();
        requestFocusInWindow();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw background
        drawBackground(g2d);
        
        // Draw game elements based on state
        switch (gameState) {
            case MENU:
                drawMenu(g2d);
                break;
            case PLAYING:
                drawGame(g2d);
                break;
            case GAME_OVER:
                drawGame(g2d);
                drawGameOver(g2d);
                break;
        }
    }
    
    private void drawBackground(Graphics2D g2d) {
        // Sky background
        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Ground
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(0, HEIGHT - GROUND_HEIGHT, WIDTH, GROUND_HEIGHT);
        
        // Ground border
        g2d.setColor(new Color(0, 100, 0));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, HEIGHT - GROUND_HEIGHT, WIDTH, HEIGHT - GROUND_HEIGHT);
    }
    
    private void drawMenu(Graphics2D g2d) {
        // Title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        String title = "FLAPPY BIRD";
        FontMetrics fm = g2d.getFontMetrics();
        int titleX = (WIDTH - fm.stringWidth(title)) / 2;
        int titleY = HEIGHT / 3;
        g2d.drawString(title, titleX, titleY);
        
        // Instructions
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        String instruction = "Press SPACE to Start";
        fm = g2d.getFontMetrics();
        int instX = (WIDTH - fm.stringWidth(instruction)) / 2;
        int instY = HEIGHT / 2 + 50;
        g2d.drawString(instruction, instX, instY);
        
        // Draw bird in menu
        bird.draw(g2d);
    }
    
    private void drawGame(Graphics2D g2d) {
        // Draw pipes
        for (Pipe pipe : pipes) {
            pipe.draw(g2d);
        }
        
        // Draw bird
        bird.draw(g2d);
        
        // Draw score
        drawScore(g2d);
    }
    
    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 32));
        String score = "Score: " + gameManager.getScore();
        FontMetrics fm = g2d.getFontMetrics();
        int scoreX = (WIDTH - fm.stringWidth(score)) / 2;
        int scoreY = 50;
        g2d.drawString(score, scoreX, scoreY);
    }
    
    private void drawGameOver(Graphics2D g2d) {
        // Semi-transparent overlay
        g2d.setColor(new Color(0, 0, 0, 128));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Game Over text
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 48));
        String gameOver = "GAME OVER";
        FontMetrics fm = g2d.getFontMetrics();
        int gameOverX = (WIDTH - fm.stringWidth(gameOver)) / 2;
        int gameOverY = HEIGHT / 2 - 50;
        g2d.drawString(gameOver, gameOverX, gameOverY);
        
        // Final score
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 32));
        String finalScore = "Final Score: " + gameManager.getScore();
        fm = g2d.getFontMetrics();
        int finalScoreX = (WIDTH - fm.stringWidth(finalScore)) / 2;
        int finalScoreY = HEIGHT / 2;
        g2d.drawString(finalScore, finalScoreX, finalScoreY);
        
        // Restart instruction
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        String restart = "Press SPACE to Restart";
        fm = g2d.getFontMetrics();
        int restartX = (WIDTH - fm.stringWidth(restart)) / 2;
        int restartY = HEIGHT / 2 + 50;
        g2d.drawString(restart, restartX, restartY);
    }
    
    private void updateGame() {
        if (gameState == GameState.PLAYING) {
            // Update bird
            bird.update();
            
            // Update pipes
            for (int i = pipes.size() - 1; i >= 0; i--) {
                Pipe pipe = pipes.get(i);
                pipe.update();
                
                // Remove pipes that are off screen
                if (pipe.getX() + PIPE_WIDTH < 0) {
                    pipes.remove(i);
                }
            }
            
            // Generate new pipes
            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < WIDTH - 300) {
                generatePipe();
            }
            
            // Check collisions
            checkCollisions();
            
            // Update score
            updateScore();
        }
    }
    
    private void generatePipe() {
        int gapY = random.nextInt(HEIGHT - GROUND_HEIGHT - PIPE_GAP - 100) + 50;
        pipes.add(new Pipe(WIDTH, gapY, PIPE_GAP));
    }
    
    private void checkCollisions() {
        // Check if bird hits the ground or ceiling
        if (bird.getY() <= 0 || bird.getY() + BIRD_SIZE >= HEIGHT - GROUND_HEIGHT) {
            gameOver();
            return;
        }
        
        // Check collision with pipes
        Rectangle birdBounds = bird.getBounds();
        for (Pipe pipe : pipes) {
            if (birdBounds.intersects(pipe.getTopBounds()) || 
                birdBounds.intersects(pipe.getBottomBounds())) {
                gameOver();
                return;
            }
        }
    }
    
    private void updateScore() {
        for (Pipe pipe : pipes) {
            if (!pipe.isScored() && pipe.getX() + PIPE_WIDTH < bird.getX()) {
                pipe.setScored(true);
                gameManager.incrementScore();
            }
        }
    }
    
    private void gameOver() {
        gameState = GameState.GAME_OVER;
    }
    
    private void restartGame() {
        bird = new Bird(WIDTH / 4, HEIGHT / 2);
        pipes.clear();
        gameManager.resetScore();
        gameState = GameState.PLAYING;
        generatePipe();
    }
    
    private void startPlaying() {
        gameState = GameState.PLAYING;
        generatePipe();
    }
    
    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGame();
        repaint();
    }
    
    // KeyListener implementation
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameState == GameState.MENU) {
                startPlaying();
            } else if (gameState == GameState.PLAYING) {
                bird.flap();
            } else if (gameState == GameState.GAME_OVER) {
                restartGame();
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    // MouseListener implementation
    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameState == GameState.MENU) {
            startPlaying();
        } else if (gameState == GameState.PLAYING) {
            bird.flap();
        } else if (gameState == GameState.GAME_OVER) {
            restartGame();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) {}
    
    @Override
    public void mouseExited(MouseEvent e) {}
} 