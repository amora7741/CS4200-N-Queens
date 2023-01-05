public class Main{
    public static void main(String[] args) {
        OutputData output;
        SteepestHill s = new SteepestHill();
        double count = 0, iterations = 1000;

        for(int i = 0; i < iterations; i++){
            Board b = new Board();
            output = s.solve(b);

            output.getBoard().boardToString();
            System.out.println(output.getBoard().getFitness());

            if(output.solved)
                count++;
        }

        System.out.printf("Percentage of Boards Solved: %.2f", (count / iterations));
    }
}