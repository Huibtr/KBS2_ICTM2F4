import java.util.ArrayList;

public class Coordination {
    private ArrayList<Integer> coordination;

    public Coordination(){
        coordination = new ArrayList<>();
    }

    public void AddCoordination(Integer coordination){
        this.coordination.add(coordination);
    }

    public ArrayList<Integer> getCoordination() {
        return coordination;
    }
}
