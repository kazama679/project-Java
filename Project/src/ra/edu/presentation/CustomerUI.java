package ra.edu.presentation;

import ra.edu.business.model.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.validate.CustomerValidator;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class CustomerUI {
    private static final CustomerService customerService = new CustomerServiceImp();

    public static void menuCustomer(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU CUSTOMER==============");
            System.out.println("1. In danh sách khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Sửa khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Tìm kiếm khách hàng theo tên");
            System.out.println("6. Quay về menu chính");
            System.out.println("===========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    displayCustomer();
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
                    System.out.println("Thoát MENU CUSTOMER!");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }

    public static void displayCustomer() {
        List<Customer> listCustomers = customerService.findAll();
        if (listCustomers == null || listCustomers.isEmpty()) {
            System.err.println("Danh sách khách hàng rỗng!");
            return;
        }
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
        System.out.println("| ID |         Tên khách hàng         | Số điện thoại |             Email              |              Địa chỉ           |");
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
        listCustomers.forEach(System.out::println);
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
    }

    public static void addCustomer(Scanner scanner){
        System.out.println("Nhập vào số lương khách hàng muốn thêm: ");
        int n = Validator.ValidInt(scanner, 0);
        for (int i = 0; i < n; i++) {
            Customer customer = new Customer();
            customer.inputData(scanner);
            boolean check = customerService.add(customer);
            if(check){
                System.out.println("Thêm sản khách hàng công!");
            }else{
                System.out.println("Có lỗi trong quá trình thêm mới!");
            }
        }
        displayCustomer();
    }

    public static void updateCustomer(Scanner scanner) {
        displayCustomer();
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
                System.out.println("3. Sửa email khách hàng)");
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
                        System.out.println("Thoát menu sửa sản phẩm!");
                        break;
                    default:
                        System.out.println("Vui lòng chọn từ 1-5!");
                }
            } while (choice != 5);
            boolean check = customerService.update(customer);
            if (check) {
                System.out.println("Sửa khách hàng thành công!");
                displayCustomer();
            } else {
                System.out.println("Có lỗi trong quá trình sửa!");
            }
        } else {
            System.out.println("Id khách hàng không tồn tại!");
        }
    }

    public static void deleteCustomer(Scanner scanner){
        displayCustomer();
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
                    System.out.println("Xóa khách hàng thành công!");
                    displayCustomer();
                    return;
                }
                System.out.println("Có lỗi trong quá trình xóa khách hàng!");
                return;
            }
            System.out.println("Đã hủy quá trình xóa!");
            return;
        }
        System.out.println("Id khách hàng không tồn tại!");
    }

    public static void searchByName(Scanner scanner){
        System.out.println("Nhập vào tên: ");
        String name = Validator.ValidString(scanner, 0, 50);
        List<Customer> customers = customerService.findAll();
        boolean found = false;
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                found = true;
            }
        }
        if (!found) {
            System.err.println("Không tìm thấy khách hàng nào!");
            return;
        }
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
        System.out.println("| ID |         Tên khách hàng         | Số điện thoại |             Email              |              Địa chỉ           |");
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
        for (Customer customer : customers) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(customer);
            }
        }
        System.out.println("+----+--------------------------------+---------------+--------------------------------+--------------------------------+");
    }
}