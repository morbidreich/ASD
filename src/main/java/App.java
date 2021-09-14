
public class App {
    public static void main(String[] args) {



        AirspaceReader reader = new HibernateDataReader();
        MapWindow mapWindow = new MapWindow(reader.readAirspace());
        mapWindow.setVisible(true);


    }
}
