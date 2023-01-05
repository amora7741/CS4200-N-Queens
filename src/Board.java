import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class Board {
    final private int boardSize = 8;
    private int fitness;
    private int[] board = new int[boardSize];
    Random randInt = new Random();

    public Board(){
        for(int i = 0; i < boardSize; i++){
            board[i] = randInt.nextInt(boardSize); 
        }

        fitness = getFitness();
    }

    public int[] getBoard(){
        return board;
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

    private void boardToString(){ //used to print the board
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < board.length; i++){
            String output = "";
            for(int j = 0; j < board.length; j++){
                if(board[j] == i)
                    output += "[X]";
                else
                    output += "[ ]";
            }
            output += "\n";
            stack.push(output);
        }

        while(!stack.isEmpty())
            System.out.print(stack.pop());
    }
    public static void main(String[] args) {
        Board fart = new Board();
        System.out.println(Arrays.toString(fart.board));
        fart.boardToString();
        System.out.println(fart.getFitness());
    }
}
