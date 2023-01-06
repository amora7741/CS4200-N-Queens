import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Population {
    static int size = Genetic.popSize;
    ArrayList<Board> p = new ArrayList<>();
    
    public Board randomSelect(){
        Random rand = new Random();

        return p.get(rand.nextInt(size));
    }

    public void sortbyFitness(){
        p.sort(Comparator.comparingInt(a -> a.getFitness()));
    }

    @SuppressWarnings("unchecked")
    public void setList(ArrayList<Board> other){
        p = (ArrayList<Board>) other.clone();
    }
    
}
