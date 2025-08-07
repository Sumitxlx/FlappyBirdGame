public class GameManager {
    private int score;
    private int highScore;
    
    public GameManager() {
        this.score = 0;
        this.highScore = 0;
    }
    
    public void incrementScore() {
        score++;
        if (score > highScore) {
            highScore = score;
        }
    }
    
    public void resetScore() {
        score = 0;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getHighScore() {
        return highScore;
    }
    
    public void setScore(int score) {
        this.score = score;
        if (score > highScore) {
            highScore = score;
        }
    }
} 