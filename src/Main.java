public class Main{
    public static void main(String[] args) {
        OutputData output;
        Genetic s = new Genetic();
        double count = 0, iterations = 100;

        for(int i = 0; i < iterations; i++){
            output = s.solve();

            output.getBoard().boardToString();
            System.out.println(output.getBoard().getFitness());

            if(output.solved)
                count++;
        }

        System.out.printf("Percentage of Boards Solved: %.3f", (count / iterations));
    }
}