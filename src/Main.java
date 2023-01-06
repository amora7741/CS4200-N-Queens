public class Main{
    public static void main(String[] args) {
        OutputData output;
        Genetic gen = new Genetic();
        SteepestHill steep = new SteepestHill();
        double count = 0, iterations = 1000;
        double percent1, percent2;
        
        for(int i = 0; i < iterations; i++){
            Board b = new Board();
            output = steep.solve(b);
            
            output.getBoard().boardToString();

            System.out.println("------------------------------");

            if(output.solved)
                count++;
        }

        percent1 = count / iterations;
        count = 0;

        for(int i = 0; i < iterations; i++){
            output = gen.solve();
            output.getBoard().boardToString();

            System.out.println("------------------------------");

            if(output.solved)
                count++;
        }

        percent2 = count / iterations;
        
        System.out.printf("Percentage of Boards Solved by Steepest-Hill Algo: %.3f%n", percent1);
        System.out.printf("Percentage of Boards Solved by Genetic Algo: %.3f", percent2);
    }
}