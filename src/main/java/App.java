
public class App {
    public static void main(String[] args) {
        Airspace airspace = new Airspace();
        MapWindow mapWindow = new MapWindow(airspace);

        mapWindow.setVisible(true);

    }
}
