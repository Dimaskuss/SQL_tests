import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee = new Employee(10, "Maxim", "Power", "male", 45, 4);
//       employeeDAO.create(employee);
//        employeeDAO.updateEmployee(employee);
//        System.out.println(employeeDAO.readById(2).toString());
//        System.out.println(employeeDAO.readALL());
//
        employeeDAO.delete(employee);


    }
}