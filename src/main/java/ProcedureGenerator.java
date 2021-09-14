import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProcedureGenerator {
    public static void main(String[] args) {
        Session session = HibernateUtils.factory.getCurrentSession();

        //create procedure
        Procedure p1 = new Procedure("NIVON1S", Runway.RUNWAY_19, ProcedureType.SID);


        try {
            //load fixes for procedure
            session.beginTransaction();
            List<Fix> fixList = new ArrayList<>();
            System.out.println("Loading fixes");
            fixList.add((Fix) session.createQuery("from Fix where name='DER19'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY512'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY513'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='NIVON'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p1.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p1);


            session.getTransaction().commit();


            //save objects
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

}
