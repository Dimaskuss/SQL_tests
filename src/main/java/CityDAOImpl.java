import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {
    @Override
    public void create(City city) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }

    }

    @Override
    public City readById(int id) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }


    }

    @Override
    public List<City> readALL() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {

            List<City> cityList = session.createQuery("FROM City", City.class).list();
            return cityList;
        }
    }

    @Override
    public void updateEmployee(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}

