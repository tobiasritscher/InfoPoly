package ch.zhaw.pm2.caffeineaddicts.infopoly.controller;

import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Config;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.GameBoard;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Logic;
import ch.zhaw.pm2.caffeineaddicts.infopoly.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Main Window UI Controller. Contains all fields to address the main window, and all methods used to update fields.
 * <p>
 * Displays players on field, can move players, can indicate if a player takes over a field, calls dice rolls and
 * starts/stops a game as well as spawns a dialog to enter player names / number.
 * <p>
 * This controller is deliberately kept (kinda) dumb, and it does not handle any business logic. This ensures a correct
 * MVP pattern. All information on the UI is updated via PropertyListeners (from JavaFx Beans)
 *
 * @author corrooli
 */
public class MainWindowController {

    /**
     * All fields in the game. Can be formatted to our liking.
     */
    @FXML
    private BorderPane field01; // START
    @FXML
    private BorderPane field02; // CLASS : Programming
    @FXML
    private BorderPane field03; // CLASS : Discrete Mathematics
    @FXML
    private BorderPane field04; // CLASS : Information Theory & Coding
    @FXML
    private BorderPane field05; // SEMESTER TUITION
    @FXML
    private BorderPane field06; // JOB   : BarStucks
    @FXML
    private BorderPane field07; // CLASS : Software Project
    @FXML
    private BorderPane field08; // CHANCE
    @FXML
    private BorderPane field09; // CLASS : Electronics & digital technology
    @FXML
    private BorderPane field10; // CLASS : Analysis
    @FXML
    private BorderPane field11; // REPEATING SEMESTER
    @FXML
    private BorderPane field12; // CLASS : Databases
    @FXML
    private BorderPane field13; // CLASS : Communication Competence
    @FXML
    private BorderPane field14; // CLASS : Linear Algebra
    @FXML
    private BorderPane field15; // CLASS : Communication Technologies
    @FXML
    private BorderPane field16; // JOB   : McBurger
    @FXML
    private BorderPane field17; // CHANCE
    @FXML
    private BorderPane field18; // CLASS : System Orientated Programming
    @FXML
    private BorderPane field19; // CLASS : Web Development
    @FXML
    private BorderPane field20; // ZVV FEES
    @FXML
    private BorderPane field22; // CLASS : Algorithms & Data Structures
    @FXML
    private BorderPane field23; // CLASS : Stochastics & Statistics
    @FXML
    private BorderPane field24; // CHANCE
    @FXML
    private BorderPane field25; // CLASS : Computer Engineering
    @FXML
    private BorderPane field26; // JOB   : AnalogTec
    @FXML
    private BorderPane field27; // CLASS : IT Security
    @FXML
    private BorderPane field28; // CLASS : Higher Mathematics
    @FXML
    private BorderPane field29; // CLASS : Physics Engines
    @FXML
    private BorderPane field30; // CLASS : Business Administration
    @FXML
    private BorderPane field31; // EXAM FAILED
    @FXML
    private BorderPane field32; // CLASS : Project Thesis
    @FXML
    private BorderPane field33; // CLASS : Machine Learning
    @FXML
    private BorderPane field34; // CLASS : Cloud Computing
    @FXML
    private BorderPane field35; // CLASS : Game Development
    @FXML
    private BorderPane field36; // JOB   : CashBank
    @FXML
    private BorderPane field37; // CHANCE
    @FXML
    private BorderPane field38; // CLASS : Mobile Applications
    @FXML
    private BorderPane field39; // PARTY
    @FXML
    private BorderPane field40; // CLASS : Bachelor
    /**
     * Bottom of fields (player area). This is where player initials are used to indicate which players are on which
     * field.
     */
    @FXML
    private Label field01Players; // START
    @FXML
    private Label field02Players; // CLASS : Programming
    @FXML
    private Label field03Players; // CLASS : Discrete Mathematics
    @FXML
    private Label field04Players; // CLASS : Information Theory & Coding
    @FXML
    private Label field05Players; // SEMESTER TUITION
    @FXML
    private Label field06Players; // JOB   : BarStucks
    @FXML
    private Label field07Players; // CLASS : Software Project
    @FXML
    private Label field08Players; // CHANCE
    @FXML
    private Label field09Players; // CLASS : Electronics & digital technology
    @FXML
    private Label field10Players; // CLASS : Analysis
    @FXML
    private Label field11Players; // REPEATING SEMESTER
    @FXML
    private Label field12Players; // CLASS : Databases
    @FXML
    private Label field13Players; // CLASS : Communication Competence
    @FXML
    private Label field14Players; // CLASS : Linear Algebra
    @FXML
    private Label field15Players; // CLASS : Communication Technologies
    @FXML
    private Label field16Players; // JOB   : McBurger
    @FXML
    private Label field17Players; // CHANCE
    @FXML
    private Label field18Players; // CLASS : System Orientated Programming
    @FXML
    private Label field19Players; // CLASS : Web Development
    @FXML
    private Label field20Players; // ZVV FEES
    @FXML
    private Label field21Players; // STARTUP
    @FXML
    private Label field22Players; // CLASS : Algorithms & Data Structures
    @FXML
    private Label field23Players; // CLASS : Stochastics & Statistics
    @FXML
    private Label field24Players; // CHANCE
    @FXML
    private Label field25Players; // CLASS : Computer Engineering
    @FXML
    private Label field26Players; // JOB   : AnalogTec
    @FXML
    private Label field27Players; // CLASS : IT Security
    @FXML
    private Label field28Players; // CLASS : Higher Mathematics
    @FXML
    private Label field29Players; // CLASS : Physics Engines
    @FXML
    private Label field30Players; // CLASS : Business Administration
    @FXML
    private Label field31Players; // EXAM FAILED
    @FXML
    private Label field32Players; // CLASS : Project Thesis
    @FXML
    private Label field33Players; // CLASS : Machine Learning
    @FXML
    private Label field34Players; // CLASS : Cloud Computing
    @FXML
    private Label field35Players; // CLASS : Game Development
    @FXML
    private Label field36Players; // JOB   : CashBank
    @FXML
    private Label field37Players; // CHANCE
    @FXML
    private Label field38Players; // CLASS : Mobile Applications
    @FXML
    private Label field39Players; // PARTY
    @FXML
    private Label field40Players; // CLASS : Bachelor Thesis
    /**
     * Content of 'Repeating Semester/Repeating:' text label. Indicates which player has to repeat currently.
     */
    @FXML
    private Label repeatingPlayers;
    /**
     * Top / Middle title fields. The background colors of these fields will switch to the player
     * color using Listeners in case he/she decides to take a class/job/do a startup
     */
    @FXML
    private BorderPane field02Color; // CLASS : Programming
    @FXML
    private BorderPane field03Color; // CLASS : Discrete Mathematics
    @FXML
    private BorderPane field04Color; // CLASS : Information Theory & Coding
    @FXML
    private BorderPane field06Color; // JOB   : BarStucks
    @FXML
    private BorderPane field07Color; // CLASS : Software Project
    @FXML
    private BorderPane field09Color; // CLASS : Electronics & digital technology
    @FXML
    private BorderPane field10Color; // CLASS : Analysis
    @FXML
    private BorderPane field12Color; // CLASS : Databases
    @FXML
    private BorderPane field13Color; // CLASS : Communication Competence
    @FXML
    private BorderPane field14Color; // CLASS : Linear Algebra
    @FXML
    private BorderPane field15Color; // CLASS : Communication Technologies
    @FXML
    private BorderPane field16Color; // JOB   : McBurger
    @FXML
    private BorderPane field18Color; // CLASS : System Orientated Programming
    @FXML
    private BorderPane field19Color; // CLASS : Web Development
    @FXML
    private BorderPane field21Color; // STARTUP
    @FXML
    private BorderPane field22Color; // CLASS : Algorithms & Data Structures
    @FXML
    private BorderPane field23Color; // CLASS : Stochastics & Statistics
    @FXML
    private BorderPane field25Color; // CLASS : Computer Engineering
    @FXML
    private BorderPane field26Color; // JOB   : AnalogTec
    @FXML
    private BorderPane field27Color; // CLASS : IT Security
    @FXML
    private BorderPane field28Color; // CLASS : Higher Mathematics
    @FXML
    private BorderPane field29Color; // CLASS : Physics Engines
    @FXML
    private BorderPane field30Color; // CLASS : Business Administration
    @FXML
    private BorderPane field32Color; // CLASS : Project Thesis
    @FXML
    private BorderPane field33Color; // CLASS : Machine Learning
    @FXML
    private BorderPane field34Color; // CLASS : Cloud Computing
    @FXML
    private BorderPane field35Color; // CLASS : Game Development
    @FXML
    private BorderPane field36Color; // JOB   : CashBank
    @FXML
    private BorderPane field38Color; // CLASS : Mobile Applications
    @FXML
    private BorderPane field40Color; // CLASS : Bachelor Thesis
    @FXML
    private BorderPane dummyPane;    // Dummy Pane to make ArrayList easier to handle
    /**
     * Content of fundsBox. This box indicates labels indicating their info (player names, their color, their credits
     * and money.) as well as line separators.
     */
    @FXML
    private VBox fundsBoxLabels;
    @FXML
    private Label fundsBoxPlayer1Name;
    @FXML
    private Label fundsBoxPlayer1Credits;
    @FXML
    private Label fundsBoxPlayer1Money;
    @FXML
    private Label fundsBoxPlayer2Name;
    @FXML
    private Label fundsBoxPlayer2Credits;
    @FXML
    private Label fundsBoxPlayer2Money;
    @FXML
    private Label fundsBoxPlayer3Name;
    @FXML
    private Label fundsBoxPlayer3Credits;
    @FXML
    private Label fundsBoxPlayer3Money;
    @FXML
    private Label fundsBoxPlayer4Name;
    @FXML
    private Label fundsBoxPlayer4Credits;
    @FXML
    private Label fundsBoxPlayer4Money;
    @FXML
    private Circle player1Ball;
    @FXML
    private Circle player2Ball;
    @FXML
    private Circle player3Ball;
    @FXML
    private Circle player4Ball;
    @FXML
    private Line seperator0;
    @FXML
    private Line seperator1;
    @FXML
    private Line seperator2;
    @FXML
    private Line seperator3;
    /**
     * Content of Game controls (current player, rollDiceButton, rollDiceOutput)
     */
    @FXML
    private Label currentPlayer;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label rollDiceOutput;
    @FXML
    private Label rollDiceLabel;
    @FXML
    private Button rollDiceButton;
    /**
     * Game logic instance
     */
    private Logic logic;
    /**
     * Boolean indicating if no game has started yet. This will eliminate the "Are you sure?" dialog when starting a
     * new game.
     */
    private Boolean newGameConfirmationNeeded = true;
    /**
     * Boolean indicating if no game has started yet. Will be needed to switch text of the Button.
     */
    private Boolean gameWasStarted = false;
    /**
     * ArrayList for all fields on the board.
     */
    private ArrayList<BorderPane> fields;

