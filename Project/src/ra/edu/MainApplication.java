package ra.edu;

import ra.edu.business.model.User;
import ra.edu.business.service.user.UserService;
import ra.edu.business.service.user.UserServiceImp;
import ra.edu.presentation.CustomerUI;
import ra.edu.presentation.InvoiceUI;
import ra.edu.presentation.ProductUI;
import ra.edu.utils.Print.PrintError;
import ra.edu.utils.Print.PrintSuccess;
import ra.edu.utils.Print.printColor.PrintColor;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

import static ra.edu.validate.Validator.*;

public class MainApplication {
    private static final UserService userService = new UserServiceImp();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<User> listUsers = userService.findAll();
        if(listUsers.stream().anyMatch(u->u.getStatus() && u.getId()==1)){
            menu(sc);
        }
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
                    PrintColor.printYellow("Dừng chương trình!");
                    System.exit(0);
                default:
                    PrintError.println("Vui lòng chọn từ 1-2!");
            }
        }while(true);
    }

    public static void login(Scanner sc){
        List<User> listUsers = userService.findAll();
        System.out.println("==============ĐĂNG NHẬP QUẢN TRỊ===============");
        while(true){
            System.out.println("Nhập tài khoản: ");
            String email = Validator.ValidString(sc, 0,255);
            System.out.println("Nhập mật khẩu: ");
            String password = Validator.ValidString(sc, 0,255);
            if(listUsers.stream().anyMatch(user->user.getAccount().equals(email) && user.getPassword().equals(password))){
                PrintSuccess.println("Đăng nhập thành công, xin chào "+email+"!");
                User user = userService.findById(1);
                if (user != null) {
                    user.setStatus(true);
                    boolean check = userService.update(user);
                }
                menu(sc);
            }else{
                PrintError.println("Tài khoản hoặc mật khẩu không chính xác, vui lòng nhập lại!");
            }
        }
    }

    public static void menu(Scanner sc){
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
                    PrintSuccess.println("Đăng xuất thành công!");
                    User user = userService.findById(1);
                    if (user != null) {
                        user.setStatus(false);
                        boolean check = userService.update(user);
                    }
                    break;
                case 5:
                    PrintColor.printYellow("Thoát chương trình!");
                    System.exit(0);
                default:
                    PrintError.println("Vui lòng nhập từ 1-5!");
            }
        }while(choice!=4);
    }
}