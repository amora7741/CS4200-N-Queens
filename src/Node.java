public class Node {
    int[] board;
    Node neighbor;
    public Node(int[] board){
        this.board = board;
    }

    private void setNeighbor(Node neighbor){
        this.neighbor = neighbor;
    }
}
