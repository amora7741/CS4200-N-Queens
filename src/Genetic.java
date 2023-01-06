import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Genetic {
    static final int boardSize = Board.boardSize;
    static int popSize = 150;
    final double mutationChance = 0.40;

    public OutputData solve(){
        double generations = 0;
        Random rand = new Random();
        Population startingPopulation = new Population();
        Population newPopulation;
        int maxIterations = 5000;

        for(int i = 0; i < popSize; i++){
            startingPopulation.p.add(new Board());
        }

        for(int iteration = 0; iteration < maxIterations; iteration++){
            newPopulation = new Population();

            for(int i = 0; i < startingPopulation.p.size(); i++){
                Board mother = startingPopulation.randomSelect();
                Board father = startingPopulation.randomSelect();

                Board child = reproduce(mother, father);
                
                if(rand.nextDouble() <= mutationChance)
                    child = mutate(child);

                if(child.getFitness() == 0)
                    return new OutputData(true, child);

                newPopulation.p.add(child);
            }
            startingPopulation.setList(newPopulation.p);
        }

        startingPopulation.sortbyFitness();

        return new OutputData(false, startingPopulation.p.get(0));
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

    private Board mutate(Board child){
        int n = child.getBoard().length;
        Random rand = new Random();
        int index = rand.nextInt(n);

        child.getBoard()[index] = rand.nextInt(n);

        return child;
    }
}