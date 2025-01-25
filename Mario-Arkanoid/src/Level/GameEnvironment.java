package Level;
import Collidables.Collidable;
import Collidables.CollisionInfo;
import Geometry.Line;

import java.util.List;

/**
 * The Level.GameEnvironment class manages a collection of collidable objects
 * and handles collision detection for objects moving within the game.
 */
public class GameEnvironment {
    private List<Collidable> blocks;

    /**
     * Constructs a Level.GameEnvironment with a given list of collidable objects.
     *
     * @param blocks the list of collidable objects
     */
    public GameEnvironment(List<Collidable> blocks) {
        this.blocks = blocks;
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        blocks.add(c);
    }

    /**
     * Assumes an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, returns null. Else, returns the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the line representing the object's trajectory
     * @return the information about the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closest = null;
        for (Collidable block : blocks) {
            Line[] rectangle = block.getCollisionRectangle().getEdges();
            for (Line edge : rectangle) {
                if (edge.isIntersecting(trajectory)) {
                    closest = new CollisionInfo(block,
                            trajectory.closestIntersectionToStartOfLine(block.getCollisionRectangle()));
                }
            }
        }
        return closest;
    }

    /**
     * Removes the specified collidable object from the collection.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidableFromCollection(Collidable c) {
        blocks.remove(c);
    }

    /**
     * Returns the list of collidable objects in the environment.
     *
     * @return the list of collidable objects
     */
    public List<Collidable> getBlocks() {
        return blocks;
    }
}
