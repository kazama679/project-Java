package ra.edu.business.service;

import ra.edu.business.model.Customer;

import java.util.List;

public interface AppService<T> {
    List<T> findAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
    List<T> paginateList(int offset, int rowCount);
    int countList();
}
