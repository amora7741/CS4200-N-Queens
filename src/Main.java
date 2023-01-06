import java.util.Arrays;

public class Main{
    public static void main(String[] args) {
        OutputData output;
        Genetic gen = new Genetic();
        SteepestHill steep = new SteepestHill();
        double count = 0, iterations = 1000;
        double percent1, percent2;
        long start, end;
        long duration1 = 0, duration2 = 0;
        int avgcost1 = 0, avgcost2 = 0;
        
        for(int i = 0; i < iterations; i++){
            Board b = new Board();
            start = System.currentTimeMillis();
            output = steep.solve(b);
            end = System.currentTimeMillis() - start;
            duration1 += end;
            
            output.getBoard().boardToString();
            System.out.printf("Fitness: %d%n", output.getBoard().getFitness());
            System.out.printf("Search cost: %d%n", output.getCost());
            avgcost1 += output.getCost();

            System.out.println("------------------------------");

            if(output.solved)
                count++;
        }

        System.out.println("----STEEPEST HILL SEARCH DONE, STARTING GENETIC ALGORITHM----");
        percent1 = count / iterations;
        duration1 /= iterations;
        avgcost1 /= iterations;
        String output1 = String.format("Percentage of Boards Solved by Steepest-Hill Search: %.0f / %.0f = %.2f%% %n", count, iterations, percent1 * 100);
        count = 0;

        for(int i = 0; i < iterations; i++){
            start = System.currentTimeMillis();
            output = gen.solve();
            end = System.currentTimeMillis() - start;
            duration2 += end;
            output.getBoard().boardToString();

            System.out.println(Arrays.toString(output.getBoard().getBoard()));
            System.out.printf("Fitness: %d%n", output.getBoard().getFitness());
            System.out.printf("Search cost: %d%n", output.getCost());
            avgcost2 += output.getCost();

            System.out.println("------------------------------");

            if(output.solved)
                count++;
        }

        System.out.println("----GENETIC ALGORITHM DONE----\n-----------RESULTS------------");
        percent2 = count / iterations;
        duration2 /= iterations;
        avgcost2 /= iterations;
        String output2 = String.format("%nPercentage of Boards Solved by Genetic Algorithm: %.0f / %.0f = %.2f%% %n", count, iterations, percent2 * 100);
        
        System.out.print(output1);
        System.out.printf("Average Search Time for Steepest Hill Search: %d ms%n", duration1);
        System.out.printf("Average Search Cost for Steepest Hill Search: %d%n", avgcost1);

        System.out.print(output2);
        System.out.printf("Average Search Time for Genetic Algorithm: %d ms%n", duration2);
        System.out.printf("Average Search Cost for Genetic Algorithm: %d%n", avgcost2);
    }
}