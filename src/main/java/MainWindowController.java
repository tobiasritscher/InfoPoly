import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main Window UI Controller. Contains all fields to address the main window, and all methods used to update fields.
 * This controller is deliberately kept dumb, and it does not handle any business logic. This ensures a correct MVP
 * pattern.
 *
 * @author corrooli
 */
public class MainWindowController {

    /**
     * Game menu commands.
     */
    @FXML
    public MenuItem newGame;
    @FXML
    public MenuItem quit;

    /**
     * Window menu commands.
     */
    @FXML
    public MenuItem resize;
    @FXML
    public MenuItem saveWindowConfiguration;
    @FXML
    public MenuItem loadWindowConfiguration;

    /**
     * Bottom fields where player initials are used to indicate which players are on which field.
     */
    @FXML
    public Label field01Players; // START
    @FXML
    public Label field02Players; // CLASS : Programming
    @FXML
    public Label field03Players; // CLASS : Discrete Mathematics
    @FXML
    public Label field04Players; // CLASS : Information Theory & Coding
    @FXML
    public Label field05Players; // SEMESTER TUITION
    @FXML
    public Label field06Players; // JOB   : BarStucks
    @FXML
    public Label field07Players; // CLASS : Software Project
    @FXML
    public Label field08Players; // CHANCE
    @FXML
    public Label field09Players; // CLASS : Electronics & digital technology
    @FXML
    public Label field10Players; // CLASS : Analysis
    @FXML
    public Label field11Players; // REPEATING SEMESTER
    @FXML
    public Label field12Players; // CLASS : Databases
    @FXML
    public Label field13Players; // CLASS : Communication Competence
    @FXML
    public Label field14Players; // CLASS : Linear Algebra
    @FXML
    public Label field15Players; // CLASS : Communication Technologies
    @FXML
    public Label field16Players; // JOB   : McBurger
    @FXML
    public Label field17Players; // CHANCE
    @FXML
    public Label field18Players; // CLASS : System Orientated Programming
    @FXML
    public Label field19Players; // CLASS : Web Development
    @FXML
    public Label field20Players; // ZVV FEES
    @FXML
    public Label field21Players; // STARTUP
    @FXML
    public Label field22Players; // CLASS : Algorithms & Data Structures
    @FXML
    public Label field23Players; // CLASS : Stochastics & Statistics
    @FXML
    public Label field24Players; // CHANCE
    @FXML
    public Label field25Players; // CLASS : Computer Engineering
    @FXML
    public Label field26Players; // JOB   : AnalogTec
    @FXML
    public Label field27Players; // CLASS : IT Security
    @FXML
    public Label field28Players; // CLASS : Higher Mathematics
    @FXML
    public Label field29Players; // CLASS : Physics Engines
    @FXML
    public Label field30Players; // CLASS : Business Administration
    @FXML
    public Label field31Players; // EXAM FAILED
    @FXML
    public Label field32Players; // CLASS : Project Thesis
    @FXML
    public Label field33Players; // CLASS : Machine Learning
    @FXML
    public Label field34Players; // CLASS : Cloud Computing
    @FXML
    public Label field35Players; // CLASS : Game Development
    @FXML
    public Label field36Players; // JOB   : CashBank
    @FXML
    public Label field37Players; // CHANCE
    @FXML
    public Label field38Players; // CLASS : Mobile Applications
    @FXML
    public Label field39Players; // PARTY
    @FXML
    public Label field40Players; // CLASS : Bachelor Thesis

