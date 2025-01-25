package Screens;

import Animations.Animation;
import Level.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

/**
 * The GameOver class implements the Animation interface and represents the game over screen
 * that is displayed when the player loses the game. It shows the final score and waits for
 * the player to press the space bar to continue.
 */
public class GameOver implements Animation {
    private KeyboardSensor keyboardSensor;
    private ScoreTrackingListener score;
    private boolean stop = false;

    /**
     * Constructs a GameOver object.
     *
     * @param keyboardSensor the keyboard sensor to detect key presses
     * @param score          the score tracking listener to display the final score
     */
    public GameOver(KeyboardSensor keyboardSensor, ScoreTrackingListener score) {
        this.keyboardSensor = keyboardSensor;
        this.score = score;
    }

    /**
     * Draws the game over screen, showing a red background and displaying the final score.
     * The game stops when the player presses the space bar.
     *
     * @param d the DrawSurface on which the game over screen is drawn
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (keyboardSensor.isPressed(KeyboardSensor.SPACE_KEY)) {
            stop = true;
        }
        d.setColor(Color.red);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.black);
        d.drawText((d.getWidth() / 2) - 250, (d.getHeight() / 2) - 100,
                "Game Over! Your score is: " + score.getCurrentScore().getValue(), 32);
    }

    /**
     * Indicates whether the animation should stop.
     *
     * @return true if the space bar is pressed, false otherwise
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
