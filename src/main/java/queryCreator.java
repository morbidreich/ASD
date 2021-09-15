import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class queryCreator {
    public static void main(String[] args) {

        try {
            File myFile = new File("src/main/resources/EPSY/border.txt");
            Scanner myReader = new Scanner(myFile);

            FileWriter writer = new FileWriter("src/main/resources/EPSY/border_query.txt");

            while (myReader.hasNextLine()) {
                String[] lines = myReader.nextLine().replace(',', '.').split("\t");

                //insert into point (coordinates, polygon_id) values ('53°23''33"N 020°15''25"E', 12);
                String queryStart = "insert into point (coordinates, polygon_id) values (";

                String coords = CoordinateConverter.getFromLatLon(Double.valueOf(lines[0]), Double.valueOf(lines[1]));

                String queryEnd = ", 12);";

                writer.write(queryStart + coords + queryEnd + "\n");


            }

            myReader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
