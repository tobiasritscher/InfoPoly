package ch.zhaw.pm2.caffeineaddicts.infopoly.model;

public class StartupGameField {
    private final int moneyNeeded;
    private final int moneyPayout;
    private int founderId;

    public StartupGameField(int moneyNeeded, int moneyPayout) {
        this.moneyNeeded = moneyNeeded;
        this.moneyPayout = moneyPayout;
        founderId = -1;
    }

    public boolean isLaunched() {
        return (founderId == -1) ? false : true;
    }

    /**
     * Note: before calling call @{@link StartupGameField#isLaunched()}
     * Will throw exception if not launched.
     *
     * @return
     */
    public int getFounderId() {
        if (!isLaunched()) {
            throw new RuntimeException("invalid operation: field already has founder");
        }
        return founderId;
    }

    public void setFounderId(int founderId) {
        if (isLaunched()) {
            throw new RuntimeException("invalid operation: field already has founder");
        }
        this.founderId = founderId;
    }

    public int getMoneyNeeded() {
        return moneyNeeded;
    }

    public int getMoneyPayout() {
        if (!isLaunched()) {
            throw new RuntimeException("invalid operation: no may money may obtained, startup is not launched yet.");
        }
        return moneyPayout;
    }
}
