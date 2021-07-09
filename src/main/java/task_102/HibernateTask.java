package task_102;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import task_102.entity.*;


import java.util.List;
import java.util.Set;

public class HibernateTask {


    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());
        System.out.println(course.getTeacher().getName());

        List<Student> studentList = course.getStudents();
        studentList.forEach(System.out::println);


        //задание 1
        List<Teacher> teachers = session.createQuery("from Teacher " + "WHERE salary > 20000 AND age > 30").getResultList();
        for (Teacher t : teachers)
            System.out.println(t);

        //задание 2
        Student student = session.get(Student.class, 2);
        List<Course> courseList = student.getCourseList();
        courseList.forEach(System.out::println);

        List<Purchaselist> purchaselist = session.createQuery("from Purchaselist " + "WHERE  student_name = 'Дутов Александр'").getResultList();
        purchaselist.forEach(System.out::println);


        List<Subscriptions> subscriptions = session.createQuery("from Subscriptions " + "WHERE student_id = 3").getResultList();
        for (Subscriptions s : subscriptions)
            System.out.println(s.toString());


//задание 3 создание класса

        String hqlPurchaselist =
                "From " + Purchaselist.class.getSimpleName();//выборка всей таблицы
        List<Purchaselist> purchaselists = session.createQuery(hqlPurchaselist).getResultList();

//        purchaselists.forEach(s->{session.save(new LinkedPurchaseList(new LinkedPurchaseList
//               .Id(s.getStudent().getId(), s.getCourse().getId()), s.getStudent().getId(), s.getCourse().getId()));});
        for (Purchaselist pl : purchaselists) {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(new LinkedPurchaseList
                    .Id(pl.getStudent().getId(), pl.getCourse().getId()), pl.getStudent().getId(), pl.getCourse().getId());

            session.save(linkedPurchaseList);
        }

        transaction.commit();
        sessionFactory.close();
    }

}
