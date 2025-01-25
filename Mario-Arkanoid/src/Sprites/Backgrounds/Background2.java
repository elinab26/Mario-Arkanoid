package Sprites.Backgrounds;

import Screens.GameLevel;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * Background2 represents the second level's background in the game.
 * This background features a dark cave with subtle lighting effects
 * and blocks that may represent the cave floor.
 * It extends the Background class and implements the drawing logic for this environment.
 */
public class Background2 extends Background {
    /**
     * Draws the background on the specified DrawSurface.
     *
     * @param d the DrawSurface on which to draw the background
     */
    @Override
    public void drawOn(DrawSurface d) {
        drawBackground(d);
    }

    /**
     * Updates the background state. Currently, there are no updates for this background.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Adds this background to the specified GameLevel.
     *
     * @param g the GameLevel to which this background will be added
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Draws the background color and lighting effects for the cave.
     *
     * @param d the DrawSurface on which to draw the background
     */
    public static void drawBackground(DrawSurface d) {
        // Dark background with subtle lighting effect
        d.setColor(new Color(30, 30, 30));  // Dark cave
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(50, 50, 50)); // Subtle light effect
        d.fillOval(300, 100, 400, 400);  // Light in the cave

        drawBlocks(d); // Draw blocks on the cave floor
    }

    /**
     * Draws the blocks on the cave floor.
     *
     * @param d the DrawSurface on which to draw the blocks
     */
    public static void drawBlocks(DrawSurface d) {
        Color blockColor = new Color(0, 0x7c, 0x8d);
        for (int i = 0; i < 800; i += 40) {
            drawBlock(d, i, 560, blockColor); // Draw first row of blocks
        }
        for (int i = 0; i < 800; i += 40) {
            drawBlock(d, i, 520, blockColor); // Draw second row of blocks
        }
    }

    /**
     * Draws a single block at the specified position.
     *
     * @param d         the DrawSurface on which to draw the block
     * @param x         the horizontal position of the block
     * @param y         the vertical position of the block
     * @param blockColor the color of the block
     */
    public static void drawBlock(DrawSurface d, int x, int y, Color blockColor) {
        d.setColor(blockColor);
        d.fillRectangle(x, y, 40, 40); // Fill block color
        d.setColor(Color.black);
        d.drawRectangle(x, y, 40, 40); // Draw block outline
        drawCracks(d, x, y); // Draw cracks on the block
    }

    /**
     * Draws cracks on a block to give it a worn look.
     *
     * @param d the DrawSurface on which to draw the cracks
     * @param x the horizontal position of the block
     * @param y the vertical position of the block
     */
    public static void drawCracks(DrawSurface d, int x, int y) {
        d.setColor(Color.black);
        d.drawLine(x + 22, y, x + 24, y + 19); // Crack line 1
        d.drawLine(x + 24, y + 19, x + 33, y + 40); // Crack line 2
        d.drawLine(x + 33, y + 40, x + 40, y); // Crack line 3
        d.drawLine(x + 24, y + 19, x + 10, y + 10); // Crack line 4
        d.drawLine(x + 10, y + 10, x, y + 35); // Crack line 5
    }
}
