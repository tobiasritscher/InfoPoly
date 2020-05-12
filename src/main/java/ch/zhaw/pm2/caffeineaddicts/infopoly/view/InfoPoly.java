package ch.zhaw.pm2.caffeineaddicts.infopoly.view;

/**
 *  /$$$$$$            /$$$$$$          /$$$$$$$           /$$
 * |_  $$_/           /$$__  $$        | $$__  $$         | $$
 *   | $$   /$$$$$$$ | $$  \__//$$$$$$ | $$  \ $$ /$$$$$$ | $$ /$$   /$$
 *   | $$  | $$__  $$| $$$$   /$$__  $$| $$$$$$$//$$__  $$| $$| $$  | $$
 *   | $$  | $$  \ $$| $$_/  | $$  \ $$| $$____/| $$  \ $$| $$| $$  | $$
 *   | $$  | $$  | $$| $$    | $$  | $$| $$     | $$  | $$| $$| $$  | $$
 *  /$$$$$$| $$  | $$| $$    |  $$$$$$/| $$     |  $$$$$$/| $$|  $$$$$$$
 * |______/|__/  |__/|__/     \______/ |__/      \______/ |__/ \____  $$
 *                                                             /$$  | $$
 *                                                            |  $$$$$$/
 *                                                             \______/
 *
 * Entry point and host of main method of the InfoPoly game. Instances a new View, and with it, starts the entire game.
 *
 * @author ritsctob, zankoerm, smailnik, corrooli
 */
public class InfoPoly {

    public static void main(String[] args) {
        setUpGame();
    }
    /**
     * Starts an UI (and a new game)
     */
    private static void setUpGame() {
        MainWindowView view = new MainWindowView();
        view.startUI();
    }
}
