

public class GameController {
    private static final Logic logic = new Logic();
    private static final Config config = new Config();

    public static void main(String[] args) {
        setUpGame();
    }

    private static void setUpGame() {
        //TODO: set up UI
        //TODO: ask for number of players
        logic.addPlayer(new Player("player", config.START_MONEY, 0));
    }
}
