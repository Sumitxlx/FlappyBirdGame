import java.awt.*;

public class Bird {
    private double x, y;
    private double velocityY;
    private static final int SIZE = 20;
    private static final double GRAVITY = 0.5;
    private static final double MAX_FALL_SPEED = 10;
    private static final double FLAP_STRENGTH = -8;
    
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocityY = 0;
    }
    
    public void update() {
        // Apply gravity
        velocityY += GRAVITY;
        
        // Limit fall speed
        if (velocityY > MAX_FALL_SPEED) {
            velocityY = MAX_FALL_SPEED;
        }
        
        // Update position
        y += velocityY;
    }
    
    public void flap() {
        velocityY = FLAP_STRENGTH;
    }
    
    public void draw(Graphics2D g2d) {
        // Save original color
        Color originalColor = g2d.getColor();
        
        // Draw bird body (yellow circle)
        g2d.setColor(new Color(255, 255, 0));
        g2d.fillOval((int)x, (int)y, SIZE, SIZE);
        
        // Draw bird outline
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval((int)x, (int)y, SIZE, SIZE);
        
        // Draw bird eye
        g2d.setColor(Color.WHITE);
        g2d.fillOval((int)x + SIZE - 8, (int)y + 3, 6, 6);
        g2d.setColor(Color.BLACK);
        g2d.fillOval((int)x + SIZE - 7, (int)y + 4, 4, 4);
        
        // Draw bird beak (small triangle)
        int[] beakX = {(int)x + SIZE, (int)x + SIZE + 5, (int)x + SIZE};
        int[] beakY = {(int)y + SIZE/2 - 2, (int)y + SIZE/2, (int)y + SIZE/2 + 2};
        g2d.setColor(new Color(255, 165, 0)); // Orange beak
        g2d.fillPolygon(beakX, beakY, 3);
        g2d.setColor(Color.BLACK);
        g2d.drawPolygon(beakX, beakY, 3);
        
        // Restore original color
        g2d.setColor(originalColor);
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, SIZE, SIZE);
    }
} 