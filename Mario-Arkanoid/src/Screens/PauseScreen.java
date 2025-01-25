package Screens;

import Animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * The PauseScreen class implements the Animation interface to create a pause screen
 * that displays a message when the game is paused. It allows the player to resume
 * the game by pressing the space key.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructs a new PauseScreen object with the specified keyboard sensor.
     *
     * @param k the keyboard sensor used to detect key presses
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Draws the pause screen on the given DrawSurface.
     * It displays a message indicating that the game is paused and instructs
     * the player to press the space key to continue.
     *
     * @param d the DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.CYAN);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText((d.getWidth() / 2) - 155, (d.getHeight() / 2) - 100, "You paused the game", 32);
        d.drawText((d.getWidth() / 2) - 170, (d.getHeight() / 2) - 68, "Press space to continue", 32);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * Indicates whether the pause screen should stop being displayed.
     *
     * @return true if the space key was pressed, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
