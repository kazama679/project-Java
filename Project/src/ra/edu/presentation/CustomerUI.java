package ra.edu.presentation;

import ra.edu.business.model.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.utils.color.Color;
import ra.edu.utils.color.Print.PrintError;
import ra.edu.utils.color.Print.PrintSuccess;
import ra.edu.utils.color.Print.printColor.PrintColor;
import ra.edu.validate.CustomerValidator;
import ra.edu.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private static final CustomerService customerService = new CustomerServiceImp();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GRAY = "\u001B[90m";

    public static void menuCustomer(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU CUSTOMER==============\n" +
                                "| "+Color.BRIGHT_BLUE+"1. In danh sách khách"+Color.RESET+"                   |\n" +
                                "| "+Color.BRIGHT_BLUE+"2. Thêm khách hàng"+Color.RESET+"                      |\n" +
                                "| "+Color.BRIGHT_BLUE+"3. Sửa khách hàng"+Color.RESET+"                       |\n" +
                                "| "+Color.BRIGHT_BLUE+"4. Xóa khách hàng"+Color.RESET+"                       |\n" +
                                "| "+Color.BRIGHT_BLUE+"5. Tìm kiếm khách hàng theo tên"+Color.RESET+"         |\n" +
                                "| "+Color.BRIGHT_BLUE+"6. Quay về menu chính"+Color.RESET+"                   |\n" +
                                "===========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    displayCustomer(scanner);
                    break;
                case 2:
                    addCustomer(scanner);
                    break;
                case 3:
                    updateCustomer(scanner);
                    break;
                case 4:
                    deleteCustomer(scanner);
                    break;
                case 5:
                    searchByName(scanner);
                    break;
                case 6:
                    PrintColor.printYellow("Thoát MENU CUSTOMER!");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }

    public static void displayCustomer(Scanner scanner) {
        int offset = 0, rowCount = 5, page = customerService.countList();
        int numberPage = (int) Math.ceil((double) page / rowCount);
        while(true){
            int currentPage = offset / rowCount + 1;
            List<Customer> customers = customerService.paginateList(offset, rowCount);
            displayCustomer(customers);
            if (offset == 0) {
                System.out.printf(ANSI_GRAY +"<- Trang trước"+ ANSI_RESET+"                                       %d/%d                                                    Trang sau ->\n", currentPage, numberPage);
            }else if(offset + rowCount >= page){
                System.out.printf("<- Trang trước                                       %d/%d                                                    "+ANSI_GRAY+"Trang sau ->\n"+ ANSI_RESET, currentPage, numberPage);
                System.out.println("\n1. Lùi về trang trước");
            }else{
                System.out.printf("<- Trang trước                                       %d/%d                                                    Trang sau ->\n", currentPage, numberPage);
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
                    PrintColor.printYellow("Thoát menu hiển thị danh sách khách hàng!");
                    return;
                default:
                    PrintError.println("Vui lòng chọn đúng!");
            }
        }
    }

    public static void addCustomer(Scanner scanner){
        System.out.println("Nhập vào số lượng khách hàng muốn thêm: ");
        int n = Validator.ValidInt(scanner, 0);
        for (int i = 0; i < n; i++) {
            Customer customer = new Customer();
            customer.inputData(scanner);
            boolean check = customerService.add(customer);
            if(check){
                PrintSuccess.println("Thêm sản khách hàng công!");
                System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
                System.out.println("| ID |         Tên khách hàng         | Số điện thoại |             Email              |              Địa chỉ           |");
                System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
                List<Customer> customers = customerService.findAll();
                System.out.println(customers.getLast().toString());
                System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
            }else{
                PrintError.println("Có lỗi trong quá trình thêm mới!");
            }
        }
    }

    public static void updateCustomer(Scanner scanner) {
        displayCustomer(scanner);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        System.out.println("Nhập vào id khách hàng muốn sửa: ");
        int n = Validator.ValidInt(scanner, 0);
        Customer customer = customerService.findById(n);
        if (customer != null) {
            int choice;
            do {
                System.out.println("==========MENU SỬA KHÁCH HÀNG============");
                System.out.println("1. Sửa tên khách hàng");
                System.out.println("2. Sửa số điện thoại khách hàng");
                System.out.println("3. Sửa email khách hàng");
                System.out.println("4. Sửa địa chỉ khách hàng");
                System.out.println("5. Thoát menu sửa khách hàng");
                System.out.println("+++++++++++++++++++++++++++++++++++");
                System.out.print("Lựa chọn sửa của bạn: ");
                choice = Validator.ValidInt(scanner, 0);
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên khách hàng mới: ");
                        customer.setName(Validator.ValidString(scanner, 0,100));
                        break;
                    case 2:
                        System.out.println("Nhập vào số điện thoại mới: ");
                        customer.setPhone(CustomerValidator.validCheckSomePhone(scanner, n));
                        break;
                    case 3:
                        System.out.println("Nhập vào email mới: ");
                        customer.setEmail(CustomerValidator.validCheckSomeEmail(scanner, n));
                        break;
                    case 4:
                        System.out.println("Nhập vào địa chỉ mới: ");
                        customer.setAddress(Validator.ValidString(scanner, 0,100));
                        break;
                    case 5:
                        PrintColor.printYellow("Thoát menu sửa sản phẩm!");
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-5!");
                }
            } while (choice != 5);
            boolean check = customerService.update(customer);
            if (check) {
                PrintSuccess.println("Sửa khách hàng thành công!");
                List<Customer> displayCustomerUpdate = new ArrayList<>();
                displayCustomerUpdate.add(customerService.findById(n));
                displayCustomer(displayCustomerUpdate);
            } else {
                PrintError.println("Có lỗi trong quá trình sửa!");
            }
        } else {
            PrintError.println("Id khách hàng không tồn tại!");
        }
    }

    public static void deleteCustomer(Scanner scanner){
        displayCustomer(scanner);
        System.out.println("==========================================");
        System.out.println("Nhập vào id khách hàng cần xóa: ");
        int n = Validator.ValidInt(scanner, 0);
        if(customerService.findById(n) != null){
            System.out.println("Bạn có chắc chắn muốn xóa:");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Validator.ValidInt(scanner, 0);
            if(choice == 1){
                Customer customer = new Customer();
                customer.setId(n);
                boolean check = customerService.delete(customer);
                if(check){
                    PrintSuccess.println("Đã xóa thành công khách hàng có id là: " + n+"!");
                    System.out.println("Bạn có muốn xem danh sách khách hàng luôn không? (Nhập 1):");
                    int c = Validator.ValidInt(scanner,0);
                    if(c==1){
                        displayCustomer(scanner);
                        return;
                    }
                    return;
                }
                PrintError.println("Có lỗi trong quá trình xóa khách hàng!");
                return;
            }
            PrintSuccess.println("Đã hủy quá trình xóa!");
            return;
        }
        PrintError.println("Id khách hàng không tồn tại!");
    }

    public static void searchByName(Scanner scanner){
        System.out.println("Nhập vào tên khách hàng cần tìm kiếm: ");
        String name = Validator.ValidString(scanner, 0, 50);
        List<Customer> customers = customerService.searchByName(name);
        if(customers == null ||customers.isEmpty()){
            PrintError.println("Không tìm thấy khách hàng nào có tên là: "+name+"!");
            return;
        }
        displayCustomer(customers);
    }

    public static void displayCustomer(List<Customer> customers){
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
        System.out.println("| ID |         Tên khách hàng         | Số điện thoại |             Email              |              Địa chỉ           |");
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
        customers.forEach(System.out::println);
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
    }
}