package ListenersAndNotifier;

import Sprites.Ball;
import Sprites.Block;

/**
 * The ListenersAndNotifier.HitListener interface should be implemented by any class that wants to be notified
 * whenever a Sprites.Block object is hit by a Sprites.Ball. Classes implementing this interface must define
 * the hitEvent method, which is called whenever the beingHit object is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the Sprites.Block that is being hit
     * @param hitter   the Sprites.Ball that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
