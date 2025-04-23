package ra.edu.business.dao.product;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Product;

import java.util.List;

public interface ProductDAO extends AppDAO<Product> {
    Product findById(int id);
    List<Product> searchByBrand(String brand);
    List<Product> searchByPrice(double start, double end);
    List<Product> searchByStock(int start, int end);
}