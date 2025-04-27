package ra.edu.validate;

import ra.edu.business.model.Customer;
import ra.edu.business.service.customer.CustomerService;
import ra.edu.business.service.customer.CustomerServiceImp;
import ra.edu.utils.color.Print.PrintError;

import java.util.List;
import java.util.Scanner;

public class CustomerValidator {
    private static final CustomerService customerService = new CustomerServiceImp();

    public static String validCheckSomePhone(Scanner sc, int id) {
        while (true) {
            String phone = Validator.ValidPhone(sc);
            List<Customer> listCustomers = customerService.findAll();
            boolean check = listCustomers.stream().anyMatch(c -> c.getPhone().equals(phone) && c.getId() != id);
            if (check) {
                PrintError.println("Số điện thoại đã tồn tại, vui lòng nhập lại!");
            } else {
                return phone;
            }
        }
    }

    public static String validCheckSomeEmail(Scanner sc, int id) {
        while (true) {
            String email = Validator.ValidEmail(sc);
            List<Customer> listCustomers = customerService.findAll();
            boolean check = listCustomers.stream().anyMatch(c -> c.getEmail().equals(email) && c.getId() != id);
            if (check) {
                PrintError.println("Email đã tồn tại, vui lòng nhập lại!");
            } else {
                return email;
            }
        }
    }

    public static int validCheckIsExist(Scanner sc) {
        while(true){
            int id = Validator.ValidInt(sc, 0);
            Customer customer = customerService.findById(id);
            if (customer != null) {
                return id;
            }
            PrintError.println("Id khách hàng không tồn tại, vui lòng nhập lại!");
        }
    }
}
