package Level;

import Sprites.Block;
import Sprites.Sprite;
import Sprites.Velocity;

import java.util.List;

/**
 * The LevelInformation interface defines the structure of the information required
 * for each level in the game. It provides methods to get details about the balls,
 * paddle, blocks, and background for a specific level.
 */
public interface LevelInformation {

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns a list of velocities for each ball.
     * The number of velocities in the list is equal to the number of balls.
     *
     * @return a list of ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle for the level.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle for the level.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     * This name will be displayed at the top of the screen during the game.
     *
     * @return the name of the level
     */
    String levelName();

    /**
     * Returns the background sprite for the level.
     * This sprite contains the visual background that will be drawn behind the game elements.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns the list of blocks that make up this level.
     * Each block contains its size, color, and position.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that must be removed to clear the level.
     * This number must be less than or equal to the total number of blocks.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();
}
