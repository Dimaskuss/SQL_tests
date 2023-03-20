
import org.hibernate.stat.internal.StatisticsImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {


    private static Connection getConnection() {
        final String user = "postgres";
        final String password = "1991";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void execute(String sql) throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }


    @Override
    public void create(Employee employee) {


        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (id , first_name , last_name , gender , age , city_id) " +
                        "VALUES ((?),(?),(?),(?),(?),(?))")) {


            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setInt(6, employee.getCity().getCityId());


            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee readById(int id) {

        Employee employee = new Employee();

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND id=(?)")) {


            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {


                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCity(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;

    }

    @Override
    public List<Employee> readALL() {

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                City city = new City(resultSet.getInt("city_id"), resultSet.getString("city_name"));


                employeeList.add(new Employee(id, firstName, lastName, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;

    }

    @Override
    public void updateEmployeeById(int id, String firstName, String lastName, String gender, int age, int cityId) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET first_name=(?),last_name=(?),gender=(?),age=(?),city_id=(?) WHERE id=(?)")) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, cityId);
            statement.setInt(6, id);


            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