    /**
     * Top / Middle title fields. The background colors of these fields will switch to the player
     * color in case he/she decides to take a class/job/do a startup
     */
    @FXML
    public BorderPane field02Color; // CLASS : Programming
    @FXML
    public BorderPane field03Color; // CLASS : Discrete Mathematics
    @FXML
    public BorderPane field04Color; // CLASS : Information Theory & Coding
    @FXML
    public BorderPane field06Color; // JOB   : BarStucks
    @FXML
    public BorderPane field07Color; // CLASS : Software Project
    @FXML
    public BorderPane field09Color; // CLASS : Electronics & digital technology
    @FXML
    public BorderPane field10Color; // CLASS : Analysis
    @FXML
    public BorderPane field12Color; // CLASS : Databases
    @FXML
    public BorderPane field13Color; // CLASS : Communication Competence
    @FXML
    public BorderPane field14Color; // CLASS : Linear Algebra
    @FXML
    public BorderPane field15Color; // CLASS : Communication Technologies
    @FXML
    public BorderPane field16Color; // JOB   : McBurger
    @FXML
    public BorderPane field18Color; // CLASS : System Orientated Programming
    @FXML
    public BorderPane field19Color; // CLASS : Web Development
    @FXML
    public BorderPane field21Color; // STARTUP
    @FXML
    public BorderPane field22Color; // CLASS : Algorithms & Data Structures
    @FXML
    public BorderPane field23Color; // CLASS : Stochastics & Statistics
    @FXML
    public BorderPane field25Color; // CLASS : Computer Engineering
    @FXML
    public BorderPane field26Color; // JOB   : AnalogTec
    @FXML
    public BorderPane field27Color; // CLASS : IT Security
    @FXML
    public BorderPane field28Color; // CLASS : Higher Mathematics
    @FXML
    public BorderPane field29Color; // CLASS : Physics Engines
    @FXML
    public BorderPane field30Color; // CLASS : Business Administration
    @FXML
    public BorderPane field32Color; // CLASS : Project Thesis
    @FXML
    public BorderPane field33Color; // CLASS : Machine Learning
    @FXML
    public BorderPane field34Color; // CLASS : Cloud Computing
    @FXML
    public BorderPane field35Color; // CLASS : Game Development
    @FXML
    public BorderPane field36Color; // JOB   : CashBank
    @FXML
    public BorderPane field38Color; // CLASS : Mobile Applications
    @FXML
    public BorderPane field40Color; // CLASS : Bachelor Thesis
    @FXML
    public BorderPane dummyPane;    // Dummy Pane to make ArrayList easier to handle

    /**
     * Content of fundsBox. This box indicates player names, their color, their credits and money.
     */
    @FXML
    public Label fundsBoxPlayer1Name;
    @FXML
    public Label fundsBoxPlayer1Credits;
    @FXML
    public Label fundsBoxPlayer1Money;
    @FXML
    public Label fundsBoxPlayer2Name;
    @FXML
    public Label fundsBoxPlayer2Credits;
    @FXML
    public Label fundsBoxPlayer2Money;
    @FXML
    public Label fundsBoxPlayer3Name;
    @FXML
    public Label fundsBoxPlayer3Credits;
    @FXML
    public Label fundsBoxPlayer3Money;
    @FXML
    public Label fundsBoxPlayer4Name;
    @FXML
    public Label fundsBoxPlayer4Credits;
    @FXML
    public Label fundsBoxPlayer4Money;

    /**
     * Content of Game controls (current player, rollDiceButton, rollDiceOutput)
     */
    @FXML
    public Label currentPlayer;
    @FXML
    public Label rollDiceOutput;
    @FXML
    public Button rollDiceButton;

    /**
     * Content of 'Repeating Semester/Repeating:' text label. Indicates which player has to repeat currently.
     */
    @FXML
    public Label repeatingPlayers;

    /**
     * ArrayList for all player position Labels (fieldXXPlayers) on the board.
     */
    ArrayList<Label> fieldLabels;

    /**
     * ArrayList for all changeable BorderPanes (fieldXXColor) on the board.
     */
    ArrayList<BorderPane> fieldPanes;

    /**
     * Empty Constructor of UIController Class. Needs to be empty!
     */
    public MainWindowController() {
    }

