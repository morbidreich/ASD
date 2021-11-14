package io.github.morbidreich.dataPrepUtils;

//VOR - 7
//DME - 13

import io.github.morbidreich.utils.CoordinateConverter;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class VorQueryCreator {

    public static void main(String[] args) {

        try {
            File VORs = new File("src/main/resources/EPSY/VOR.txt");

            Scanner myReader = new Scanner(VORs);
            FileWriter writer = new FileWriter("src/main/resources/EPSY/VOR_resultQuery.txt");

            while (myReader.hasNextLine()) {

                String[] lines = myReader.nextLine().replace(',', '.').split("\t");

                String name = "'" + lines[0] + "'";
                String lat = CoordinateConverter.getSfromD(Double.parseDouble(lines[1]), CoordinateConverter.CoordType.N) + " ";
                String lon = CoordinateConverter.getSfromD(Double.parseDouble(lines[2]), CoordinateConverter.CoordType.E) + "', ";

                    //insert into fix (coordinates, fix_name, fix_type_id) values ('49°46''16"N 019°13''04"E', 'NIVON', 12);
                    String queryStart = "insert into fix (coordinates, fix_name, fix_type_id) values ('";
                    String queryEnd = ", 7);";

                    writer.write(queryStart + lat + lon + name + queryEnd + "\n");
            }
            myReader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
