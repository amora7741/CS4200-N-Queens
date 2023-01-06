import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Genetic {
    static final int boardSize = Board.boardSize;
    static int popSize = 200;
    final double mutationChance = 0.40;

    public OutputData solve(){
        double generations = 0;
        Random rand = new Random();
        Population startingPopulation = new Population();
        Population newPopulation;
        int maxIterations = 5000;
        int searchCost = 0;

        for(int i = 0; i < popSize; i++){
            startingPopulation.p.add(new Board());
            searchCost++;
        }

        for(int iteration = 0; iteration < maxIterations; iteration++){
            newPopulation = new Population();

            for(int i = 0; i < startingPopulation.p.size(); i++){
                Board mother = startingPopulation.randomSelect();
                Board father = startingPopulation.randomSelect();

                Board child = reproduce(mother, father);
                
                if(rand.nextDouble() <= mutationChance)
                    child = mutate(child.getBoard());

                if(child.getFitness() == 0)
                    return new OutputData(true, child, searchCost);

                newPopulation.p.add(child);
                searchCost++;
            }
            
            startingPopulation.setList(newPopulation.p);
        }

        startingPopulation.sortbyFitness();

        return new OutputData(false, startingPopulation.p.get(0), searchCost);
    }

    private Board reproduce(Board x, Board y){
        int n = x.getBoard().length;
        Random c = new Random();
        int rand = c.nextInt(n);
        int[] child = new int[n];

        for(int i = 0; i < n; i++){
            if(i <= rand)
                child[i] = x.getBoard()[i];
            else
                child[i] = y.getBoard()[i];

        }

        return new Board(child);
    }

    private Board mutate(int[] child){
        int n = child.length;
        Random rand = new Random();
        int index = rand.nextInt(n);

        child[index] = rand.nextInt(n);

        return new Board(child);
    }
}