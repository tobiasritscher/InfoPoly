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
 * MVP pattern. All information on the UI is updated via PropertyListeners
 *
 * @author corrooli
 */
public class MainWindowController {

    //All fields in the game. Can be formatted to our liking.
    @FXML
    private BorderPane field01;
    @FXML
    private BorderPane field02;
    @FXML
    private BorderPane field03;
    @FXML
    private BorderPane field04;
    @FXML
    private BorderPane field05;
    @FXML
    private BorderPane field06;
    @FXML
    private BorderPane field07;
    @FXML
    private BorderPane field08;
    @FXML
    private BorderPane field09;
    @FXML
    private BorderPane field10;
    @FXML
    private BorderPane field11;
    @FXML
    private BorderPane field12;
    @FXML
    private BorderPane field13;
    @FXML
    private BorderPane field14;
    @FXML
    private BorderPane field15;
    @FXML
    private BorderPane field16;
    @FXML
    private BorderPane field17;
    @FXML
    private BorderPane field18;
    @FXML
    private BorderPane field19;
    @FXML
    private BorderPane field20;
    @FXML
    private BorderPane field22;
    @FXML
    private BorderPane field23;
    @FXML
    private BorderPane field24;
    @FXML
    private BorderPane field25;
    @FXML
    private BorderPane field26;
    @FXML
    private BorderPane field27;
    @FXML
    private BorderPane field28;
    @FXML
    private BorderPane field29;
    @FXML
    private BorderPane field30;
    @FXML
    private BorderPane field31;
    @FXML
    private BorderPane field32;
    @FXML
    private BorderPane field33;
    @FXML
    private BorderPane field34;
    @FXML
    private BorderPane field35;
    @FXML
    private BorderPane field36;
    @FXML
    private BorderPane field37;
    @FXML
    private BorderPane field38;
    @FXML
    private BorderPane field39;
    @FXML
    private BorderPane field40;

    /*
    * Bottom of fields (player area). This is where player initials are used to indicate which players are on which
    * field.
    */
    @FXML
    private Label field01Players;
    @FXML
    private Label field02Players;
    @FXML
    private Label field03Players;
    @FXML
    private Label field04Players;
    @FXML
    private Label field05Players;
    @FXML
    private Label field06Players;
    @FXML
    private Label field07Players;
    @FXML
    private Label field08Players;
    @FXML
    private Label field09Players;
    @FXML
    private Label field10Players;
    @FXML
    private Label field11Players;
    @FXML
    private Label field12Players;
    @FXML
    private Label field13Players;
    @FXML
    private Label field14Players;
    @FXML
    private Label field15Players;
    @FXML
    private Label field16Players;
    @FXML
    private Label field17Players;
    @FXML
    private Label field18Players;
    @FXML
    private Label field19Players;
    @FXML
    private Label field20Players;
    @FXML
    private Label field21Players;
    @FXML
    private Label field22Players;
    @FXML
    private Label field23Players;
    @FXML
    private Label field24Players;
    @FXML
    private Label field25Players;
    @FXML
    private Label field26Players;
    @FXML
    private Label field27Players;
    @FXML
    private Label field28Players;
    @FXML
    private Label field29Players;
    @FXML
    private Label field30Players;
    @FXML
    private Label field31Players;
    @FXML
    private Label field32Players;
    @FXML
    private Label field33Players;
    @FXML
    private Label field34Players;
    @FXML
    private Label field35Players;
    @FXML
    private Label field36Players;
    @FXML
    private Label field37Players;
    @FXML
    private Label field38Players;
    @FXML
    private Label field39Players;
    @FXML
    private Label field40Players;

    //Content of 'Repeating Semester/Repeating:' text label. Indicates which player has to repeat currently.
    @FXML
    private Label repeatingPlayers;

