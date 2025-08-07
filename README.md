# Flappy Bird Clone

A complete Flappy Bird-style desktop game built entirely in Java using Swing/AWT graphics. All graphics, including the bird, pipes, background, and UI elements, are drawn programmatically using Java's Graphics2D API.

## 🎮 Game Features

### Core Gameplay
- **Bird Physics**: Realistic gravity and flapping mechanics
- **Pipe Obstacles**: Randomly generated pipes with gaps to navigate through
- **Collision Detection**: Precise collision detection with pipes and boundaries
- **Score System**: Points awarded for successfully passing through pipes
- **Game States**: Menu, Playing, and Game Over states

### Visual Design
- **Bird**: Yellow circle with orange beak and black eye
- **Pipes**: Green rectangles with darker borders and caps
- **Background**: Sky blue background with green ground
- **UI**: Clean, readable text overlays for score and instructions

### Controls
- **SPACE**: Start game, flap bird, or restart after game over
- **Mouse Click**: Alternative control for flapping and menu navigation

## 🏗️ Project Structure

```
src/
├── FlappyBird.java      # Main entry point and window setup
├── GamePanel.java       # Main game logic, rendering, and input handling
├── Bird.java           # Bird physics and drawing
├── Pipe.java           # Pipe obstacles and collision detection
├── GameManager.java    # Score tracking and game state management
└── Main.java          # Alternative entry point
```

## 🚀 How to Run

### Prerequisites
- Java 8 or higher
- IntelliJ IDEA (recommended) or any Java IDE

### Running in IntelliJ IDEA
1. Open the project in IntelliJ IDEA
2. Navigate to `src/FlappyBird.java`
3. Right-click and select "Run 'FlappyBird.main()'"

### Running from Command Line
```bash
# Compile the project
javac -cp src src/*.java

# Run the game
java -cp src FlappyBird
```

## 🎯 Game Mechanics

### Bird Physics
- **Gravity**: Constant downward acceleration
- **Flap Strength**: Upward velocity boost on space/click
- **Max Fall Speed**: Limited to prevent excessive falling speed
- **Position**: Bird starts at 1/4 screen width, center height

### Pipe Generation
- **Spacing**: New pipes generated when last pipe is 300px from right edge
- **Gap Position**: Random vertical position with minimum distance from edges
- **Gap Size**: Fixed 150px gap between top and bottom pipes
- **Movement**: Pipes move left at constant speed
- **Cleanup**: Pipes removed when completely off-screen

### Scoring System
- **Point Award**: 1 point for each pipe successfully passed
- **Scoring Logic**: Points awarded when bird passes pipe's right edge
- **High Score**: Automatically tracks and updates high score

### Collision Detection
- **Pipe Collision**: Bird bounds intersect with pipe bounds
- **Boundary Collision**: Bird hits top of screen or ground
- **Game Over**: Immediate transition to game over state

## 🎨 Visual Elements

### Bird Design
- **Body**: Yellow circle with black outline
- **Eye**: White circle with black pupil
- **Beak**: Orange triangle pointing right
- **Size**: 20x20 pixels

### Pipe Design
- **Color**: Forest green with darker green borders
- **Width**: 80 pixels
- **Caps**: Wider rectangles at pipe ends for visual appeal
- **Borders**: 3-pixel stroke for definition

### Background Design
- **Sky**: Light blue (#87CEEB)
- **Ground**: Green rectangle at bottom
- **Ground Border**: Dark green line separating sky and ground

### UI Design
- **Fonts**: Arial for all text elements
- **Colors**: White text on dark overlay for game over
- **Positioning**: Centered text with proper spacing

## 🔧 Technical Implementation

### Game Loop
- **Frame Rate**: 60 FPS using javax.swing.Timer
- **Update Cycle**: Physics, collision detection, score updates
- **Render Cycle**: Background, game objects, UI overlays

### State Management
- **MENU**: Title screen with instructions
- **PLAYING**: Active gameplay with physics and collision
- **GAME_OVER**: Final score display with restart option

### Input Handling
- **KeyListener**: Space bar detection
- **MouseListener**: Click detection for alternative controls
- **Focus Management**: Automatic focus for input capture

## 🎯 Game States

### Menu State
- Displays "FLAPPY BIRD" title
- Shows "Press SPACE to Start" instruction
- Bird visible but stationary
- Space or click starts game

### Playing State
- Bird responds to gravity and flapping
- Pipes move continuously left
- Score updates in real-time
- Collision detection active

### Game Over State
- Semi-transparent overlay
- "GAME OVER" message
- Final score display
- "Press SPACE to Restart" instruction

## 🚫 Restrictions Met

✅ **No External Assets**: All graphics drawn programmatically
✅ **No Images**: Bird, pipes, and background created with Graphics2D
✅ **No Sound Files**: Silent gameplay as requested
✅ **No External Fonts**: Using system default Arial font
✅ **No Third-party Libraries**: Pure Java Standard Library
✅ **No JavaFX**: Using only Swing/AWT

## 🎮 Controls Summary

| Action | Keyboard | Mouse |
|--------|----------|-------|
| Start Game | SPACE | Click |
| Flap Bird | SPACE | Click |
| Restart | SPACE | Click |

## 🏆 Features Implemented

- ✅ Fixed 800x600 resolution window
- ✅ 60 FPS game loop
- ✅ Bird physics with gravity and flapping
- ✅ Pipe generation and movement
- ✅ Collision detection
- ✅ Score system
- ✅ Game over screen
- ✅ Restart functionality
- ✅ Menu screen
- ✅ All graphics drawn programmatically
- ✅ Smooth gameplay
- ✅ No external dependencies

The game is now ready to play! Enjoy the classic Flappy Bird experience with all graphics generated entirely through Java code. 