    /**
     * ArrayList for all player position Labels (fieldXXPlayers) on the board.
     */
    private ArrayList<Label> fieldLabels;

    /**
     * ArrayList for all changeable BorderPanes (fieldXXColor) on the board.
     */
    private ArrayList<BorderPane> fieldColors;

    /**
     * Empty Constructor of UIController Class. Needs to be empty (FXML convention)
     * initialize() will automatically be called after.
     */
    public MainWindowController() {
    }

    /**
     * Create Arraylist for field labels to make it easier to iterate through fields.
     * Create Arraylist for field colors to make it easier to iterate through fields. Non-addressable fields are
     * replaced with an invisible dummyPane object so the order of this ArrayList harmonizes with the fields on
     * the board.
     */
    @FXML
    private void initialize() {
        fieldLabels = new ArrayList<>(Arrays.asList(
                field01Players, field02Players, field03Players, field04Players, field05Players, field06Players,
                field07Players, field08Players, field09Players, field10Players, field11Players, field12Players,
                field13Players, field14Players, field15Players, field16Players, field17Players, field18Players,
                field19Players, field20Players, field21Players, field22Players, field23Players, field24Players,
                field25Players, field26Players, field27Players, field28Players, field29Players, field30Players,
                field31Players, field32Players, field33Players, field34Players, field35Players, field36Players,
                field37Players, field38Players, field39Players, field40Players, repeatingPlayers
        ));

        fieldColors = new ArrayList<>(Arrays.asList(
                dummyPane, field02Color, field03Color, field04Color, dummyPane, field06Color, field07Color, dummyPane,
                field09Color, field10Color, dummyPane, field12Color, field13Color, field14Color, field15Color,
                field16Color, dummyPane, field18Color, field19Color, dummyPane, field21Color, field22Color,
                field23Color, dummyPane, field25Color, field26Color, field27Color, field28Color, field29Color,
                field30Color, dummyPane, field32Color, field33Color, field34Color, field35Color, field36Color,
                dummyPane, field38Color, dummyPane, field40Color, dummyPane
        ));

        fields = new ArrayList<>(Arrays.asList(
                field01, field02, field03, field04, field05, field06, field07, field08, field09, field10, field11,
                field12, field13, field14, field15, field16, field17, field18, field19, field20, field21Color, field22,
                field23, field24, field25, field26, field27, field28, field29, field30, field31, field32, field33,
                field34, field35, field36, field37, field38, field39, field40
        ));
        initializeGame();
    }

