import java.util.List;

public class Sid extends Polygon {
    private Runway runway;

    public Sid(String name, Runway runway) {
        super(name);
        this.runway = runway;
    }

    public Sid(String name, List<Fix> fixList) {
        super(name, fixList);
    }

    public Sid(String name, List<Fix> fixList, Runway runway) {
        super(name, fixList);
        this.runway = runway;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }
}
