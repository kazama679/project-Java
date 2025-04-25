package ra.edu.business.dao.product;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Product;
import ra.edu.utils.Print.PrintError;
import ra.edu.validate.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAOImp implements ProductDAO {
    @Override
    public Product findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public Product findByIdAll(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_id_all(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_by_brand(?)}");
            callSt.setString(1, brand);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<Product>();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public List<Product> searchByPrice(double start, double end) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_by_price(?,?)}");
            callSt.setDouble(1, start);
            callSt.setDouble(2, end);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<Product>();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public List<Product> searchByStock(int start, int end) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_by_stock(?,?)}");
            callSt.setInt(1, start);
            callSt.setInt(2, end);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<>();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public List<Product> paginateProducts(int offset, int rowCount) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call paginate_products(?,?)}");
            callSt.setInt(1, offset);
            callSt.setInt(2, rowCount);
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<Product>();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public int countProducts() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_products()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
    }

    @Override
    public List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProducts = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call display_product()}");
            ResultSet rs = callSt.executeQuery();
            listProducts = new ArrayList<Product>();
            while(rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                listProducts.add(product);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProducts;
    }

    @Override
    public boolean add(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_product(?,?,?,?)}");
            callSt.setString(1, product.getName());
            callSt.setString(2, product.getBrand());
            callSt.setDouble(3,product.getPrice());
            callSt.setInt(4,product.getStock());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?)}");
            callSt.setInt(1,product.getProductId());
            callSt.setString(2, product.getName());
            callSt.setString(3, product.getBrand());
            callSt.setDouble(4,product.getPrice());
            callSt.setInt(5,product.getStock());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, product.getProductId());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy productDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy productDAOImp!");
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}