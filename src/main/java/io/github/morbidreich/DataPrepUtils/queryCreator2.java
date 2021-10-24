package io.github.morbidreich.DataPrepUtils;

import io.github.morbidreich.CoordinateConverter;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class queryCreator2 {
    public static void main(String[] args) {

        try {
            File myFile = new File("src/main/resources/EPSY/polska.txt");
            Scanner myReader = new Scanner(myFile);

            FileWriter writer = new FileWriter("src/main/resources/EPSY/border_query2.txt");

            int i = 1;

            while (myReader.hasNextLine()) {

                String[] out = myReader.nextLine().split("\s");

                i++;
                if (i == 3) {
                    i = 1;
                    continue;
                }

                //insert into point (coordinates, polygon_id) values ('49°26''01"N 021°08''59"E', 13);
                String query1 = "insert into point (coordinates, polygon_id) values (";
                String coords = CoordinateConverter.getFromLatLon(Double.valueOf(out[0]), Double.valueOf(out[1]));
                String query2 = ", 13);";


                writer.write(query1 + coords + query2 + "\n");
//

            }

            myReader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
