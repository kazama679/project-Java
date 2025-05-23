package ra.edu.presentation;

import ra.edu.business.model.Product;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.utils.color.Color;
import ra.edu.utils.color.Print.PrintError;
import ra.edu.utils.color.Print.PrintSuccess;
import ra.edu.utils.color.Print.printColor.PrintColor;
import ra.edu.validate.ProductValidator;
import ra.edu.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductUI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GRAY = "\u001B[90m";
    private static final ProductService productService = new ProductServiceImp();

    public static void menuProduct(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU PRODUCT==============\n" +
                                "| "+Color.BRIGHT_GREEN+"1. In danh sách sản phẩm"+Color.RESET+"               |\n" +
                                "| "+Color.BRIGHT_GREEN+"2. Thêm sản phẩm"+Color.RESET+"                       |\n" +
                                "| "+Color.BRIGHT_GREEN+"3. Sửa sản phẩm"+Color.RESET+"                        |\n" +
                                "| "+Color.BRIGHT_GREEN+"4. Xóa sản phẩm"+Color.RESET+"                        |\n" +
                                "| "+Color.BRIGHT_GREEN+"5. Tìm kiếm điện thoại"+Color.RESET+"                 |\n" +
                                "| "+Color.BRIGHT_GREEN+"6. Quay về menu chính"+Color.RESET+"                  |\n" +
                                "==========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    displayProduct(scanner);
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
                    PrintColor.printYellow("Thoát MENU PRODUCT!");
                    break;
                default:
                    PrintError.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }

    public static void displayProduct(Scanner scanner) {
        int offset = 0, rowCount = 5, page = productService.countList();
        int numberPage = (int) Math.ceil((double) page / rowCount);
        while(true){
            int currentPage = offset / rowCount + 1;
            List<Product> products = productService.paginateList(offset, rowCount);
            displayProductSearch(products);
            if (offset == 0) {
                System.out.printf(ANSI_GRAY +"<- Trang trước"+ ANSI_RESET+"                        %d/%d                       Trang sau ->\n", currentPage, numberPage);
            }else if(offset + rowCount >= page){
                System.out.printf("<- Trang trước                        %d/%d                       "+ANSI_GRAY+"Trang sau ->\n"+ ANSI_RESET, currentPage, numberPage);
                System.out.println("\n1. Lùi về trang trước");
            }else{
                System.out.printf("<- Trang trước                        %d/%d                       Trang sau ->\n", currentPage, numberPage);
                System.out.println("\n1. Lùi về trang trước");
            }
            if (offset + rowCount < page) {
                System.out.println("2. Tiến về trang sau");
            }
            System.out.println("3. Chọn trang muốn xem");
            System.out.println("4. Thoát");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    if(offset==0){
                        PrintError.println("Không thể lùi về trang trước vì đang là trang đầu tiên!");
                    }else{
                        offset = offset-rowCount;
                    }
                    break;
                case 2:
                    if (offset + rowCount >= page) {
                        PrintError.println("Không thể tiến về trang sau vì đang là trang cuối cùng!");
                    } else {
                        offset += rowCount;
                    }
                    break;
                case 3:
                    System.out.println("Nhập vào trang muốn xem");
                    int n = Validator.ValidIntPage(scanner, 0, numberPage+1);
                    offset = n*5-5;
                    break;
                case 4:
                    PrintColor.printYellow("Thoát menu hiển thị danh sách sản phẩm!");
                    return;
                default:
                    PrintError.println("Vui lòng chọn đúng!");
            }
        }
    }

    public static void addProduct(Scanner scanner){
        System.out.println("Nhập vào số lượng sản phẩm muốn thêm: ");
        int n = Validator.ValidInt(scanner, 0);
        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.inputData(scanner);
            boolean check = productService.add(product);
            List<Product> products = productService.findAll();
            if(check){
                PrintSuccess.println("Thêm sản phẩm thành công!");
                List<Product> displayProductAdd = new ArrayList<>();
                displayProductAdd.add(products.getLast());
                displayProductSearch(displayProductAdd);
            }else{
                PrintError.println("Có lỗi trong quá trình thêm mới!");
            }
        }
    }

    public static void updateProduct(Scanner scanner) {
        displayProduct(scanner);
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
                        product.setName(ProductValidator.validCheckSomeName(scanner, n));
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
                        PrintColor.printYellow("Thoát menu sửa sản phẩm!");
                        break;
                    default:
                        PrintError.println("Vui lòng chọn từ 1-5!");
                }
            } while (choice != 5);
            boolean check = productService.update(product);
            if (check) {
                PrintSuccess.println("Sửa sản phẩm thành công!");
                List<Product> displayProductUpdate = new ArrayList<>();
                displayProductUpdate.add(productService.findById(product.getProductId()));
                displayProductSearch(displayProductUpdate);
            } else {
                PrintError.println("Có lỗi trong quá trình sửa!");
            }
        } else {
            PrintError.println("Id sản phẩm không tồn tại!");
        }
    }

    public static void deleteProduct(Scanner scanner){
        displayProduct(scanner);
        System.out.println("==========================================");
        System.out.println("Nhập vào id sản phẩm cần xóa: ");
        int n = Validator.ValidInt(scanner, 0);
        if(productService.findById(n) != null){
            System.out.println("Bạn có chắc chắn muốn xóa:");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Validator.ValidInt(scanner, 0);
            if(choice == 1){
                Product product = new Product();
                product.setProductId(n);
                boolean check = productService.delete(product);
                if(check){
                    PrintSuccess.println("Đã xóa thành công sản phẩm có id là: " + n+"!");
                    System.out.println("Bạn có muốn xem danh sách sản phẩm luôn không? (Nhập 1):");
                    int c = Validator.ValidInt(scanner,0);
                    if(c==1){
                        displayProduct(scanner);
                        return;
                    }
                    return;
                }
                PrintError.println("Có lỗi trong quá trình xóa sản phẩm!");
                return;
            }
            PrintSuccess.println("Đã hủy quá trình xóa!");
            return;
        }
        PrintError.println("Id sản phẩm không tồn tại!");
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
                    PrintColor.printYellow("Thoát menu tìm kiếm sản phẩm!");
                    return;
                default:
                    PrintError.println("Vui lòng nhập từ 1-4!");
            }
        }
    }

    public static void searchByBrand(Scanner scanner){
        System.out.println("Nhập vào tên nhãn hàng cần tìm: ");
        String brand = Validator.ValidString(scanner,0,50);
        List<Product> listProducts = productService.searchByBrand(brand);
        if(listProducts == null ||listProducts.isEmpty()){
            PrintError.println("Không tìm thấy nhãn hàng nào có tên là: "+brand+"!");
            return;
        }
        displayProductSearch(listProducts);
    }

    public static void searchByPrice(Scanner scanner){
        System.out.println("Nhập vào số tiền bắt đầu tìm kiếm: ");
        double start = Validator.ValidDouble(scanner, -1);
        System.out.println("Nhập vào số tiền bắt kết thúc kiếm: ");
        double end;
        while(true){
            end = Validator.ValidDouble(scanner, -1);
            if(end >= start){
                break;
            }
            PrintError.println("Phải lớn hơn hoặc bằng số ban đầu!");
        }
        List<Product> listProducts = productService.searchByPrice(start, end);
        if(listProducts == null ||listProducts.isEmpty()){
            PrintError.println("Danh sách sản phẩm rỗng!");
            return;
        }
        displayProductSearch(listProducts);
    }

    public static void searchByStock(Scanner scanner){
        System.out.println("Nhập vào số lượng tồn kho bắt đầu tìm kiếm: ");
        int start = Validator.ValidInt(scanner, -1);
        System.out.println("Nhập vào số lượng tồn kho bắt kết thúc kiếm: ");
        int end;
        while(true){
            end = Validator.ValidInt(scanner, -1);
            if(end > start){
                break;
            }
            PrintError.println("Phải lớn hơn số ban đầu!");
        }
        List<Product> listProducts = productService.searchByStock(start, end);
        if(listProducts == null ||listProducts.isEmpty()){
            PrintError.println("Danh sách sản phẩm rỗng!");
            return;
        }
        displayProductSearch(listProducts);
    }

    public static void displayProductSearch(List<Product> listProducts) {
        System.out.println("+----+-----------------------------------------------+------------------------+-------------------+------------+");
        System.out.println("| ID |                 Tên sản phẩm                  |        Nhãn hàng       |     Giá bán       |  Số lượng  |");
        System.out.println("+----+-----------------------------------------------+------------------------+-------------------+------------+");
        listProducts.forEach(System.out::println);
        System.out.println("+----+-----------------------------------------------+------------------------+-------------------+------------+");
    }
}