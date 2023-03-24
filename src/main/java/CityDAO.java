import java.util.List;

public interface CityDAO {
    void create(City city);

    City readById(int id);

    List<City> readALL();

    void updateEmployee(City city);

    void delete(City city);
}
