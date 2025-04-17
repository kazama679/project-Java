package ra.edu.presentation;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class CustomerUI {
    public static void menuCustomer(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU CUSTOMER==============");
            System.out.println("1. In danh sách khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Sửa khách hàng");
            System.out.println("4. Xóa khách hàng");
//            System.out.println("5. Tìm kiếm điện thoại theo nhãn hàng");
//            System.out.println("5. Tìm kiếm điện thoại theo khoảng giá");
//            System.out.println("5. Tìm kiếm điện thoại theo số lượng tồn kho");
            System.out.println("6. Quay về menu chính");
            System.out.println("===========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Thoát MENU CUSTOMER!");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }
}
