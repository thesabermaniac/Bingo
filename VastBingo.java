import javax.swing.*;

public class VastBingo extends BingoGame{

    public VastBingo(int col){
        int players = Integer.parseInt(JOptionPane.showInputDialog("How many people are playing?"));
        BingoGame myGame = new BingoGame(players, col);
    }

    public static void main(String[] args) {
        VastBingo vast = new VastBingo(9);
    }
}
