package ir.maktab58.dao;

import java.util.List;
import java.util.Map;

/**
 * @author Taban Soleymani
 */
public interface DaoInterface<T> {
    T get(Class<T> cl, Integer id);
    int save(T object);
    int save(T object, String entityName);
    void update(T object);
    void delete(T object);
    List<T> query(String mySqlQuery, Map<String, Object> params);
}