    /*
     * Top / Middle title fields. The background colors of these fields will switch to the player
     * color using Listeners in case he/she decides to take a class/job/do a startup
     */
    @FXML
    private BorderPane field02Color;
    @FXML
    private BorderPane field03Color;
    @FXML
    private BorderPane field04Color;
    @FXML
    private BorderPane field06Color;
    @FXML
    private BorderPane field07Color;
    @FXML
    private BorderPane field09Color;
    @FXML
    private BorderPane field10Color;
    @FXML
    private BorderPane field12Color;
    @FXML
    private BorderPane field13Color;
    @FXML
    private BorderPane field14Color;
    @FXML
    private BorderPane field15Color;
    @FXML
    private BorderPane field16Color;
    @FXML
    private BorderPane field18Color;
    @FXML
    private BorderPane field19Color;
    @FXML
    private BorderPane field21Color;
    @FXML
    private BorderPane field22Color;
    @FXML
    private BorderPane field23Color;
    @FXML
    private BorderPane field25Color;
    @FXML
    private BorderPane field26Color;
    @FXML
    private BorderPane field27Color;
    @FXML
    private BorderPane field28Color;
    @FXML
    private BorderPane field29Color;
    @FXML
    private BorderPane field30Color;
    @FXML
    private BorderPane field32Color;
    @FXML
    private BorderPane field33Color;
    @FXML
    private BorderPane field34Color;
    @FXML
    private BorderPane field35Color;
    @FXML
    private BorderPane field36Color;
    @FXML
    private BorderPane field38Color;
    @FXML
    private BorderPane field40Color;
    @FXML
    private BorderPane dummyPane;

    /*
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
    private Line separator0;
    @FXML
    private Line separator1;
    @FXML
    private Line separator2;
    @FXML
    private Line separator3;

    //Content of Game controls (current player, rollDiceButton, rollDiceOutput)
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

    private Logic logic;

    /*
     * Boolean indicating if no game has started yet. This will eliminate the "Are you sure?" dialog when starting a
     * new game.
     */
    private Boolean newGameConfirmationNeeded = true;

    //Boolean indicating if no game has started yet. Will be needed to switch text of the Button.
    private Boolean gameWasStarted = false;

    private ArrayList<BorderPane> fields;
    private ArrayList<Label> fieldLabels;
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
        fieldColors.forEach((fieldColor) -> fieldColor.setStyle("-fx-background-color: " +
                Config.PlayerColor.UNOCCUPIED.getColorValue()));
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

        player1Ball.setFill(Config.PlayerColor.PLAYER1.getColor());
        player2Ball.setFill(Config.PlayerColor.PLAYER2.getColor());
        player3Ball.setFill(Config.PlayerColor.PLAYER3.getColor());
        player4Ball.setFill(Config.PlayerColor.PLAYER4.getColor());

        separator0.setOpacity(0.0);
        separator1.setOpacity(0.0);
        separator2.setOpacity(0.0);
        separator3.setOpacity(0.0);
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

        //Check if Player is in one of the fields. Loop through entire board
        for (Label fieldLabel : fieldLabels)
            if (fieldLabel.getText().contains(playerName)) {

                //Extract text from field to temporary String, delete player name, clean up String and update label
                tempText = fieldLabel.getText().replace(playerName, "").trim();
                fieldLabel.setText(tempText);
            }

