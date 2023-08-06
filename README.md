# Snake Game Readme
This is a simple Snake Game implemented in Java using Swing and AWT libraries. The game is played in a grid with a snake that moves around and eats food to grow longer. 
The objective is to eat as much food as possible without colliding with the borders or the snake itself.

# How to Play
Use the arrow keys (UP, DOWN, LEFT, RIGHT) to control the direction of the snake.
The snake will move continuously in the specified direction.
The snake will grow longer when it eats food.
The game ends when the snake collides with the borders or itself.

# Game Features
The game window is initially set to full screen with hidden window decorations.
The snake moves automatically, and the game speed increases as you eat more food.
There are 10 levels in the game, and each level requires you to reach a certain score to level up.
Special food appears randomly and gives you a bonus score when eaten.

# Controls
Use the UP arrow key to move the snake upwards.
Use the DOWN arrow key to move the snake downwards.
Use the LEFT arrow key to move the snake to the left.
Use the RIGHT arrow key to move the snake to the right.

# Game Logic
The snake moves one cell at a time in the current direction.
When the snake collides with the food, it grows longer, and the player's score increases.
As the player's score increases, the game speed also increases, making it more challenging.
The game ends when the snake collides with the borders or itself, and a game-over message is displayed.
The game also features special food that gives additional score points when eaten.

# Constants
BOX_SIZE: The size of each cell in pixels.
GRID_WIDTH and GRID_HEIGHT: The size of the game grid (number of cells in each direction).
INITIAL_GAME_SPEED: The initial delay of the game timer in milliseconds.
SPEED_INCREMENT: The amount of speed increment after each food is eaten.
MAX_LEVEL: The maximum level in the game.
SPECIAL_FOOD_SCORE_INCREMENT: The score increment when special food is eaten.

# Class Details
SnakeGame: The main class that extends JFrame and implements KeyListener. It manages the game's logic and GUI components.
generateFood(): Generates random coordinates for the food on the game grid.
move(): Moves the snake one cell in the current direction, updates the score, and generates new food when needed.
checkCollision(): Checks if the snake collides with the borders or itself and triggers game over if so.
checkFood(): Checks if the snake eats regular or special food and updates the score accordingly.
checkLevel(): Checks if the player's score reaches the required amount for leveling up.
gameOver(): Resets the game after a game over and displays the final score.
paint(): Overrides the paint method to draw the game grid, snake, food, score, and level on the screen.
keyPressed(), keyTyped(), keyReleased(): Implement the KeyListener interface to handle user input.

# How to Run
Compile the SnakeGame.java file using a Java compiler.
Run the compiled SnakeGame class with the command: java SnakeGame.
Enjoy playing the Snake Game!
