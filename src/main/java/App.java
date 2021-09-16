
public class App {
    public static void main(String[] args) {

        AirspaceReader mainReader = new HibernateDataReader();
        AirspaceReader fallbackReader = new FallbackDataReader();

        //use FallbackDataReader to speed up launch time
        MapWindow mapWindow = new MapWindow(fallbackReader.readAirspace());
        mapWindow.setVisible(true);
    }
}
