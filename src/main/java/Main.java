import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = new Employee(9, "Max", "Power", "male", 45, new City(3));
//        employeeDAO.create(employee);

        System.out.println(employeeDAO.readById(2).toString());
        System.out.println(employeeDAO.readALL());
//        employeeDAO.updateEmployeeById(8,"Anjelica","Varum","female",43,6);
        employeeDAO.deleteById(9);


    }
}