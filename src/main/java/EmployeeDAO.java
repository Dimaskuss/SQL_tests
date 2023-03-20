import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);

    Employee readById(int id);

    List<Employee> readALL();

    void updateEmployeeById(int id, String firstName, String lastName, String gender, int age, int city_id);

    void deleteById(int id);

}
