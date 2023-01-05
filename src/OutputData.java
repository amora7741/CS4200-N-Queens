public class OutputData {
    boolean solved;
    Board board;

    OutputData(boolean solved, Board board){
        this.solved = solved;
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }
}
