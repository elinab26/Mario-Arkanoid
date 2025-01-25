package Screens;

import Geometry.Point;
import Level.LevelInformation;
import Sprites.Backgrounds.Background2;
import Sprites.Block;
import Sprites.Sprite;
import Sprites.Velocity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Level2 class implements the LevelInformation interface and defines the
 * characteristics of the second level of the game. This includes the number of
 * balls, their initial velocities, paddle properties, background, blocks,
 * and the conditions for clearing the level.
 */
public class Level2 implements LevelInformation {

    /**
     * Returns the number of balls to be used in this level.
     *
     * @return the number of balls (2 for Level 2)
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * Returns a list of initial velocities for the balls in this level.
     *
     * @return a list of Velocity objects representing the initial velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(312, 7));
        velocities.add(Velocity.fromAngleAndSpeed(300, 7));
        return velocities;
    }

    /**
     * Returns the speed of the paddle in this level.
     *
     * @return the paddle speed (5 for Level 2)
     */
    @Override
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Returns the width of the paddle in this level.
     *
     * @return the paddle width (150 for Level 2)
     */
    @Override
    public int paddleWidth() {
        return 150;
    }

    /**
     * Returns the name of this level.
     *
     * @return the level name ("Level 2")
     */
    @Override
    public String levelName() {
        return "Level 2";
    }

    /**
     * Returns the background sprite for this level.
     *
     * @return a Sprite representing the background
     */
    @Override
    public Sprite getBackground() {
        return new Background2();
    }

    /**
     * Returns a list of blocks that make up this level. This includes regular blocks
     * as well as special blocks that may have unique behaviors.
     *
     * @return a list of Block objects representing the blocks in this level
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Random random = new Random();
        boolean flagRemove = true;
        boolean flagAdd = true;
        Color[] colors = {Color.red, Color.yellow,
                Color.green, new Color(0, 0x80, 0x80), Color.MAGENTA, new Color(0x87, 0x09, 0x72)};
        for (int j = 6; j > 0; j--) {
            for (int i = 7; i >= j; i--) {
                if (flagRemove && random.nextInt(20) + 1 == 2 && j != 6) {
                    Sprites.Block special = new Sprites.Block(new Geometry.Point(320 + i * 40, 130 + j * 40), 40, 40,
                            colors[j - 1]);
                    blocks.add(special);
                    special.doSpecialBlock1();
                    flagRemove = false;
                    i--;
                }
                if (flagAdd && random.nextInt(20) + 1 == 2 && j != 6) {
                    Sprites.Block special = new Sprites.Block(new Geometry.Point(320 + i * 40, 130 + j * 40), 40, 40,
                            colors[j - 1]);
                    blocks.add(special);
                    special.doSpecialBlock2();
                    flagAdd = false;
                    i--;
                }

                Block block = new Block(new Point(320 + i * 40, 130 + j * 40), 40, 40, colors[j - 1]);
                blocks.add(block);
            }
        }
        return blocks;
    }

    /**
     * Returns the number of blocks that must be removed for the level to be considered cleared.
     *
     * @return the number of blocks to remove (equal to the total number of blocks)
     */
    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
