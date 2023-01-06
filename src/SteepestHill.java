import java.util.HashMap;
import java.util.Random;

public class SteepestHill {
    private int[] test;

    public OutputData solve(Board board){
        int[] current = board.getBoard();
        int searchCost = 0;
        
        while(true){
            test = getBestSuccessor(current); //get the best successor from the current board
            searchCost++; //increase search cost
            
            if(getFitness(current) <= getFitness(test)){ //if amount of attacking queens in current board is lower than best successor, choose the current board to return
                test = current.clone();
                Board b = new Board(test); //create a board with the current board

                return new OutputData(getFitness(test) == 0, b, searchCost); //return output data with either true/false solved value and search cost
            }

            current = test; //if the value of the successor is lower than the current puzzle, set the current puzzle as the successor
        }
    }

    private int[] getBestSuccessor(int[] puzzle){ //get the best successor of the current node
        int[] board = puzzle.clone();
        HashMap<Integer, HashMap<Integer, Integer>> moves = new HashMap<>(); //store possible moves that can be made on the board

        for (int col = 0; col < board.length; col++) {
            HashMap<Integer, Integer> move = new HashMap<>(); //store row values that can be placed in the current column

            for (int row = 0; row < board.length; row++) { //iterate through rows of the array
                if (board[col] == row)
                    continue;

                int[] boardCopy = board.clone(); 
                boardCopy[col] = row; //if current row is not value of the column, place row value in a clone of the current board
                move.put(row, getFitness(boardCopy)); //store row value and fitness of the clone
            }
            moves.put(col, move); //store all possible row moves
        }
        int hToBeat = getFitness(board); //calculate fitness of original board to test other values
        HashMap<Integer, Integer> bestMoves = new HashMap<>(); //used to store moves which contain best fitness values

        for (HashMap<Integer, Integer> entry : moves.values()) { //iterate through each entry from all possible moves
            for (int row : entry.keySet()) { 
                int hCost = entry.get(row);

                if (hCost < hToBeat) //if attacking queens of entry is lower than current board, set fitness value to beat as the current entry
                    hToBeat = hCost;
            }
        }
        for (int col : moves.keySet()) {
            HashMap<Integer, Integer> entry = moves.get(col);

            for (int row : entry.keySet()) {
                int hCost = entry.get(row); //get fitness value of the current row

                if (hCost == hToBeat) //if fitness value is equal to the fitness value to beat, add the column/row values to the best possible moves
                    bestMoves.put(col, row);
            }
        }
        if (bestMoves.size() > 0) { //if best moves is not empty, pick a random move from best moves and return that board
            Random rand = new Random();
            int pick = rand.nextInt(bestMoves.size());
            int col = (int) bestMoves.keySet().toArray()[pick];
            int row = bestMoves.get(col);
            board[col] = row;
        }

        return board;
    }

    private int getFitness(int[] board){ //calculates how many queens are in the same row/column/diagonal
        int fitness = 0;
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
}
