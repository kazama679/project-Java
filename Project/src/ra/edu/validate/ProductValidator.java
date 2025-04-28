package ra.edu.validate;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.utils.color.Print.PrintError;

import java.util.List;
import java.util.Scanner;

public class ProductValidator {
    private static final ProductService productService = new ProductServiceImp();

    public static String validCheckSomeName(Scanner sc, int id) {
        while (true) {
            String name = Validator.ValidString(sc, 0, 100);
            List<Product> listProducts = productService.findAll();
            boolean isDuplicate = listProducts.stream().anyMatch(product -> normalize(product.getName()).equals(normalize(name)) && product.getProductId() != id);
            if (isDuplicate) {
                PrintError.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại!");
            } else {
                return name;
            }
        }
    }

    private static String normalize(String input) {
        return input.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    public static int checkIdIsExist(Scanner sc) {
        while(true){
            int id = Validator.ValidInt(sc, 0);
            Product product = productService.findById(id);
            if (product != null) {
                return id;
            }
            PrintError.println("Id sản phẩm không tồn tại, vui lòng nhập lại!");
        }
    }

    public static int checkQuantity(Scanner sc, int id) {
        while (true){
            int n = Validator.ValidInt(sc, 0);
            Product product = productService.findById(id);
            if (product != null) {
                if(product.getStock()>=n){
                    return n;
                }
                PrintError.println("Số lượng trong kho không đủ, vui lòng nhập lại!");
                continue;
            }
            PrintError.println("Không tìm thấy sản phẩm");
        }
    }
}
