import java.util.Scanner;

public class TicTacToeConsole {
    private Board board;
    private GameState currentState;
    private Seed currentPlayer;

    private static Scanner in = new Scanner(System.in);

    public TicTacToeConsole() {
        board = new Board();

        initGame();
        do {
            playerMove(currentPlayer);
            board.paint();
            updateGame(currentPlayer);

            if (currentState == GameState.Xs_WON) {
                System.out.println("'X' won! Bye!");
            } else if (currentState == GameState.Os_WON) {
                System.out.println("'O' won! Bye!");
            } else if (currentState == GameState.DRAW) {
                System.out.println("It's Draw! Bye!");
            }

            currentPlayer = (currentPlayer == Seed.Xs) ? Seed.Os : Seed.Xs;
        } while (currentState == GameState.PLAYING);
    }

    public void initGame() {
        board.init();
        currentPlayer = Seed.Xs;
        currentState = GameState.PLAYING;
    }

    public void playerMove(Seed theSeed) {
        boolean validInput = false;
        do {
            if (theSeed == Seed.Xs) {
                System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
            }
            int row = in.nextInt() - 1;
            int col = in.nextInt() - 1;
            if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                    && board.cells[row][col].content == Seed.EMPTY) {
                board.cells[row][col].content = theSeed;
                board.currentRow = row;
                board.currentCol = col;
                validInput = true;
            } else {
                System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                        + ") is not valid. Try again...");
            }
        } while (!validInput);
    }

    public void updateGame(Seed theSeed) {
        if (board.hasWon(theSeed)) {
            currentState = (theSeed == Seed.Xs) ? GameState.Xs_WON : GameState.Os_WON;
        } else if (board.isDraw()) {
            currentState = GameState.DRAW;
        }
    }

    public static void main(String[] args) {
        new TicTacToeConsole();
    }
}