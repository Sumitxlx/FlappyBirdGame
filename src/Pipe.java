import java.awt.*;

public class Pipe {
    private int x;
    private int gapY;
    private int gapHeight;
    private static final int WIDTH = 80;
    private static final int SPEED = 3;
    private boolean scored;
    
    public Pipe(int x, int gapY, int gapHeight) {
        this.x = x;
        this.gapY = gapY;
        this.gapHeight = gapHeight;
        this.scored = false;
    }
    
    public void update() {
        x -= SPEED;
    }
    
    public void draw(Graphics2D g2d) {
        // Save original color
        Color originalColor = g2d.getColor();
        
        // Draw top pipe
        g2d.setColor(new Color(34, 139, 34)); // Forest green
        g2d.fillRect(x, 0, WIDTH, gapY);
        
        // Draw bottom pipe
        g2d.fillRect(x, gapY + gapHeight, WIDTH, 600 - gapY - gapHeight);
        
        // Draw pipe borders
        g2d.setColor(new Color(0, 100, 0)); // Darker green
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(x, 0, WIDTH, gapY);
        g2d.drawRect(x, gapY + gapHeight, WIDTH, 600 - gapY - gapHeight);
        
        // Draw pipe caps (wider rectangles at the ends)
        int capHeight = 20;
        g2d.fillRect(x - 5, gapY - capHeight, WIDTH + 10, capHeight);
        g2d.fillRect(x - 5, gapY + gapHeight, WIDTH + 10, capHeight);
        
        // Draw cap borders
        g2d.setColor(new Color(0, 100, 0));
        g2d.drawRect(x - 5, gapY - capHeight, WIDTH + 10, capHeight);
        g2d.drawRect(x - 5, gapY + gapHeight, WIDTH + 10, capHeight);
        
        // Restore original color
        g2d.setColor(originalColor);
    }
    
    public int getX() {
        return x;
    }
    
    public Rectangle getTopBounds() {
        return new Rectangle(x, 0, WIDTH, gapY);
    }
    
    public Rectangle getBottomBounds() {
        return new Rectangle(x, gapY + gapHeight, WIDTH, 600 - gapY - gapHeight);
    }
    
    public boolean isScored() {
        return scored;
    }
    
    public void setScored(boolean scored) {
        this.scored = scored;
    }
} 