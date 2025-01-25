package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.SpriteCollection class represents a collection of sprites.
 * It manages adding sprites, updating their state, and drawing them.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a Sprites.SpriteCollection with the given list of sprites.
     *
     * @param sprites the list of sprites to initialize the collection
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notifies all sprites in the collection that time has passed.
     * This method calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList = new ArrayList<>(this.sprites);

        for (Sprite sprite : spriteList) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * This method calls drawOn(d) on all sprites.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Removes a sprite from the collection.
     *
     * @param s the sprite to remove
     */
    public void removeSpriteFromCollection(Sprite s) {
        sprites.remove(s);
    }
}
