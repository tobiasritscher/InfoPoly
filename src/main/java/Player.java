public class Player {
    private int playerNumber;
    private int money;
    private int credits;
    private String name;
    private boolean isBroke = false;
    private boolean isWaiting = false;

    public Player(String name, int money, int credits, int playerNumber) {
        this.name = name;
        this.credits = credits;
        this.money = money;
        this.playerNumber = playerNumber;
    }

    public Player() {
    }

    public int getMoney() {
        return money;
    }

    public int getCredits() {
        return credits;
    }

    public String getName() {
        return name;
    }

    public boolean getIsWaiting() {
        return isWaiting;
    }

    public void isWaiting() {
        isWaiting = true;
    }

    public void isNotWaiting() {
        isWaiting = false;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public boolean isBroke() {
        return isBroke;
    }

    public void winMoney(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Cannot earn negative amount or 0");
        }
        if ((money - amount) <= 0) {
            isBroke = true;
        } else {
            money = money - amount;
        }
    }
}
