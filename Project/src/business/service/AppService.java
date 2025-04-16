package business.service;

import java.util.List;

public interface AppService<T> {
    List<T> findAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}
