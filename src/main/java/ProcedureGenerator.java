//thats a bit of shameful code
//i use it to generate sql table, just to get that table
//and manually rewrite it to flyway migration scripts :(
//to be able to use it later with hibernate WTF?


import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class    ProcedureGenerator {
    public static void main(String[] args) {
        Session session = HibernateUtils.factory.getCurrentSession();

        //THAT'S horrible way of doing that ...

        //create procedure
        Procedure p1 = new Procedure("NIVON1S", Runway.RUNWAY_01, ProcedureType.SID);
        Procedure p2 = new Procedure("LUSUL1S", Runway.RUNWAY_01, ProcedureType.SID);
        Procedure p3 = new Procedure("UDROV1S", Runway.RUNWAY_01, ProcedureType.SID);
        Procedure p4 = new Procedure("NIVON1W", Runway.RUNWAY_19, ProcedureType.SID);
        Procedure p5 = new Procedure("LUSUL1W", Runway.RUNWAY_19, ProcedureType.SID);
        Procedure p6 = new Procedure("UDROV1W", Runway.RUNWAY_19, ProcedureType.SID);
        Procedure p7 = new Procedure("IBINO1R", Runway.RUNWAY_01, ProcedureType.STAR);
        Procedure p8 = new Procedure("ARDUT1R", Runway.RUNWAY_01, ProcedureType.STAR);
        Procedure p9 = new Procedure("UDROV1R", Runway.RUNWAY_01, ProcedureType.STAR);
        Procedure p10 = new Procedure("IBINO2T", Runway.RUNWAY_19, ProcedureType.STAR);
        Procedure p11 = new Procedure("ARDUT2T", Runway.RUNWAY_19, ProcedureType.STAR);
        Procedure p12 = new Procedure("UDROV2T", Runway.RUNWAY_19, ProcedureType.STAR);


        try {
            //load fixes for procedure
            session.beginTransaction();
            List<Fix> fixList = new ArrayList<>();
            System.out.println("NIVON1S");
            fixList.add((Fix) session.createQuery("from Fix where name='DER01'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY411'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY412'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY413'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='NIVON'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p1.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p1);

            fixList = new ArrayList<>();
            System.out.println("UDROV1S");
            fixList.add((Fix) session.createQuery("from Fix where name='DER01'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY411'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY412'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY413'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY414'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='UDROV'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p2.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p2);

            fixList = new ArrayList<>();
            System.out.println("LUSUL1S");
            fixList.add((Fix) session.createQuery("from Fix where name='DER01'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY411'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY416'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY417'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='NIVON'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p3.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p3);

            fixList = new ArrayList<>();
            System.out.println("NIVON1W");
            fixList.add((Fix) session.createQuery("from Fix where name='DER19'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY511'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY512'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY513'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='NIVON'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p4.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p4);

            fixList = new ArrayList<>();
            System.out.println("UDROV1W");
            fixList.add((Fix) session.createQuery("from Fix where name='DER19'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY511'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY512'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY514'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY517'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='UDROV'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p5.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p5);

            fixList = new ArrayList<>();
            System.out.println("LUSUL1W");
            fixList.add((Fix) session.createQuery("from Fix where name='DER19'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY511'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY512'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY514'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY516'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='LUSUL'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p6.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p6);

            fixList = new ArrayList<>();
            System.out.println("IBINO1R");
            fixList.add((Fix) session.createQuery("from Fix where name='IBINO'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY361'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY366'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p7.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p7);

            fixList = new ArrayList<>();
            System.out.println("ARDUT1R");
            fixList.add((Fix) session.createQuery("from Fix where name='ARDUT'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY362'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY357'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY364'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY366'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p8.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p8);

            fixList = new ArrayList<>();
            System.out.println("UDROV1R");
            fixList.add((Fix) session.createQuery("from Fix where name='UDROV'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY363'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY364'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY366'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p9.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p9);

            fixList = new ArrayList<>();
            System.out.println("IBINO2T");
            fixList.add((Fix) session.createQuery("from Fix where name='IBINO'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY791'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY792'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY801'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY802'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p10.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p10);

            fixList = new ArrayList<>();
            System.out.println("ARDUT2T");
            fixList.add((Fix) session.createQuery("from Fix where name='ARDUT'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY793'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY801'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY802'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p11.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p11);

            fixList = new ArrayList<>();
            System.out.println("UDROV2T");
            fixList.add((Fix) session.createQuery("from Fix where name='UDROV'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY792'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY801'").getSingleResult());
            fixList.add((Fix) session.createQuery("from Fix where name='SY802'").getSingleResult());
            System.out.println(fixList);
            System.out.println("adding fixes to procedure");
            p12.setFixList(fixList);
            System.out.println("saving fixlist");
            session.save(p12);


            session.getTransaction().commit();


            //save objects
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }


    }

}
