package ra.edu.business.dao.customer;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Customer;

public interface CustomerDAO extends AppDAO<Customer> {
    Customer findById(int id);
}