    @FXML
    private void initialize() {
        /**
         * Create Arraylist for field labels to make it easier to iterate through fields.
         */
        // TODO: Should be replaced with something more compact. Maybe some methods from Reflection?
        fieldLabels = new ArrayList<>(Arrays.asList(
                field01Players, field02Players, field03Players, field04Players, field05Players, field06Players,
                field07Players, field08Players, field09Players, field10Players, field11Players, field12Players,
                field13Players, field14Players, field15Players, field16Players, field17Players, field18Players,
                field19Players, field20Players, field21Players, field22Players, field23Players, field24Players,
                field25Players, field26Players, field27Players, field28Players, field29Players, field30Players,
                field31Players, field32Players, field33Players, field34Players, field35Players, field36Players,
                field37Players, field38Players, field39Players, field40Players
        ));

        /**
         * Create Arraylist for field colors to make it easier to iterate through fields. Non-addressable fields are
         * replaced with an invisible dummyPane object so the order of this ArrayList harmonizes with the fields on
         * the board.
         */
        fieldPanes = new ArrayList<>(Arrays.asList(
                dummyPane, field02Color, field03Color, field04Color, dummyPane, field06Color, field07Color, field09Color, field10Color,
                dummyPane, field12Color, field13Color, field14Color, field15Color, field16Color, dummyPane, field18Color, field19Color,
                field21Color, dummyPane, field22Color, field23Color, dummyPane, field25Color, field26Color, field27Color, field28Color,
                field29Color, field30Color, dummyPane, field32Color, field33Color, field34Color, field35Color, field36Color,
                dummyPane, field38Color, dummyPane, field40Color
        ));

        /**
         * Initialize all fieldLabels with an empty String
         */
        fieldLabels.forEach((fieldLabel) -> fieldLabel.setText(""));


        // Test some stuff with dirty dirty no-good tests TODO clean up dirty tests

        Player player1 = new Player("BU", 0, 0);
        Player player2 = new Player("TT", 0, 0);
        Player player3 = new Player("FA", 0, 0);
        Player player4 = new Player("CE", 0, 0);

        setPlayerName(1, player1.getName());
        setPlayerName(2, player2.getName());
        setPlayerName(3, player3.getName());
        setPlayerName(4, player4.getName());

        movePlayer(player1.getName(), 6);
        movePlayer(player2.getName(), 5);
        movePlayer(player3.getName(), 4);
        movePlayer(player4.getName(), 3);

        takeOverField(PlayerColor.PLAYER1, 2);
        takeOverField(PlayerColor.PLAYER2, 3);
        takeOverField(PlayerColor.PLAYER3, 4);
        takeOverField(PlayerColor.PLAYER4, 6);

        setPlayerCredits(1, 666);
        setPlayerCredits(2, 420);
        setPlayerCredits(3, 69);
        setPlayerCredits(4, 1);

        setPlayerMoney(1, 666);
        setPlayerMoney(2, 420);
        setPlayerMoney(3, 69);
        setPlayerMoney(4, 1);
    }

    /**
     * Moves a player to a new field. Checks which field the player is on right now, deletes its name and moves it
     * to another field. Field numbers must be given explicitly.
     *
     * @param playerName  Current player number (1-4).
     * @param fieldNumber The number of the field that the player moves to. (See documentation)
     */
    public void movePlayer(String playerName, int fieldNumber) {

        // TODO: Should work, but we first need to decide how much 'intelligence' this class needs to have

        // Iterate through all fields and delete current player location
        String tempText;

        // Check if Player is in one of the fields. Loop through entire board (maybe could be made easier but i don't want to save the whole board state here
        for (Label fieldLabel : fieldLabels) {

            // If Player is on one field, proceed
            if (fieldLabel.getText().contains(playerName)) {

                // Extract text from field to temporary String
                tempText = fieldLabel.getText();

                // Delete Player name
                tempText = tempText.replace(playerName, "");

                // Trim leading and trailing whitepace
                tempText = tempText.trim();

                // Set new text to field
                fieldLabel.setText(tempText);
            }
        }

        // Move player to new field after checking if he isn't already on this field
        if (fieldLabels.get(fieldNumber - 1).getText().contains(playerName)) {

            // Error msg if Player is already on this field (not really necessary but helps debugging
            System.out.println("Player already on this field!");
        } else {

            // Extract text from field and concatenate it with whitespace and Player name

            tempText = fieldLabels.get(fieldNumber - 1).getText() + " " + playerName;

            // Trim leading and trailing whitespaces
            tempText = tempText.trim();

            // Set new text to field
            fieldLabels.get(fieldNumber - 1).setText(tempText);
        }
    }

