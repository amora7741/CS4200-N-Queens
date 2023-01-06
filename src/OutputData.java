public class OutputData { //class to store output data of the solved board
    boolean solved;
    Board board;
    int searchCost;

    OutputData(boolean solved, Board board, int searchCost){
        this.solved = solved;
        this.board = board;
        this.searchCost = searchCost;
    }

    public Board getBoard(){ //return solved board
        return board;
    }

    public int getCost(){ //return search cost of the solved board
        return searchCost;
    }
}
