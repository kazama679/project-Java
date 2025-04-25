package ra.edu.business.dao.customer;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Customer;

import java.util.List;

public interface CustomerDAO extends AppDAO<Customer> {
    Customer findById(int id);
    List<Customer> searchByName(String name);
    List<Customer> paginateCustomer(int offset, int rowCount);
    int countCustomer();
}