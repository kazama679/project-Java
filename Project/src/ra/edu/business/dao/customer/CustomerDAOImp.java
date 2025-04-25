package ra.edu.business.dao.customer;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Customer;
import ra.edu.utils.Print.PrintError;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO{
    @Override
    public Customer findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_customer_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
            }
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return customer;
    }

    @Override
    public Customer findByIdAll(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Customer customer = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_customer_by_id_all(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
            }
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return customer;
    }

    @Override
    public List<Customer> searchByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> listCustomers = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_by_name(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            listCustomers = new ArrayList<Customer>();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                listCustomers.add(customer);
            }
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCustomers;
    }

    @Override
    public List<Customer> paginateCustomer(int offset, int rowCount) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> listCustomer = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call paginate_customers(?,?)}");
            callSt.setInt(1, offset);
            callSt.setInt(2, rowCount);
            ResultSet rs = callSt.executeQuery();
            listCustomer = new ArrayList<Customer>();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                listCustomer.add(customer);
            }
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCustomer;
    }

    @Override
    public int countCustomer() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_customers()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public List<Customer> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> listCustomers= null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call display_customer()}");
            ResultSet rs = callSt.executeQuery();
            listCustomers = new ArrayList<>();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                listCustomers.add(customer);
            }
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCustomers;
    }

    @Override
    public boolean add(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_customer(?,?,?,?)}");
            callSt.setString(1, customer.getName());
            callSt.setString(2, customer.getPhone());
            callSt.setString(3,customer.getEmail());
            callSt.setString(4,customer.getAddress());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_customer(?,?,?,?,?)}");
            callSt.setInt(1,customer.getId());
            callSt.setString(2, customer.getName());
            callSt.setString(3, customer.getPhone());
            callSt.setString(4, customer.getEmail());
            callSt.setString(5, customer.getAddress());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_customer(?)}");
            callSt.setInt(1,customer.getId());
            callSt.executeUpdate();
            return true;
        }catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy customerDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy customerDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}