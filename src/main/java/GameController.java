import java.util.Objects;

public class GameController {
    private static final Logic logic = new Logic();
    private static final Config config = new Config();
    private static MainWindowController mainWindowController;


    public static void main(String[] args) {
        setUpGame();
    }

    private static void setUpGame() {
        MainWindowView view = new MainWindowView();
        mainWindowController = view.getController();
        //mainWindowController.setGameController(this); Doesnt work!
        view.startUI();
    }

    void addPlayers(){
        PlayerEntryWindow entry = null;

        // Clean up board, empty out all the fields,
        mainWindowController.initializeGame();
        mainWindowController.setBoardVisibility(false);
        entry = new PlayerEntryWindow(); // TODO: This is where new user entries come from!

        try {
            mainWindowController.setBoardVisibility(true);

            for (int i = 0; i < Objects.requireNonNull(entry).getPlayersList().size(); i++) {

                // Add players to UI
                mainWindowController.setPlayerName(i + 1, entry.getPlayersList().get(i));
                mainWindowController.setPlayerMoney(i + 1, Config.START_MONEY);
                mainWindowController.setPlayerCredits(i + 1, Config.START_CREDITS);
                mainWindowController.movePlayer(entry.getPlayersList().get(i), 1);

                // Add players to Logic
                logic.addPlayer(new Player(entry.getPlayersList().get(i), Config.START_MONEY, Config.START_CREDITS, i+1));
            }
            mainWindowController.updateCurrentPlayer(entry.getPlayersList().get(0));
            mainWindowController.setNewGameConfirmationNeeded(false);
        } catch (Exception e) {
            mainWindowController.setBoardVisibility(false);
            mainWindowController.setNewGameConfirmationNeeded(true);
        }
    }
}
