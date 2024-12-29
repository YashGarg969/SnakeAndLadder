public class Player {

    String playerName;
    int currPosition;
    boolean isWinner;

    public Player(String playerName, int currPosition, boolean isWinner) {
        this.playerName = playerName;
        this.currPosition = currPosition;
        this.isWinner = isWinner;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getCurrPosition() {
        return currPosition;
    }

    public void setCurrPosition(int currPosition) {
        this.currPosition = currPosition;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
