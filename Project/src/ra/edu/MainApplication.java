package ra.edu;

import ra.edu.business.model.Product;
import ra.edu.business.model.User;
import ra.edu.business.service.user.UserService;
import ra.edu.business.service.user.UserServiceImp;
import ra.edu.presentation.CustomerUI;
import ra.edu.presentation.InvoiceUI;
import ra.edu.presentation.ProductUI;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

import static ra.edu.validate.Validator.*;

public class MainApplication {
    private static final UserService userService = new UserServiceImp();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("==============HỆ THỐNG QUẢN LÝ CỬA HÀNG==============");
            System.out.println("1. Đăng nhập Admin");
            System.out.println("2. Thoát");
            System.out.println("=====================================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = ValidInt(sc, 0);
            switch (choice) {
                case 1:
                    login(sc);
                    break;
                case 2:
                    System.out.println("Dừng chương trình!");
                    System.exit(0);
                default:
                    System.out.println("Vui lòng chọn từ 1-2!");
            }
        }while(true);
    }

    public static void login(Scanner sc){
        List<User> listUsers = userService.findAll();
        System.out.println("==============ĐĂNG NHẬP QUẢN TRỊ===============");
        while(true){
            System.out.print("Nhập tài khoản: ");
            String email = Validator.ValidString(sc, 0,255);
            System.out.print("Nhập mật khẩu: ");
            String password = Validator.ValidString(sc, 0,255);
            if(listUsers.stream().anyMatch(user->user.getAccount().equals(email) && user.getPassword().equals(password))){
                System.out.println("Đăng nhập thành công!\n");
                int choice;
                do {
                    System.out.println("==============MENU CHÍNH=============");
                    System.out.println("1. Quản lý sản phẩm");
                    System.out.println("2. Quản lý khách hàng");
                    System.out.println("3. Quản lý đơn hàng");
                    System.out.println("4. Đăng xuất");
                    System.out.println("5. Thoát");
                    System.out.println("=====================================");
                    System.out.println("Lựa chọn của bạn: ");
                    choice= ValidInt(sc,0);
                    switch(choice){
                        case 1:
                            ProductUI.menuProduct(sc);
                            break;
                        case 2:
                            CustomerUI.menuCustomer(sc);
                            break;
                        case 3:
                            InvoiceUI.menuInvoice(sc);
                            break;
                        case 4:
                            System.out.println("Đăng xuất thành công!");
                            break;
                        case 5:
                            System.out.println("Thoát chương trình!");
                            System.exit(0);
                        default:
                            System.out.println("Vui lòng nhập từ 1-5!");
                    }
                }while(choice!=4);
            }else{
                System.err.println("Tài khoản hoặc mật khẩu không chính xác, vui lòng nhập lại!");
            }
        }
    }
}