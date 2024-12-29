import java.util.*;

public class GameSetter {
    static Set<LadderInfo> ladderSet;
    static HashSet<SnakeInfo> snakesSet;
    static Queue<Player> players= new LinkedList<>();
    static int noOfPlayers;

    static Map<Integer,Cell> gameBoard;
    static String board[][];
    public static void setUpLadders()
    {
        ladderSet= new HashSet<>();

        LadderInfo ladder= new LadderInfo(0,4,5,5,3,57);
        ladderSet.add(ladder);

        ladder= new LadderInfo(1,6,14,4,8,49);
        ladderSet.add(ladder);

        ladder= new LadderInfo(4,1,42,5,0,60);
        ladderSet.add(ladder);

        ladder= new LadderInfo(5,7,53,7,8,72);
        ladderSet.add(ladder);

        ladder= new LadderInfo(6,3,64,8,2,83);
        ladderSet.add(ladder);

        ladder= new LadderInfo(7,5,75,9,6,94);
        ladderSet.add(ladder);
    }
    public static void setUpSnakes()
    {
        snakesSet= new HashSet<>();
        SnakeInfo snake= new SnakeInfo(3,2,38,1,0,20);
        snakesSet.add(snake);

        snake= new SnakeInfo(4,4,45,0,6,7);
        snakesSet.add(snake);

        snake= new SnakeInfo(5,9,51,0,9,10);
        snakesSet.add(snake);

        snake= new SnakeInfo(6,4,65,5,6,54);
        snakesSet.add(snake);

        snake= new SnakeInfo(9,9,91,7,7,73);
        snakesSet.add(snake);

        snake= new SnakeInfo(9,3,97,0,2,3);
        snakesSet.add(snake);
    }

    public static void setUpGameBoard()
    {
        gameBoard= new TreeMap<>();
        int value=1;
        for(int i=0;i<10;i++)
        {
            if(i%2==0)
            {
                for(int j=0;j<10;j++)
                {
                    gameBoard.put(value,new Cell(i,j));
                    value++;
                }
            }
            else
            {
                for(int j=9;j>=0;j--)
                {
                    gameBoard.put(value,new Cell(i,j));
                    value++;
                }
            }

        }
    }
    public static void setUpPlayers()
    {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the number of players");

        noOfPlayers= sc.nextInt();

        sc.nextLine();

        for(int i=0;i<noOfPlayers;i++)
        {
            System.out.println("Enter Name Of Player"+ (i+1));
            String name= sc.nextLine();

            players.add(new Player(name,0,false));
        }
    }
    public static void showBoard()
    {
        board= new String[10][10];
        for(Map.Entry<Integer,Cell> boardEntry: gameBoard.entrySet())
        {
            Cell cell= boardEntry.getValue();
            board[cell.getRow()][cell.getCol()]=String.valueOf(boardEntry.getKey());
        }
        int ladderNumber=1;
        for(LadderInfo ladder: ladderSet)
        {
            board[ladder.getStartRow()][ladder.getStartCol()]= "L"+ladderNumber;
            board[ladder.getEndRow()][ladder.getEndCol()]= "L"+ladderNumber;
            ladderNumber++;
        }

        int snakeNumber=1;
        for(SnakeInfo snake: snakesSet)
        {
            board[snake.getStartRow()][snake.getStartCol()]= "S"+snakeNumber;
            board[snake.getEndRow()][snake.getEndCol()]= "S"+snakeNumber;
            snakeNumber++;
        }

        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
