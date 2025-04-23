package ra.edu.business.service.customer;

import ra.edu.business.model.Customer;
import ra.edu.business.service.AppService;

import java.util.List;

public interface CustomerService extends AppService<Customer> {
    Customer findById(int id);
    List<Customer> searchByName(String name);
}
