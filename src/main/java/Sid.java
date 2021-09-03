public class Sid extends Polygon {
    private Runway runway;

    public Sid(String name, Runway runway) {
        super(name);
        this.runway = runway;
    }

    public Runway getRunway() {
        return runway;
    }
}
