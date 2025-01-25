package Level;

import Screens.GameLevel;

import java.util.List;

/**
 * The GameFlow class is responsible for running all the levels of the game in sequence.
 * It manages the transition between levels and tracks the player's score throughout the game.
 */
public class GameFlow {
    private ScoreTrackingListener score;
    private static int counter;

    /**
     * Constructor that creates a new GameFlow object.
     * It initializes the score tracking for the game.
     *
     * @param score the ScoreTrackingListener used to track the player's score
     */
    public GameFlow(ScoreTrackingListener score) {
        this.score = score;
    }

    /**
     * Runs all the levels in the game.
     * This method iterates through the list of levels, initializing and running each one
     * until it is completed, and then moving on to the next level.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        counter = levels.size();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, score);
            level.initialize();
            while (!level.shouldStop()) {
                level.run();
            }
        }
    }

    /**
     * Gets the number of levels in the game.
     * This method returns the total number of levels that were added to the game.
     *
     * @return the number of levels
     */
    public static int getCounter() {
        return counter;
    }
}
