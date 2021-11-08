package io.github.morbidreich;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempTest {
    public static void main(String[] args) {
       AirspaceReader ar = new HibernateDataReader();
       Airspace airspace = ar.readAirspace();

       List<Fix> fixList = airspace.getFixList();
       Map<String, Fix> fixMap = new HashMap<>();

        for (Fix f : fixList)
            fixMap.put(f.getName(), f);

        //fixMap.

    }
}
