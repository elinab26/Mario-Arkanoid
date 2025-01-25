package Sprites;
import Screens.GameLevel;
import biuoop.DrawSurface;

/**
 * This interface represents a sprite object that can be drawn on a DrawSurface.
 * It provides methods for drawing the sprite and updating its state.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, so it can update its state.
     */
    void timePassed();

    /**
     * Adds this sprite to the specified game.
     *
     * @param g the game to which this sprite will be added
     */
    void addToGame(GameLevel g);
}
