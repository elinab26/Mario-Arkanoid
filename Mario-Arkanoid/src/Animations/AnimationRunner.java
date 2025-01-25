package Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner class is responsible for running animations.
 * It controls the game loop, rendering frames on the GUI at a fixed frame rate,
 * and ensuring smooth animation by managing timing and sleeping between frames.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor that creates a new AnimationRunner.
     * Initializes the GUI and sets the frame rate to 59 frames per second.
     *
     * @param gui the GUI object used to display the animation
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 59;
        sleeper = new Sleeper();
    }

    /**
     * Runs the given animation.
     * This method continuously calls the animation's doOneFrame method to display each frame,
     * and controls the timing to ensure a consistent frame rate.
     *
     * @param animation the animation to run
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            // timing
            int millisecondsPerFrame = 1000 / framesPerSecond;
            long startTime = System.currentTimeMillis(); // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
