
public class App {
    public static void main(String[] args) {

        AirspaceReader mainReader = new HibernateDataReader();
        AirspaceReader fallbackReader = new FallbackDataReader();

        //using FallbackDataReader to speed up launch time
        MapWindow mapWindow = new MapWindow(mainReader.readAirspace());
        mapWindow.setVisible(true);
    }
}
