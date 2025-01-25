import Level.Counter;
import Level.GameFlow;
import Level.LevelInformation;
import Level.ScoreTrackingListener;
import Screens.Level1;
import Screens.Level2;

import java.util.ArrayList;
import java.util.List;

/**
 * The ArkanoidGame class contains the main method to start and run the game.
 * It initializes and runs the game by creating a Screens.Game object and calling
 * its initialize and run methods.
 */
public class ArkanoidGame {

    /**
     * The main method is the entry point of the game. It creates a new Screens.Game object,
     * initializes the game, and starts the game loop.
     * The game consists of multiple levels that are stored in a list. Each level is represented
     * by an object that implements the LevelInformation interface. The levels are passed to the
     * GameFlow object, which manages the game's progression and the player's score.
     * The method does the following:
     * - Creates a new ScoreTrackingListener to track the player's score.
     * - Creates a GameFlow object that handles the game logic.
     * - Creates two levels (Level1 and Level2) and adds them to a list.
     * - Calls the runLevels method of the GameFlow object to start the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ScoreTrackingListener score = new ScoreTrackingListener(new Counter(0));
        GameFlow gameFlow = new GameFlow(score);
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(level1);
        levels.add(level2);
        gameFlow.runLevels(levels);
    }
}
