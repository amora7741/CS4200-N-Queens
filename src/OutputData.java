public class OutputData {
    boolean solved;
    Board board;
    int searchCost;

    OutputData(boolean solved, Board board, int searchCost){
        this.solved = solved;
        this.board = board;
        this.searchCost = searchCost;
    }

    public Board getBoard(){
        return board;
    }
}
