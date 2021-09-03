
public class Star extends Polygon {
    private Runway runway;

    public Star(String name, Runway runway) {
        super(name);
        this.runway = runway;
    }

    public Runway getRunway() {
        return runway;
    }
}
