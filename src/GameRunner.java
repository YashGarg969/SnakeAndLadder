import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameRunner {
    static  List<String> winnersList= new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        GameSetter.setUpGameBoard();
        GameSetter.setUpLadders();
        GameSetter.setUpSnakes();
        GameSetter.showBoard();
        GameSetter.setUpPlayers();
        playGame();
        showWinnerList();
    }
    public static void playGame() throws InterruptedException {

        while (GameSetter.players.size()!=0)
        {
            Player currPlayer= GameSetter.players.poll();

            System.out.println("Current Player is "+ currPlayer.getPlayerName());

            boolean isWinner= currPlayer.isWinner();
            if(!isWinner)
            {
                int currPlayerPosition= currPlayer.getCurrPosition();
                if(currPlayerPosition==0)
                {
                    currPlayer= givePlayerAChance(currPlayerPosition,currPlayer);
                    if(currPlayer.isWinner())
                    {
                        System.out.println("Its position is "+ currPlayer.getCurrPosition());
                        System.out.println("Added to Winners List");
                        winnersList.add(currPlayer.getPlayerName());
                    }
                    else
                        GameSetter.players.add(currPlayer);
                }
                else
                {
                    Cell playerCell= GameSetter.gameBoard.get(currPlayerPosition);
                    int playerRow= playerCell.getRow();
                    int playerCol= playerCell.getCol();

                    currPlayer= givePlayerAChance(currPlayerPosition,currPlayer);
                    if(currPlayer.isWinner())
                    {
                        winnersList.add(currPlayer.getPlayerName());
                    }
                    else
                        GameSetter.players.add(currPlayer);
                }
            }
        }
    }

    public static Player givePlayerAChance(int playerPosition, Player currPlayer) throws InterruptedException {
        int diceValue= rollDice();

        System.out.println("Rolling Dice....");
        Thread.sleep(1000);
        System.out.println("Dice Value is "+ diceValue);

        if(playerPosition==0 && diceValue!=1)
            return currPlayer;

        int newPosition= playerPosition;

        if(diceValue==6)
        {
            while(diceValue==6)
            {
                System.out.println("Congrats!! You got another chance");
                newPosition+=diceValue;
                diceValue= rollDice();
                System.out.println("New chance's dice value is "+ diceValue);
            }
        }
        newPosition+=diceValue;

        if(newPosition>100)
            return currPlayer;

        if(newPosition==100) {
            currPlayer.setWinner(true);
            return currPlayer;
        }


        Cell newCell= GameSetter.gameBoard.get(newPosition);
        int newRow= newCell.getRow();
        int newCol= newCell.getCol();

        //this line is added
        currPlayer.setCurrPosition(newPosition);



        System.out.println("You are at position:- "+ newPosition);

        LadderInfo ladder= checkIfThereIsLadder(newRow,newCol);
        SnakeInfo snake= checkIfThereIsSnake(newRow,newCol);

        if(ladder==null && snake==null)
            return currPlayer;

        else if(ladder!=null)
        {
            System.out.println("Hurreh!! Here is a ladder...You got another chance");
            System.out.println("Now, you are going to position "+ ladder.getEndNumber());
            newRow= ladder.getEndRow();
            newCol= ladder.getEndCol();
            currPlayer.setCurrPosition(ladder.getEndNumber());
            System.out.println("Your current position is now: "+ currPlayer.getCurrPosition());
            currPlayer= givePlayerAChance(currPlayer.getCurrPosition(),currPlayer);
        }
        else if(snake!=null)
        {
            System.out.println("Ufff!!! You got a bite from snake");
            System.out.println("Please go to position "+ snake.getEndNumber());
            currPlayer.setCurrPosition(snake.getEndNumber());
            System.out.println("Your new position is set to: "+ currPlayer.getCurrPosition());
            return currPlayer;
        }
        return currPlayer;
    }
    public static SnakeInfo checkIfThereIsSnake(int row, int col)
    {
        for(SnakeInfo snakeInfo: GameSetter.snakesSet)
        {
            if(snakeInfo.getStartRow()==row && snakeInfo.getStartCol()==col)
                return snakeInfo;
        }
        return null;
    }

    public static LadderInfo checkIfThereIsLadder(int row, int col)
    {
        for(LadderInfo ladderInfo: GameSetter.ladderSet)
        {
            if(ladderInfo.getStartRow()==row && ladderInfo.getStartCol()==col)
                return ladderInfo;
        }
        return null;
    }
    public static int rollDice()
    {
        return ThreadLocalRandom
                .current()
                .nextInt(1, 6+ 1);
    }
    public static void showWinnerList()
    {
        for(int i=0;i<winnersList.size();i++)
        {
            System.out.println("Player on position "+(i+1)+": "+ winnersList.get(i));
        }
    }
}