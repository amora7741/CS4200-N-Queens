import java.util.Arrays;
import java.util.Random;

public class Board {
    final private int boardSize = 8;
    private int fitness;
    private int[] board = new int[boardSize];
    Random randInt = new Random();

    public Board(){
        for(int i  = 0; i < boardSize; i++){
            board[i] = randInt.nextInt(boardSize); 
        }

        fitness = getFitness();
    }

    private int getFitness(){ //calculates how many queens are in the same row/column/diagonal
        for(int i = 0; i < board.length - 1; i++){
            for(int j = i + 1; j < board.length; j++){
                if(board[i] == board[j]) //same row
                    fitness++;
                else if((j - i) == Math.abs(board[j] - board[i])) //same diagonal
                    fitness++;
            }
        }

        return fitness;
    }

    private void boardToString(){ //used to print the board in terminal
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[i] == j)
                    System.out.print("[X]");
                else
                    System.out.print("[ ]");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Board fart = new Board();
        System.out.println(Arrays.toString(fart.board));
        fart.boardToString();
        System.out.println(fart.getFitness());
    }
}
