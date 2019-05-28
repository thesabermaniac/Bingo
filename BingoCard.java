import java.util.Random;

public class BingoCard {
    private int numColumns;
    private int[][] board;
    private char[][] checkBoard;
    private int middle;
    private char marker = 'X';

    public BingoCard(){
        numColumns = 5;
        middle = numColumns/2;
        board = new int[numColumns][numColumns];
        checkBoard = new char[numColumns][numColumns];
        initBoard();
    }

    public BingoCard(int columns) {
        numColumns = columns;
        middle = numColumns/2;
        board = new int[numColumns][numColumns];
        checkBoard = new char[numColumns][numColumns];
        initBoard();
    }

    public void initBoard(){
        int number = 0;
        boolean contains;
        Random rand = new Random();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                contains = true;
                while (contains) {
                    number = rand.nextInt((15) + 1) + (15 * j);
                    for (int x = 0; x < board.length; x++) {
                        for (int y = 0; y < board.length; y++) {
                            if (board[x][y] == number) {
                                contains = true;
                                break;
                            }
                            else {
                                contains = false;
                            }
                        }
                        if(contains){
                            break;
                        }
                    }
                }
                board[i][j] = number;
            }
        }
        board[middle][middle] = 0;
    }

    public void checkBoard(int num){
        middle = numColumns/2;
        for(int i = 0; i < checkBoard.length; i++) {
            for (int j = 0; j < checkBoard.length; j++) {
                if (board[i][j] == num) {
                    checkBoard[i][j] = marker;
                }
            }
        }
        checkBoard[middle][middle] = marker;
    }

    public boolean checkForWinner(){
        boolean isGameOver = false;
        //Check left to right diagonal
        for(int i = 0; i < board.length; i++){
            if(checkBoard[i][i] != marker){
                isGameOver = false;
                break;
            }
            else{
                isGameOver = true;
            }
        }
        //Check right to left diagonal
        if(!isGameOver) {
            for (int i = 0; i < board.length; i++) {
                if (checkBoard[i][board.length - (i + 1)] != marker) {
                    isGameOver = false;
                    break;
                } else {
                    isGameOver = true;
                }
            }
        }
        //Check rows
        if(!isGameOver) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (checkBoard[i][j] != marker) {
                        isGameOver = false;
                        break;
                    } else {
                        isGameOver = true;
                    }
                }
                if (isGameOver) {
                    break;
                }
            }
        }
        //Check columns
        if(!isGameOver) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (checkBoard[j][i] != marker) {
                        isGameOver = false;
                        break;
                    } else {
                        isGameOver = true;
                    }
                }
                if (isGameOver) {
                    break;
                }
            }
        }
        return isGameOver;
    }

    public String printBoard() {
        StringBuilder s = new StringBuilder();
        char verticalSep = '|';
        if(numColumns == 5) {
            s.append(" B   I   N   G  O!\n");
        }
        else if(numColumns == 9){
            s.append(" V   A   S   T   B   I   N   G  O!\n");
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (checkBoard[i][j] == marker) {
                    s.append(" ");
                    s.append(checkBoard[i][j]);
                    s.append(" ");
                } else {
                    s.append(board[i][j]);
                    if(board[i][j] < 100) {
                        s.append(" ");
                    }
                    if (board[i][j] < 10) {
                        s.append(" ");
                    }
                }
                if (j < board.length - 1) {
                    s.append(verticalSep);
                }
            }
            s.append('\n');
        }
        return s.toString();
    }

}
