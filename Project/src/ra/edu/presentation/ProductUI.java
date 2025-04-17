package ra.edu.presentation;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.PhoneValidator;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class ProductUI {
    private static final ProductService productService = new ProductServiceImp();

    public static void menuProduct(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU PRODUCT==============");
            System.out.println("1. In danh sách sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Sửa sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm điện thoại");
            System.out.println("6. Quay về menu chính");
            System.out.println("==========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    displayProduct();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    searchProduct(scanner);
                    break;
                case 6:
                    System.out.println("Thoát MENU PRODUCT!");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }

    public static void displayProduct(){
        List<Product> listProducts = productService.findAll();
        if(listProducts == null ||listProducts.isEmpty()){
            System.out.println("Danh sách sản phẩm rỗng!");
            return;
        }
        listProducts.forEach(System.out::println);
    }

    public static void addProduct(Scanner scanner){
        System.out.println("Nhập vào số lương sản phẩm muốn thêm: ");
        int n = Validator.ValidInt(scanner, 0);
        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.inputData(scanner);
            boolean check = productService.add(product);
            if(check){
                System.out.println("Thêm sản phẩm thành công!");
            }else{
                System.out.println("Có lỗi trong quá trình thêm mới!");
            }
        }
        displayProduct();
    }

    public static void updateProduct(Scanner scanner) {
        displayProduct();
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("Nhập vào id sản phẩm muốn sửa: ");
        int n = Validator.ValidInt(scanner, 0);
        Product product = productService.findById(n);
        if (product != null) {
            int choice;
            do {
                System.out.println("==========MENU SỬA SẢN PHẨM============");
                System.out.println("1. Sửa tên sản phẩm");
                System.out.println("2. Sửa nhãn hàng sản phẩm");
                System.out.println("3. Sửa giá sản phẩm");
                System.out.println("4. Sửa số lượng sản phẩm");
                System.out.println("5. Thoát menu sửa sản phẩm");
                System.out.println("+++++++++++++++++++++++++++++++++++");
                System.out.print("Lựa chọn sửa của bạn: ");
                choice = Validator.ValidInt(scanner, 0);
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên sản phẩm mới: ");
                        product.setName(PhoneValidator.validCheckSomeName(scanner, n));
                        break;
                    case 2:
                        System.out.println("Nhập vào nhãn hàng mới: ");
                        product.setBrand(Validator.ValidString(scanner, -1, 50));
                        break;
                    case 3:
                        System.out.println("Nhập vào giá sản phẩm mới: ");
                        product.setPrice(Validator.ValidDouble(scanner, 0));
                        break;
                    case 4:
                        System.out.println("Nhập vào số lượng sản phẩm mới: ");
                        product.setStock(Validator.ValidInt(scanner, 0));
                        break;
                    case 5:
                        System.out.println("Thoát menu sửa sản phẩm!");
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-5!");
                }
            } while (choice != 5);
            boolean check = productService.update(product);
            if (check) {
                System.out.println("Sửa sản phẩm thành công!");
                displayProduct();
            } else {
                System.out.println("Có lỗi trong quá trình sửa!");
            }
        } else {
            System.out.println("Id sản phẩm không tồn tại!");
        }
    }

    public static void deleteProduct(Scanner scanner){
        displayProduct();
        System.out.println("==========================================");
        System.out.println("Nhập vào id sản phẩm cần xóa: ");
        int n = Validator.ValidInt(scanner, 0);
        if(productService.findById(n) != null){
            Product product = new Product();
            product.setProductId(n);
            boolean check = productService.delete(product);
            if(check){
                System.out.println("Xóa sản phẩm thành công!");
                displayProduct();
                return;
            }
            System.out.println("Có lỗi trong quá trình xóa sản phẩm!");
            return;
        }
        System.out.println("Id sản phẩm không tồn tại!");
    }

    public static void searchProduct(Scanner scanner){
        while(true){
            System.out.println("======MENU TÌM KIẾM SẢN PHẨM=======");
            System.out.println("1. Tìm kiếm điện thoại theo nhãn hàng");
            System.out.println("2. Tìm kiếm điện thoại theo khoảng giá");
            System.out.println("3. Tìm kiếm điện thoại theo số lượng tồn kho");
            System.out.println("4. Thoát menu tìm kiếm sản phẩm");
            System.out.println("===================================");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    searchByBrand(scanner);
                    break;
                case 2:
                    searchByPrice(scanner);
                    break;
                case 3:
                    searchByStock(scanner);
                    break;
                case 4:
                    System.out.println("Thoát menu tìm kiếm sản phẩm!");
                    return;
                default:
                    System.err.println("Vui lòng nhập từ 1-4!");
            }
        }
    }

    public static void searchByBrand(Scanner scanner){
        System.out.println("Nhập vào tên nhãn hàng cần tìm: ");
        String brand = Validator.ValidString(scanner,0,50);
        List<Product> listProducts = productService.searchByBrand(brand);
        if(listProducts == null ||listProducts.isEmpty()){
            System.err.println("Danh sách sản phẩm rỗng!");
            return;
        }
        listProducts.forEach(System.out::println);
    }

    public static void searchByPrice(Scanner scanner){
        System.out.println("Nhập vào số tiền bắt đầu tìm kiếm: ");
        double start = Validator.ValidDouble(scanner, -1);
        System.out.println("Nhập vào số tiền bắt kết thúc kiếm: ");
        double end = Validator.ValidDouble(scanner, -1);
        List<Product> listProducts = productService.searchByPrice(start, end);
        if(listProducts == null ||listProducts.isEmpty()){
            System.err.println("Danh sách sản phẩm rỗng!");
            return;
        }
        listProducts.forEach(System.out::println);
    }

    public static void searchByStock(Scanner scanner){
        System.out.println("Nhập vào số lượng tồn kho bắt đầu tìm kiếm: ");
        int start = Validator.ValidInt(scanner, -1);
        System.out.println("Nhập vào số lượng tồn kho bắt kết thúc kiếm: ");
        int end = Validator.ValidInt(scanner, -1);
        List<Product> listProducts = productService.searchByStock(start, end);
        if(listProducts == null ||listProducts.isEmpty()){
            System.err.println("Danh sách sản phẩm rỗng!");
            return;
        }
        listProducts.forEach(System.out::println);
    }
}