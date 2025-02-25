package Sprites;

import Level.Counter;
import Level.LevelInformation;
import Screens.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The ScoreIndicator class implements the Sprite interface to display the current game score
 * on the game screen, along with the current level name.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private LevelInformation levelInformation;

    /**
     * Constructs a ScoreIndicator with the given score counter and level information.
     *
     * @param score            the counter holding the current score
     * @param levelInformation the information about the current level
     */
    public ScoreIndicator(Counter score, LevelInformation levelInformation) {
        this.score = score;
        this.levelInformation = levelInformation;
    }

    /**
     * Returns a string representation of the score indicator, including the current score and level name.
     *
     * @return a string showing the current score and level name
     */
    @Override
    public String toString() {
        return "Score: " + score.getValue()
                + "  Level Name: " + levelInformation.levelName();
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the score indicator
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20); // Draw background for the score indicator
        d.setColor(Color.black);
        d.drawRectangle(0, 0, 800, 20); // Draw border for the score indicator
        d.drawText(275, 15, this.toString(), 15); // Draw the score and level name text
    }

    /**
     * Performs no action for the time passed event in this implementation.
     */
    @Override
    public void timePassed() {
        // No action needed for timePassed in this class
    }

    /**
     * Adds the score indicator to the game by registering it as a sprite.
     *
     * @param g the game to which the score indicator will be added
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
