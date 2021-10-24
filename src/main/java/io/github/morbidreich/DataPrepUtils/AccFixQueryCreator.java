package io.github.morbidreich.DataPrepUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import io.github.morbidreich.CoordinateConverter;

public class AccFixQueryCreator {

    public static void main(String[] args) {

        try {
            File myFile = new File("src/main/resources/EPSY/allAccFixes.txt");
            Scanner myReader = new Scanner(myFile);

            FileWriter writer = new FileWriter("src/main/resources/EPSY/allAccFixes_resultQuery.txt");


            while (myReader.hasNextLine()) {

                String[] lines = myReader.nextLine().replace(',', '.').split("\t");

                String name = "'" + lines[0] + "'";
                String lat = CoordinateConverter.getSfromD(Double.parseDouble(lines[1]), CoordinateConverter.CoordType.N) + " ";
                String lon = CoordinateConverter.getSfromD(Double.parseDouble(lines[2]), CoordinateConverter.CoordType.E) + "', ";

                    //insert into fix (coordinates, fix_name, fix_type_id) values ('49°46''16"N 019°13''04"E', 'NIVON', 12);
                    String queryStart = "insert into fix (coordinates, fix_name, fix_type_id) values ('";
                    String queryEnd = ", 12);";


                    writer.write(queryStart + lat + lon + name + queryEnd + "\n");

            }

            myReader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
