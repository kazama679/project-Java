package ra.edu.presentation;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class InvoiceUI {
    public static void menuInvoice(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU INVOICE==============");
            System.out.println("1. In danh sách hóa đơn");
            System.out.println("2. Thêm mới đơn hàng");
            System.out.println("3. Hiển thị tổng doanh thu theo ngày");
            System.out.println("3. Hiển thị tổng doanh thu theo tháng");
            System.out.println("3. Hiển thị tổng doanh thu theo năm");
            System.out.println("4. Quay về menu chính");
            System.out.println("==========================================");
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
                    System.out.println("Thoát MENU INVOICE!");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-4!");
            }
        }while(choice!=4);
    }
}
