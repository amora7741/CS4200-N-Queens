import java.util.HashMap;
import java.util.Random;

public class SteepestHill {
    private int[] test;

    public OutputData solve(Board board){
        int[] current = board.getBoard();
        int searchCost = 0;
        
        while(true){
            test = getBestSuccessor(current);
            searchCost++;
            
            if(getFitness(current) <= getFitness(test)){
                test = current.clone();
                Board b = new Board(test);

                return new OutputData(getFitness(test) == 0, b, searchCost);
            }

            current = test;
        }
    }

    private int[] getBestSuccessor(int[] puzzle){
        int[] board = puzzle.clone();
        HashMap<Integer, HashMap<Integer, Integer>> moves = new HashMap<>();
        for (int col = 0; col < board.length; col++) {
            int bestMove = board[col];
            HashMap<Integer, Integer> move = new HashMap<>();
            for (int row = 0; row < board.length; row++) {
                if (board[col] == row) {
                    continue;
                }
                int[] boardCopy = board.clone();
                boardCopy[col] = row;
                move.put(row, getFitness(boardCopy));
            }
            moves.put(col, move);
        }
        int hToBeat = getFitness(board);
        HashMap<Integer, Integer> bestMoves = new HashMap<>();
        for (HashMap<Integer, Integer> entry : moves.values()) {
            for (int row : entry.keySet()) {
                int hCost = entry.get(row);
                if (hCost < hToBeat) {
                    hToBeat = hCost;
                }
            }
        }
        for (int col : moves.keySet()) {
            HashMap<Integer, Integer> entry = moves.get(col);
            for (int row : entry.keySet()) {
                int hCost = entry.get(row);
                if (hCost == hToBeat) {
                    bestMoves.put(col, row);
                }
            }
        }
        if (bestMoves.size() > 0) {
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