    /**
     * Initializes / resets all fieldLabels, player names and funds values with an empty String, as well as making the
     * 'Roll Dice' button, colored balls and separator lines invisible. Needs to be a separate method since the player
     * can start a new game while the application is running.
     */
    private void initializeGame() {
        fieldLabels.forEach((fieldLabel) -> fieldLabel.setText(""));
        fieldColors.forEach((fieldColor) -> fieldColor.setStyle("-fx-background-color: " + Config.PlayerColor.UNOCCUPIED.getColorValue()));
        setBoardVisibility(false);

        rollDiceOutput.setText("");

        setPlayerName(1, "");
        setPlayerName(2, "");
        setPlayerName(3, "");
        setPlayerName(4, "");

        fundsBoxPlayer1Money.setText("");
        fundsBoxPlayer2Money.setText("");
        fundsBoxPlayer3Money.setText("");
        fundsBoxPlayer4Money.setText("");

        fundsBoxPlayer1Credits.setText("");
        fundsBoxPlayer2Credits.setText("");
        fundsBoxPlayer3Credits.setText("");
        fundsBoxPlayer4Credits.setText("");

        player1Ball.setOpacity(0.0);
        player2Ball.setOpacity(0.0);
        player3Ball.setOpacity(0.0);
        player4Ball.setOpacity(0.0);

        seperator0.setOpacity(0.0);
        seperator1.setOpacity(0.0);
        seperator2.setOpacity(0.0);
        seperator3.setOpacity(0.0);
    }

