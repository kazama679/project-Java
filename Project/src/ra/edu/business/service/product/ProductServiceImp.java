package ra.edu.business.service.product;

import ra.edu.business.dao.product.ProductDAO;
import ra.edu.business.dao.product.ProductDAOImp;
import ra.edu.business.model.Product;

import java.util.List;

public class ProductServiceImp implements ProductService{
    private final ProductDAO productDAO;
    public ProductServiceImp() {
        productDAO = new ProductDAOImp();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Product findByIdAll(int id) {
        return productDAO.findByIdAll(id);
    }

    @Override
    public List<Product> searchByBrand(String brand) {
        return productDAO.searchByBrand(brand);
    }

    @Override
    public List<Product> searchByPrice(double start, double end) {
        return productDAO.searchByPrice(start, end);
    }

    @Override
    public List<Product> searchByStock(int start, int end) {
        return productDAO.searchByStock(start, end);
    }

    @Override
    public List<Product> paginateList(int offset, int rowCount) {
        return productDAO.paginateList(offset, rowCount);
    }

    @Override
    public int countList() {
        return productDAO.countList();
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public boolean add(Product product) {
        return productDAO.add(product);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public boolean delete(Product product) {
        return productDAO.delete(product);
    }
}