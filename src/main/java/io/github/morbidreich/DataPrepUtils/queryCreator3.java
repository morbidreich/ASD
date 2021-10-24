package io.github.morbidreich.DataPrepUtils;

import io.github.morbidreich.CoordinateConverter;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class queryCreator3 {
    public static void main(String[] args) {

        try {
            File myFile = new File("src/main/resources/EPSY/allAirports.txt");
            Scanner myReader = new Scanner(myFile);

            FileWriter writer = new FileWriter("src/main/resources/EPSY/airport_query.txt");

            int i = 1;

            while (myReader.hasNextLine()) {

                String[] out = myReader.nextLine().replace(',','.').split("\t");

                if (out.length == 3) {
                    double lat = Double.valueOf(out[1]);
                    double lon = Double.valueOf(out[2]);
                    String coords = CoordinateConverter.getFromLatLon(lat, lon);
                    String AD = out[0];

                    //insert into fix (coordinates, fix_name, fix_type_id) values ('49°46''16"N 019°13''04"E', 'EPZR', 8);
                    String query1 = "insert into fix (coordinates, fix_name, fix_type_id) values (";
                    String query2 = "', 8);";


                    writer.write(query1 + coords + ",'" + AD + query2 + "\n");
                }
            }

            myReader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
