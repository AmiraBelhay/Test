package services.shop;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO<T> {
    void create(T product) throws SQLException;
    List<T> readList() throws SQLException;
    T findById(int id);
    void update(T product);
    void delete(int id) throws SQLException;
}
