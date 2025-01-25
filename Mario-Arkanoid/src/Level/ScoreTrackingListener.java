package Level;

import ListenersAndNotifier.HitListener;
import Sprites.Ball;
import Sprites.Block;

/**
 * The ScoreTrackingListener class implements the HitListener interface to track hits
 * on blocks and increase the game score accordingly.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     *
     * @param scoreCounter the counter used to track the current game score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Gets the current score counter.
     *
     * @return the current score counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * Increases the current score by a given number.
     *
     * @param num the number of points to increase the score by
     */
    public void setCurrentScore(int num) {
        this.currentScore.increase(num);
    }

    /**
     * Increases the score when a block is hit by adding a fixed amount of 5 points.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
