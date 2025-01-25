package Animations;

import biuoop.DrawSurface;

/**
 * The Animation interface defines the structure for any animation.
 * It includes methods to display a single frame and to check whether the animation should stop.
 */
public interface Animation {

    /**
     * Displays one frame of the animation.
     * This method is responsible for drawing everything needed on the given DrawSurface.
     *
     * @param d the surface to draw the frame on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Indicates whether the animation should stop.
     * This method returns true when the animation is finished and should no longer be displayed.
     *
     * @return true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
