package Screens;

import Animations.Animation;
import Level.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * The Win class implements the Animation interface to create a win screen
 * that displays a message when the player wins the game. It shows the player's
 * score and allows the player to exit the win screen by pressing the space key.
 */
public class Win implements Animation {
    private KeyboardSensor keyboardSensor;
    private ScoreTrackingListener score;
    private boolean stop;

    /**
     * Constructs a new Win object with the specified keyboard sensor and score tracking listener.
     *
     * @param keyboardSensor the keyboard sensor used to detect key presses
     * @param score          the score tracking listener used to retrieve the current score
     */
    public Win(KeyboardSensor keyboardSensor, ScoreTrackingListener score) {
        this.keyboardSensor = keyboardSensor;
        this.score = score;
        stop = false;
    }

    /**
     * Draws the win screen on the given DrawSurface.
     * It displays a message indicating that the player has won and shows their score.
     * The screen can be exited by pressing the space key.
     *
     * @param d the DrawSurface to draw on
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.green);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.drawText((d.getWidth() / 2) - 250, (d.getHeight() / 2) - 100,
                "You Win! Your score is: " + score.getCurrentScore().getValue(), 32);
        if (keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            stop = true;
        }
    }

    /**
     * Indicates whether the win screen should stop being displayed.
     *
     * @return true if the space key was pressed, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
