package ch.zhaw.pm2.caffeineaddicts.infopoly.controller;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.parseInt;

/**
 * A series of windows for player entry. Asks how many players will be playing, and what the names are. Checks if all
 * entries are valid (two characters long, names not taken yet) and throws error messages if they aren't.
 * Both Logic and Controller can get their player name and player number info via getPlayersList().
 *
 * @author corrooli
 */
public class PlayerEntryWindow {


    private boolean entrySuccess = true;

    /**
     * Main array for registered player initials.
     */
    private final ArrayList<String> playersList = new ArrayList<>();

    /**
     * Constructor for PlayerEntryWindow. This is where all the magic happens.
     * <p>
     * 1. Creates a prompt where the user can select between 2-4 players.
     * 2. For each number of players there will be a name entry window to input a two-character initial.
     * <p>
     * If any of the information is invalid, an InformationalWindow is spawned to correct the user.
     */
    public PlayerEntryWindow() {
        AtomicInteger numberOfPlayers = new AtomicInteger();
        List<String> numberOfPlayersArray = new ArrayList<>(Arrays.asList("2", "3", "4"));

        // Spawn new choice dialog
        ChoiceDialog<String> numberOfPlayersDialog = new ChoiceDialog<>("2", numberOfPlayersArray);
        numberOfPlayersDialog.setTitle("Question");
        numberOfPlayersDialog.setHeaderText("How many players?");

        // Show dialog and wait for input
        Optional<String> numberOfPlayersDialogResult = numberOfPlayersDialog.showAndWait();

        // Parse chosen integer to numberOfPlayers field
        numberOfPlayersDialogResult.ifPresent(s -> numberOfPlayers.set(parseInt(s)));

        // Loop through number of players
        for (int i = 1; i <= numberOfPlayers.get(); i++) {

            // Break out of loop if an entry was cancelled
            if (!entrySuccess) {
                break;
            }

            // Loop condition
            boolean falseOrIncompleteInput = true;

            // Begin loop. If the user provides invalid input, the loop begins anew.
            while (falseOrIncompleteInput) {

                // Spawn new input dialog
                TextInputDialog nameEntryDialog = new TextInputDialog();
                nameEntryDialog.setTitle("Name entry");
                nameEntryDialog.setHeaderText("Player " + i + ", please enter your initials");

                // Show dialog and wait for input
                Optional<String> nameEntryDialogResult = nameEntryDialog.showAndWait();

                // Temporary player name string
                String playerName;

                if (nameEntryDialogResult.isPresent()) {

                    // Convert to upper case and trim
                    playerName = nameEntryDialogResult.get().toUpperCase().trim();

                    // Check if name is already taken
                    if (playersList.contains(playerName)) {
                        new InformationalWindow("Name already taken!");
                    }

                    // Check if name is just two characters long and contains no numbers + special characters
                    else if (playerName.length() == 2 && playerName.matches("[a-zA-Z]*")) {

                        // Success: Adding player to ArrayList
                        this.playersList.add(playerName);

                        // Exit loop
                        falseOrIncompleteInput = false;

                    } else {

                        // Error message & retry
                        new InformationalWindow("Make sure your name is just two alphabetical characters!");
                    }
                } else {
                    // Exit for loop
                    entrySuccess = false;
                    break;
                }
            }
        }
    }

    public ArrayList<String> getPlayersList() {
        return playersList;
    }

    public boolean isEntrySuccess() {
        return entrySuccess;
    }
}
