
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.internal.StatisticsImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {


//    private static Connection getConnection() {
//        final String user = "postgres";
//        final String password = "1991";
//        final String url = "jdbc:postgresql://localhost:5432/skypro";
//
//        try {
//            return DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static void execute(String sql) throws SQLException {
//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement()) {
//            statement.execute(sql);
//        }
//    }


    @Override
    public void create(Employee employee) {


        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {

            Transaction transaction = session.beginTransaction();

            session.save(employee);

            transaction.commit();
        }

    }

    @Override
    public Employee readById(int id) {


        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);


    }

    @Override
    public List<Employee> readALL() {

        List<Employee> users = (List<Employee>) HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From Employee").list();
        return users;

    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}