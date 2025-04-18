package ra.edu.business.service.product;

import ra.edu.business.model.Product;
import ra.edu.business.service.AppService;

import java.util.List;

public interface ProductService extends AppService<Product> {
    Product findById(int id);
    List<Product> searchByBrand(String brand);
    List<Product> searchByPrice(double start, double end);
    List<Product> searchByStock(int start, int end);
}