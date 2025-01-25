package ListenersAndNotifier;

import Level.Counter;
import Screens.GameLevel;
import Sprites.Ball;
import Sprites.Block;

/**
 * A ListenersAndNotifier.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a ListenersAndNotifier.BlockRemover with the specified game and counter for remaining blocks.
     *
     * @param game            the game from which blocks will be removed
     * @param remainingBlocks the counter for the number of remaining blocks
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event by removing the block from the game and updating the counter.
     * Blocks that are hit should be removed from the game. Remember to remove this listener
     * from the block that is being removed from the game.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
        if (beingHit.getSpecialBlock1()) {
        hitter.notifyHit(beingHit);
        }
        if (beingHit.getSpecialBlock2()) {
            double x = beingHit.getCollisionRectangle().getUpperLeft().getX();
            double y = beingHit.getCollisionRectangle().getUpperLeft().getY();

            Sprites.Ball ball = new Sprites.Ball(new Geometry.Point(x + 20, y + 20), 4, beingHit.getColor(),
            this.game.getEnvironment());
            ball.setVelocity(Sprites.Velocity.fromAngleAndSpeed(330, 5));
            ball.addToGame(this.game);
            ball.addHitListener(this.game.getBallRemover());
            this.game.setRemainingBalls(1);
        }
        remainingBlocks.increase(1);
    }
}

