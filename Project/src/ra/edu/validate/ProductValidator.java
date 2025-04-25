package ra.edu.validate;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;

import java.util.Scanner;

public class ProductValidator {
    private static final ProductService productService = new ProductServiceImp();

    public static int checkIdIsExist(Scanner sc) {
        while(true){
            int id = Validator.ValidInt(sc, 0);
            Product product = productService.findById(id);
            if (product != null) {
                return id;
            }
            System.err.println("Id sản phẩm không tồn tại, vui lòng nhập lại!");
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
                System.err.println("Số lượng trong kho không đủ, vui lòng nhập lại!");
                continue;
            }
            System.err.println("Không tìm thấy sản phẩm");
        }
    }
}
