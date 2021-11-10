package io.github.morbidreich.dataPrepUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


// class to parse town list I got from http://astronomia.zagan.pl/art/wspolrzedne.html
// and prepare flyway migrations file

// WATCH OUT!
// source file has coordinates swapped compared to my convention - see line below:
// Augustów                22°58'E        53°51'N

// fix_type value for Towns is 9

public class CityQueryCreator {

    public static void main(String[] args) {

        try {
            File myFile = new File("src/main/resources/EPSY/allCities.txt");
            Scanner myReader = new Scanner(myFile);

            FileWriter writer = new FileWriter("src/main/resources/EPSY/allCities_resultQuery.txt");

            int i = 1;
            String cityName, lat, lon;


            while (myReader.hasNextLine()) {
                // example line
                // Augustów                22°58'E        53°51'N
                // 0                       24             39 -  start index of substring

                String line = myReader.nextLine();
                cityName = "'" + line.substring(0,24).trim().toUpperCase() + "'";
                lat = line.substring(39,45) + "'00\"N ";
                lon = "0" + line.substring(24,30) + "'00\"E', ";

                if (true) {


                    //insert into fix (coordinates, fix_name, fix_type_id) values ('49°46''16"N 019°13''04"E', 'Augustów', 9);
                    String queryStart = "insert into fix (coordinates, fix_name, fix_type_id) values ('";
                    String queryEnd = ", 9);";


                    writer.write(queryStart + lat + lon + cityName + queryEnd + "\n");
                }
            }

            myReader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
