
public class App {
    public static void main(String[] args) {

        AirspaceReader reader = new HibernateDataReader();
        //Airspace airspace = new Airspace();
        Airspace airspace = reader.readAirspace();


        MapWindow mapWindow = new MapWindow(airspace);

        mapWindow.setVisible(true);

    }
}
