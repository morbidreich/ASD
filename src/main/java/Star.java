import java.util.List;

public class Star extends Polygon {
    private Runway runway;

    public Star(String name, Runway runway) {
        super(name);
        this.runway = runway;
    }

    public Star(String name, List<Fix> fixList) {
        super(name, fixList);
    }

    public Star(String name, List<Fix> fixList, Runway runway) {
        super (name, fixList);
        this.runway = runway;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }


}