    /**
     * Moves a player to a new field. Checks which field the player is on right now, deletes its name and moves it
     * to another field. Field numbers must be given explicitly.
     *
     * @param playerName Name of player (call with playerInstance.getName())
     * @param fieldId    The id of the field to which player will be moved (See documentation)
     */
    private void movePlayer(String playerName, int fieldId) {
        String tempText;

        // Check if Player is in one of the fields. Loop through entire board (maybe could be made easier but i don't want to save the whole board state here
        for (Label fieldLabel : fieldLabels) {

            // If Player is on one field, proceed
            if (fieldLabel.getText().contains(playerName)) {

                // Extract text from field to temporary String
                tempText = fieldLabel.getText();

                // Delete Player name
                tempText = tempText.replace(playerName, "");

                // Trim leading and trailing whitespace
                tempText = tempText.trim();

                // Set new text to field
                fieldLabel.setText(tempText);
            }
        }

        // Extract text from field and concatenate it with whitespace and Player name
        tempText = fieldLabels.get(fieldId).getText() + " " + playerName;

        // Trim leading and trailing whitespaces
        tempText = tempText.trim();

        // Set new text to field
        fieldLabels.get(fieldId).setText(tempText);
    }

    /**
     * Take over field and color the BorderPane color to the player's colo
     *
     * @param playerNumber The Player's number. (See documentation)
     * @param fieldNumber  The number of the field that was taken over. (See documentation)
     */
    private void takeOverField(int playerNumber, int fieldNumber) {
        Config.PlayerColor color = Config.PlayerColor.UNOCCUPIED;
        switch (playerNumber) {
            case 0:
                color = Config.PlayerColor.UNOCCUPIED;
                break;
            case 1:
                color = Config.PlayerColor.PLAYER1;
                break;
            case 2:
                color = Config.PlayerColor.PLAYER2;
                break;
            case 3:
                color = Config.PlayerColor.PLAYER3;
                break;
            case 4:
                color = Config.PlayerColor.PLAYER4;
                break;
            default:
                new InformationalWindow("Color", "Illegal Color!");
                break;
        }
        fieldColors.get(fieldNumber).setStyle("-fx-background-color: " + color.getColorValue());
    }

