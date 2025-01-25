package Animations;

import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

/**
 * The CountdownAnimation class displays a countdown before a game starts.
 * It displays the countdown on top of the game screen for a specific number of seconds.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * Constructor that creates a new CountdownAnimation.
     * Initializes the countdown with the number of seconds and starting number,
     * and takes the game's current screen to display behind the countdown.
     *
     * @param numOfSeconds the total time for the countdown
     * @param countFrom    the number to count down from
     * @param gameScreen   the game's screen to display behind the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.numOfSeconds = numOfSeconds;
        this.gameScreen = gameScreen;
        stop = false;
        sleeper = new Sleeper();
    }

    /**
     * Displays one frame of the countdown animation.
     * It draws the game screen and the countdown number. When the countdown reaches zero,
     * it displays "GO!" before stopping.
     *
     * @param d the surface to draw the frame on
     */
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        if (countFrom == -1) {
            sleeper.sleepFor((long) numOfSeconds * 500);
            stop = true;
            return;
        }
        d.setColor(Color.black);
        if (countFrom != 0) {
            d.drawText(400, 80, String.valueOf(countFrom), 32);
        } else {
            d.drawText(380, 80, "GO!", 32);
        }
        countFrom--;
        if (countFrom < 2) {
            sleeper.sleepFor((long) numOfSeconds * 1000);
        }
    }

    /**
     * Determines whether the countdown animation should stop.
     * It stops when the countdown has finished.
     *
     * @return true if the countdown has finished, false otherwise
     */
    public boolean shouldStop() {
        return stop;
    }
}
