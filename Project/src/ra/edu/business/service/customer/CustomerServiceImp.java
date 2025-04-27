package ra.edu.business.service.customer;

import ra.edu.business.dao.customer.CustomerDAO;
import ra.edu.business.dao.customer.CustomerDAOImp;
import ra.edu.business.model.Customer;

import java.util.List;

public class CustomerServiceImp implements CustomerService {
    private final CustomerDAO customerDAO;
    public CustomerServiceImp() {
        customerDAO = new CustomerDAOImp();
    }

    @Override
    public Customer findById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    public Customer findByIdAll(int id) {
        return customerDAO.findByIdAll(id);
    }

    @Override
    public List<Customer> searchByName(String name) {
        return customerDAO.searchByName(name);
    }

    @Override
    public List<Customer> paginateList(int offset, int rowCount) {
        return customerDAO.paginateList(offset, rowCount);
    }

    @Override
    public int countList() {
        return customerDAO.countList();
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public boolean add(Customer customer) {
        return customerDAO.add(customer);
    }

    @Override
    public boolean update(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public boolean delete(Customer customer) {
        return customerDAO.delete(customer);
    }
}
