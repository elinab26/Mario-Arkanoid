package ListenersAndNotifier;

import Level.Counter;
import Screens.GameLevel;
import Sprites.Ball;
import Sprites.Block;

/**
 * The ListenersAndNotifier.BallRemover class is responsible for removing balls
 * from the game when they hit a specific block.
 * It implements the ListenersAndNotifier.HitListener interface and responds to hit
 * events by removing the ball from the game
 * and updating the count of removed balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a ListenersAndNotifier.BallRemover with the specified game and counter for removed balls.
     *
     * @param game           the game from which balls will be removed
     * @param remainingBalls the counter for the number of remaining balls
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the hit event by removing the ball from the game and updating the counter.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
        remainingBalls.decrease(1);
    }
}
