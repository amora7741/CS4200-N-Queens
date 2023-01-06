import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Population {
    static int size = Genetic.popSize;
    ArrayList<Board> p = new ArrayList<>();
    
    public Board randomSelect(){ //randomly choose a board from the population
        Random rand = new Random();

        return p.get(rand.nextInt(size));
    }

    public void sortbyFitness(){ //sort the list by amount of attacking pairs
        p.sort(Comparator.comparingInt(a -> a.getFitness()));
    }

    @SuppressWarnings("unchecked") 
    public void setList(ArrayList<Board> other){ //used to set the starting population as the new population
        p = (ArrayList<Board>) other.clone();
    }
    
}