    /**
     * Take over field and color the BorderPane color to the player's colo
     *
     * @param playerColor The Player's color enum value. (See documentation)
     * @param fieldNumber The number of the field that was taken over. (See documentation)
     */
    public void takeOverField(PlayerColor playerColor, int fieldNumber) {
        fieldPanes.get(fieldNumber - 1).setStyle("-fx-background-color: " + playerColor.getColorValue());
    }

    /**
     * Set player names.
     *
     * @param playerNumber Current player number (1-4).
     * @param playerName   Name of Player (uppercase initials).
     */
    public void setPlayerName(int playerNumber, String playerName) {
        switch (playerNumber) {
            case 1:
                fundsBoxPlayer1Name.setText(playerName);
                break;
            case 2:
                fundsBoxPlayer2Name.setText(playerName);
                break;
            case 3:
                fundsBoxPlayer3Name.setText(playerName);
                break;
            case 4:
                fundsBoxPlayer4Name.setText(playerName);
                break;
            default:
                System.out.println("playerNumber out of range!");
        }
    }

    /**
     * Sets the player's credits on the GUI.
     *
     * @param playerNumber Current player number (1-4).
     * @param credits      New amount of credits currently obtained.
     */
    public void setPlayerCredits(int playerNumber, int credits) {
        switch (playerNumber) {
            case 1:
                fundsBoxPlayer1Credits.setText(String.valueOf(credits));
                break;
            case 2:
                fundsBoxPlayer2Credits.setText(String.valueOf(credits));
                break;
            case 3:
                fundsBoxPlayer3Credits.setText(String.valueOf(credits));
                break;
            case 4:
                fundsBoxPlayer4Credits.setText(String.valueOf(credits));
                break;
            default:
                System.out.println("credits out of range!");
        }
    }

    /**
     * Sets the player's money on the GUI.
     *
     * @param playerNumber Current player number (1-4).
     * @param money        New amount of money currently obtained.
     */
    public void setPlayerMoney(int playerNumber, int money) {
        switch (playerNumber) {
            case 1:
                fundsBoxPlayer1Money.setText(String.valueOf(money));
                break;
            case 2:
                fundsBoxPlayer2Money.setText(String.valueOf(money));
                break;
            case 3:
                fundsBoxPlayer3Money.setText(String.valueOf(money));
                break;
            case 4:
                fundsBoxPlayer4Money.setText(String.valueOf(money));
                break;
            default:
                System.out.println("money out of range!");
        }
    }

    /**
     * Updates the label displaying the current value of dice roll on the GUI.
     *
     * @param rolledNumber rolled number, retrieved from model.
     */
    public void updateDiceRollLabel(int rolledNumber) {

        rollDiceOutput.setText(String.valueOf(rolledNumber));
    }

    /**
     * Updates the current player label on the GUI to indicate whose turn it is.
     *
     * @param playerNumber Current player number (1-4).
     */
    public void updateCurrentPlayer(int playerNumber) {
        currentPlayer.setText(String.valueOf(playerNumber));
    }

    /**
     * Tells the model to start a new game.
     */
    public void newGameAction() {
        QuestionWindow newGameQuestion = new QuestionWindow(
                "Are you sure?", "You really want to start a new game?"
        );
        if (newGameQuestion.getAnswer()) {
            // TODO: tell logic to start a new game
        }
    }

    /**
     * Quits the entire application.
     */
    public void quitAction() {
        QuestionWindow quitQuestion = new QuestionWindow(
                "Are you sure?", "You really want to quit?"
        );
        if (quitQuestion.getAnswer()) {
            Platform.exit();
        }
    }

    /**
     * Opens a new window, prompting the user for a new display resolution.
     * TODO: This seems kinda redundant since it's easy to resize the window with the mouse. I'd kill this feature
     */
    public void resizeAction() {
        // TODO: Implement window asking for resize
    }

    /**
     * Tells the model to roll the dice via button action.
     */
    public void rollDiceAction() {
        // TODO: Tell logic to roll dice
        new InformationalWindow("The dice has been rolled ._.");

        // quick and dirty test: updateDiceRollLabel(4);
    }

    /**
     * Saves the current window configuration to a text file.
     */
    public void saveWindowConfigurationAction() {
        // TODO: implement
    }

    /**
     * Loads the current window configuration from a text file.
     */
    public void loadWindowConfigurationAction() {
        // TODO: implement
    }
}
