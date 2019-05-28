import javax.swing.*;
import java.util.*;

public class BingoGame{
    private int columns;
    private int numPlayers;
    private Random rand = new Random();
    private int number;
    private ArrayList<Integer> pickedNumbers = new ArrayList<>();
    private ArrayList<BingoCard> cards;

    public BingoGame(){
    }

    public BingoGame(int num, int col){
        numPlayers = num;
        columns = col;
        cards = new ArrayList<>();
        initCards();
        checkCards();
    }

    public void initCards(){
        for(int i = 0; i < numPlayers; i++){
            BingoCard card = new BingoCard(columns);
            cards.add(card);
        }
    }

    public void pickNumber(){
        boolean uniqueNum = true;
        number = rand.nextInt(15 * columns) + 1;
        for(int num: pickedNumbers){
            if(num == number){
                uniqueNum = false;
            }
        }
        if(uniqueNum){
            System.out.println("Number: " + number);
            pickedNumbers.add(number);
        }
        else{
            pickNumber();
        }

    }

    public boolean isGameOver(){
        boolean over = false;
        for(int player = 0; player < cards.size(); player++){
            if(cards.get(player).checkForWinner()){
                over = true;
                System.out.println("Player " + (player + 1) + " wins!");
                break;
            }
        }
        return over;
    }

    public void checkCards(){
        while (!isGameOver()) {
            //JOptionPane.showMessageDialog(null, "Press Enter to pick the next number.");
            pickNumber();
            for (int player = 0; player < cards.size(); player++) {
                cards.get(player).checkBoard(number);
                System.out.println("Player " + (player + 1) + ":");
                System.out.println(cards.get(player).printBoard());
            }
        }
    }

    public static void main(String[] args) {
        int players = Integer.parseInt(JOptionPane.showInputDialog("How many people are playing? "));
        BingoGame myGame = new BingoGame(players, 5);
    }
}
