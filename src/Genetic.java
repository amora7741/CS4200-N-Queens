import java.util.Random;

public class Genetic {
    static final int boardSize = Board.boardSize;
    static int popSize = 200; //starting population size
    final double mutationChance = 0.30; //chance of mutation of offspring

    public OutputData solve(){
        Random rand = new Random();
        Population startingPopulation = new Population();
        Population newPopulation;
        int maxIterations = 4000; //amount of iterations before a child is returned if no solution found
        int searchCost = 0;

        for(int i = 0; i < popSize; i++){
            startingPopulation.p.add(new Board()); //add n amount of boards to the starting population
            searchCost++;
        }

        for(int iteration = 0; iteration < maxIterations; iteration++){
            newPopulation = new Population(); //create a new population each iteration

            for(int i = 0; i < startingPopulation.p.size(); i++){
                Board mother = startingPopulation.randomSelect(); //select random boards to produce a child
                Board father = startingPopulation.randomSelect();

                Board child = reproduce(mother, father); //produce a child using elements from each parent
                
                if(rand.nextDouble() <= mutationChance) //if chance of mutation is hit, mutate the child
                    child = mutate(child.getBoard());

                if(child.getFitness() == 0) //check if the child board is solved, returning it as a solution if so
                    return new OutputData(true, child, searchCost);

                newPopulation.p.add(child); //add the child to the new population
                searchCost++; //increase search cost
            }
            
            startingPopulation.setList(newPopulation.p); //set starting population for the next iteration
        }

        startingPopulation.sortbyFitness(); //if max iterations are reached, sort the population by attacking queens

        return new OutputData(false, startingPopulation.p.get(0), searchCost); //return the resulting board, not solved
    }

    private Board reproduce(Board x, Board y){ //produce a child from mother and father board
        int n = x.getBoard().length;
        Random c = new Random();
        int rand = c.nextInt(n); //randomly choose the crossover point 
        int[] child = new int[n];

        for(int i = 0; i < n; i++){
            if(i <= rand) //if crossover point has not been reached, continue adding values from first parent
                child[i] = x.getBoard()[i];
            else //if crossover point reached, add values from second parent
                child[i] = y.getBoard()[i];

        }

        return new Board(child);
    }

    private Board mutate(int[] child){
        int n = child.length;
        Random rand = new Random();
        int index = rand.nextInt(n); //choose child index to be mutated

        child[index] = rand.nextInt(n); //set mutate index as a random value

        return new Board(child);
    }
}