package ra.edu;

import ra.edu.business.model.User;
import ra.edu.business.service.user.UserService;
import ra.edu.business.service.user.UserServiceImp;
import ra.edu.presentation.CustomerUI;
import ra.edu.presentation.InvoiceUI;
import ra.edu.presentation.ProductUI;
import ra.edu.utils.color.Color;
import ra.edu.utils.color.Print.PrintError;
import ra.edu.utils.color.Print.PrintSuccess;
import ra.edu.utils.color.Print.printColor.PrintColor;
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
            System.out.println("| "+Color.BRIGHT_GREEN+"1. Quản lý sản phẩm"+Color.RESET+"               |");
            System.out.println("| "+Color.BRIGHT_BLUE+"2. Quản lý khách hàng"+Color.RESET+"             |");
            System.out.println("| "+Color.BRIGHT_PURPLE+"3. Quản lý đơn hàng"+Color.RESET+"               |");
            System.out.println("| "+Color.BRIGHT_RED+"4. Đăng xuất"+Color.RESET+"                      |");
            System.out.println("| "+Color.BRIGHT_YELLOW+"5. Thoát"+Color.RESET+"                          |");
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

//    public static void main(String[] args) {
//            // In các màu cơ bản
//            System.out.println(Color.BLACK + "Đây là màu đen" + Color.RESET);
//            System.out.println(Color.RED + "Đây là màu đỏ" + Color.RESET);
//            System.out.println(Color.GREEN + "Đây là màu xanh lá" + Color.RESET);
//            System.out.println(Color.YELLOW + "Đây là màu vàng" + Color.RESET);
//            System.out.println(Color.BLUE + "Đây là màu xanh biển" + Color.RESET);
//            System.out.println(Color.PURPLE + "Đây là màu tím" + Color.RESET);
//            System.out.println(Color.CYAN + "Đây là màu cyan" + Color.RESET);
//            System.out.println(Color.WHITE + "Đây là màu trắng" + Color.RESET);
//
//            // In các màu sáng
//            System.out.println("\n"+Color.BRIGHT_BLACK + "Đây là màu đen sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_RED + "Đây là màu đỏ sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_GREEN + "Đây là màu xanh lá sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_YELLOW + "Đây là màu vàng sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_BLUE + "Đây là màu xanh biển sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_PURPLE + "Đây là màu tím sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_CYAN + "Đây là màu cyan sáng" + Color.RESET);
//            System.out.println(Color.BRIGHT_WHITE + "Đây là màu trắng sáng" + Color.RESET);
//    }
}