    /**
     * Set player names and makes the colored balls and separator lines visible.
     *
     * @param playerNumber Current player number (1-4).
     * @param playerName   Name of Player (uppercase initials).
     */
    private void setPlayerName(int playerNumber, String playerName) {
        switch (playerNumber) {
            case 1:
                fundsBoxPlayer1Name.setText(playerName);
                player1Ball.setOpacity(1.0);
                seperator0.setOpacity(1.0);
                break;
            case 2:
                fundsBoxPlayer2Name.setText(playerName);
                player2Ball.setOpacity(1.0);
                seperator1.setOpacity(1.0);
                break;
            case 3:
                fundsBoxPlayer3Name.setText(playerName);
                player3Ball.setOpacity(1.0);
                seperator2.setOpacity(1.0);
                break;
            case 4:
                fundsBoxPlayer4Name.setText(playerName);
                player4Ball.setOpacity(1.0);
                seperator3.setOpacity(1.0);
                break;
            default:
                new InformationalWindow("", "Player number out of range!");
        }
    }

    /**
     * Sets the player's credits on the GUI.
     *
     * @param playerNumber Current player number (1-4).
     * @param credits      New amount of credits currently obtained.
     */
    private void setPlayerCredits(int playerNumber, int credits) {


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
                new InformationalWindow("", "Player number out of range!");
        }
    }

    /**
     * Sets the player's money on the GUI.
     *
     * @param playerNumber Current player number (1-4).
     * @param money        New amount of money currently obtained.
     */
    private void setPlayerMoney(int playerNumber, int money) {
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
                new InformationalWindow("", "Player number out of range!");
        }
    }

    /**
     * Updates the current player label on the GUI to indicate whose turn it is.
     *
     * @param playerName Current player name.
     */
    private void updateCurrentPlayer(String playerName) {
        currentPlayer.setText(playerName);
    }

    /**
     * Tells the model to roll the dice via button action.
     */
    public void rollDiceAction() {
        if (gameWasStarted) {
            logic.rollDice();
        } else {
            newGameAction();
            gameWasStarted = true;
        }
    }

    /**
     * Tells the model to start a new game.
     */
    public void newGameAction() {
        if (newGameConfirmationNeeded) {
            addPlayers();
        } else {
            QuestionWindow newGameQuestion = new QuestionWindow(
                    "Are you sure?", "You really want to start a new game?"
            );
            if (newGameQuestion.getAnswer()) {
                addPlayers();
            }
        }

        // Listener for current player
        logic.getPlayerTurnProperty().addListener((observableValue, oldValue, newValue) ->
                updateCurrentPlayer(logic.getPlayers().get(newValue.intValue()).getName()));

        // Listener for dice
        logic.getCurrentDiceRollProperty().addListener((observableValue, oldValue, newValue) ->
                rollDiceOutput.setText(newValue.toString()));
    }

    /**
     * Helper method for adding players. Spawns a new PlayerEntryWindow and calls Logic to add new Player objects.
     */
    private void addPlayers() {
        // Resetting logic and game board
        logic = new Logic();

        // Preparing player entry windows
        PlayerEntryWindow entry;

        // Clean up board, empty out all the fields
        initializeGame();
        setBoardVisibility(false);

        // Spawn player entry windows
        entry = new PlayerEntryWindow();

        try {
            if (!entry.isEntrySuccess()) {
                gameWasStarted = false;
            } else {

                for (int i = 0; i < Objects.requireNonNull(entry).getPlayersList().size(); i++) {
                    // Add players to UI
                    setPlayerName(i + 1, entry.getPlayersList().get(i));
                    setPlayerMoney(i + 1, Config.START_MONEY);
                    setPlayerCredits(i + 1, Config.START_CREDITS);

                    // Instantiating players, add them to Logic
                    logic.addPlayer(new Player(
                            entry.getPlayersList().get(i),
                            Config.START_MONEY,
                            Config.START_CREDITS,
                            i + 1));

                    // Add listeners to money and credits.
                    int playerNumber = i;
                    logic.getPlayers().get(i).getMoneyProperty().addListener((observableValue, oldValue, newValue) ->
                            setPlayerMoney(playerNumber + 1, (Integer) newValue));
                    logic.getPlayers().get(i).getCreditsProperty().addListener((observableValue, oldValue, newValue) ->
                            setPlayerCredits(playerNumber + 1, (Integer) newValue));
                    logic.getPlayers().get(i).getPositionProperty().addListener((observableValue, oldValue, newValue) ->
                            movePlayer(logic.getPlayers().get(playerNumber).getName(), (Integer) newValue));

                    movePlayer(entry.getPlayersList().get(i), Config.PLAYER_START_POSITION);
                    newGameConfirmationNeeded = false;
                    gameWasStarted = true;
                    setBoardVisibility(true);
                }
            }

            newGameConfirmationNeeded = false;

            // Add listeners to game fields
            GameBoard gameboard = logic.getGameBoard();
            gameboard.getBoard().forEach((field) -> field.getOwnerProperty().addListener(
                    (observableValue, oldValue, newValue) ->
                            takeOverField(newValue.intValue(), field.getFieldId()))
            );

        } catch (Exception e) {
            setBoardVisibility(false);
            newGameConfirmationNeeded = true;
            gameWasStarted = false;
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
     * Makes the board visible or invisible.
     *
     * @param visibility true for visible, false for invisible
     */
    private void setBoardVisibility(boolean visibility) {
        if (visibility) {
            rollDiceButton.setText("Roll Dice");
        } else {
            rollDiceButton.setText("Start Game");
        }
        fundsBoxLabels.setVisible(visibility);
        currentPlayerLabel.setVisible(visibility);
        currentPlayer.setVisible(visibility);
        rollDiceLabel.setVisible(visibility);
        rollDiceOutput.setText("");
        fields.forEach((field) -> field.setVisible(visibility));
    }
}
