package ra.edu.validate;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.presentation.ProductUI;
import ra.edu.utils.Print.PrintError;

import java.util.List;
import java.util.Scanner;

public class PhoneValidator {
    private static final ProductService productService = new ProductServiceImp();

    public static String validCheckSomeName(Scanner sc, int id) {
        while (true) {
            String name = Validator.ValidString(sc, 0, 100);
            List<Product> listProducts = productService.findAll();
            boolean isDuplicate = listProducts.stream().anyMatch(product -> product.getName().equalsIgnoreCase(name) && product.getProductId() != id);
            if (isDuplicate) {
                PrintError.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại!");
            } else {
                return name;
            }
        }
    }
}