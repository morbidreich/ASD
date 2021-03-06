package io.github.morbidreich.playground;

import io.github.morbidreich.airspaceElements.Procedure;
import io.github.morbidreich.utils.HibernateUtils;
import org.hibernate.Session;

public class    ProcedureLoader {
    public static void main(String[] args) {
        Session session = HibernateUtils.factory.getCurrentSession();

        int id = 11;

        try {
            session.beginTransaction();
            Procedure p = session.get(Procedure.class, id);


            System.out.println("Loaded procedure " + p);
            System.out.println(p.getFixList());


            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