        //Extract text from field and concatenate it with whitespace and Player name
        tempText = fieldLabels.get(fieldId).getText() + " " + playerName;
        tempText = tempText.trim();
        fieldLabels.get(fieldId).setText(tempText);
    }

    /**
     * Take over field and color the BorderPane color to the player's color
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
     * Display player names and makes the colored balls and separator lines visible.
     *
     * @param playerNumber Current player number (1-4).
     * @param playerName   Name of Player (uppercase initials).
     */
    private void setPlayerName(int playerNumber, String playerName) {
        switch (playerNumber) {
            case 1:
                fundsBoxPlayer1Name.setText(playerName);
                player1Ball.setOpacity(1.0);
                separator0.setOpacity(1.0);
                break;
            case 2:
                fundsBoxPlayer2Name.setText(playerName);
                player2Ball.setOpacity(1.0);
                separator1.setOpacity(1.0);
                break;
            case 3:
                fundsBoxPlayer3Name.setText(playerName);
                player3Ball.setOpacity(1.0);
                separator2.setOpacity(1.0);
                break;
            case 4:
                fundsBoxPlayer4Name.setText(playerName);
                player4Ball.setOpacity(1.0);
                separator3.setOpacity(1.0);
                break;
            default:
                new InformationalWindow("", "Player number out of range!");
        }
    }

    /**
     * Updates the player's credits on the GUI.
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
     * Updates the player's money on the GUI.
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
     * Tells the model to roll the dice via button action or starts the game when the game hasn't started yet.
     */
    public void rollDiceAction() {
        if (gameWasStarted) {
            logic.rollDice();
        } else {
            newGameAction();
        }
    }

    /**
     * Tells the model to start a new game.
     */
    public void newGameAction() {
        if (newGameConfirmationNeeded) {
            addPlayers();
            updateCurrentPlayer(logic.getCurrentPlayer().getName());
        } else {
            QuestionWindow newGameQuestion = new QuestionWindow(
                    "Are you sure?", "You really want to start a new game?"
            );
            if (newGameQuestion.getAnswer()) {
                addPlayers();
            }
        }
    }

    /**
     * Helper method for adding players. Spawns a new PlayerEntryWindow and calls Logic to add new Player objects.
     */
    private void addPlayers() {
        //Clean up board, empty out all the fields
        endGame();

        //Preparing player entry windows
        PlayerEntryWindow entry = new PlayerEntryWindow();

        try {
            if (entry.isEntrySuccess()) {
                for (int i = 0; i < Objects.requireNonNull(entry).getPlayersList().size(); i++) {
                    //Add players to UI
                    setPlayerName(i + 1, entry.getPlayersList().get(i));
                    setPlayerMoney(i + 1, Config.START_MONEY);
                    setPlayerCredits(i + 1, Config.START_CREDITS);

                    //Instantiating players, add them to Logic
                    logic.addPlayer(new Player(
                            entry.getPlayersList().get(i),
                            Config.START_MONEY,
                            Config.START_CREDITS,
                            i + 1));

                    //Add listeners to money and credits.
                    int playerId = i;
                    Player player = logic.getPlayer(i);
                    player.getMoneyProperty().addListener((observableValue, oldMoney, newMoney) -> {
                        new InformationalWindow("Email from bank", String.format(
                                "%-11s%S%n%-9sAccount state%n%n%-14s%d.- CHF%n%-15s%d.- CHF%n%-16s%d.- CHF",
                                "To:", player.getName(), "Subject:", "Money before:", oldMoney.intValue(),
                                "Money now:", newMoney.intValue(),
                                "Money gained:", newMoney.intValue() - oldMoney.intValue()));
                        setPlayerMoney(playerId + 1, newMoney.intValue());
                    });
                    player.getCreditsProperty().addListener((observableValue, oldCredits, newCredits) -> {
                        new InformationalWindow("Email from ZHAW", String.format(
                                "%-11s%S%n%-9sCredits state%n%n%-16s%d  credits%n%-16s%d credits%n%-17s%d credits",
                                "To:", player.getName(), "Subject:", "Credits before:", oldCredits.intValue(),
                                "Credits now:", newCredits.intValue(),
                                "Credits gained:", newCredits.intValue() - oldCredits.intValue()));
                        setPlayerCredits(playerId + 1, newCredits.intValue());
                    });
                    player.getPositionProperty().addListener((observableValue, oldPosition, newPosition) -> {
                                final int repetitionGameFieldId = logic.getGameBoard().getRepetitionGameFieldId();
                                final int examGameFieldId = logic.getGameBoard().getExamGameFieldId();
                                if ((oldPosition.intValue() == examGameFieldId) &&
                                        (newPosition.intValue() == repetitionGameFieldId)) {
                                    movePlayer(player.getName(), fieldLabels.indexOf(repeatingPlayers));
                                } else {
                                    movePlayer(player.getName(), newPosition.intValue());
                                }
                                new InformationalWindow("GPS", String.format("%S moved to field: %S",
                                        player.getName(),
                                        logic.getGameBoard().getField(newPosition.intValue()).getFieldName()));
                            }
                    );

                    movePlayer(entry.getPlayersList().get(i), Config.PLAYER_START_POSITION);
                }

                //Add occupancy listeners to game fields
                GameBoard gameboard = logic.getGameBoard();
                gameboard.getBoard().forEach((field) -> field.getOwnerProperty().addListener(
                        (observableValue, oldValue, newValue) ->
                                takeOverField(newValue.intValue(), field.getFieldId()))
                );

                //Listener for current player
                logic.getPlayerTurnProperty().addListener((observableValue, oldValue, newValue) ->
                        updateCurrentPlayer(logic.getPlayers().get(newValue.intValue()).getName()));

                //Listener for dice
                logic.getCurrentDiceRollProperty().addListener((observableValue, oldValue, newValue) ->
                        rollDiceOutput.setText(newValue.toString()));

                // Listener in case game was won
                logic.getGameWasWonProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue) endGame();
                });

                gameWasStarted = true;
                setBoardVisibility(true);

            } else {
                endGame();
            }

        } catch (Exception e) {
            endGame();
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

    /**
     * Helper method for ending the game. Makes board invisible and resets Logic to prevent bugs when restarting the
     * Game.
     */
    private void endGame() {
        setBoardVisibility(false);
        initializeGame();
        gameWasStarted = false;
        newGameConfirmationNeeded = false;
        logic = new Logic(); // Re-initialize Logic. Prevents bugs when restarting the game.
    }
}