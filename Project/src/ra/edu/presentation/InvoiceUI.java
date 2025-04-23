package ra.edu.presentation;

import ra.edu.business.model.Customer;
import ra.edu.business.model.Invoice;
import ra.edu.business.model.InvoiceDetail;
import ra.edu.business.model.Product;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.business.service.invoiceDetail.InvoiceDetailService;
import ra.edu.business.service.invoiceDetail.InvoiceDetailServiceImp;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    public static final InvoiceService invoiceService = new InvoiceServiceImp();
    public static final InvoiceDetailService invoiceDetailService = new InvoiceDetailServiceImp();
    private static final ProductService productService = new ProductServiceImp();

    public static void menuInvoice(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU INVOICE==============");
            System.out.println("1. In danh sách hóa đơn");
            System.out.println("2. In danh sách hóa đơn chi tiết");
            System.out.println("3. Thêm mới hóa đơn");
            System.out.println("4. Tìm kiếm hóa đơn");
            System.out.println("5. Hiển thị tổng doanh thu");
            System.out.println("6. Quay về menu chính");
            System.out.println("==========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    displayInvoice();
                    break;
                case 2:
                    displayInvoiceDetail(scanner);
                    break;
                case 3:
                    addInvoice(scanner);
                    break;
                case 4:
                    searchInvoice(scanner);
                    break;
                case 5:
                    totalRevenue(scanner);
                    break;
                case 6:
                    System.out.println("Thoát MENU INVOICE!");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }

    public static void displayInvoice(){
        List<Invoice> invoices = invoiceService.findAll();
        if (invoices == null || invoices.isEmpty()) {
            System.err.println("Danh sách hóa đơn rỗng!");
            return;
        }
        System.out.println("+----+--------------------------------+------------+-------------------+");
        System.out.println("| ID |         Tên khách hàng         |  Ngày tạo  |     Tổng tiền     |");
        System.out.println("+----+--------------------------------+------------+-------------------+");
        invoices.forEach(System.out::println);
        System.out.println("+----+--------------------------------+------------+-------------------+");
    }

    public static void displayInvoiceDetail(Scanner scanner){
        displayInvoice();
        System.out.println("Mời bạn nhập id hóa đơn muốn xem chi tiết: ");
        int id = Validator.ValidInt(scanner, 0);
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllById(id);
        if(invoiceDetails == null || invoiceDetails.isEmpty()) {
            System.err.println("Danh sách rỗng!");
            return;
        }
        System.out.println("+----+------------+--------------------------------+----------+--------------------------+");
        System.out.println("| Id | Id hóa đơn |          Tên sản phẩm          | Số lượng |        Thành tiền        |");
        System.out.println("+----+------------+--------------------------------+----------+--------------------------+");
        invoiceDetails.forEach(System.out::println);
        System.out.println("+----+------------+--------------------------------+----------+--------------------------+");
    }

    public static void addInvoice(Scanner sc){
        System.out.println("Nhập số lượng đơn hàng muốn thêm: ");
        int n = Validator.ValidInt(sc, 0);
        for (int i = 0; i < n; i++) {
            Invoice invoice = new Invoice();
            invoice.inputData(sc);
            boolean check = invoiceService.add(invoice);
            if (!check) {
                System.err.println("Có lỗi trong quá trình thêm!");
                return;
            }
            System.out.println("Thêm hóa đơn thành công!");
            System.out.println("===============THÊM HÓA ĐƠN CHI TIẾT================");
            while(true){
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.inputData(sc, invoiceService.findAll().getLast().getInvoiceId());
                boolean result = invoiceDetailService.add(invoiceDetail);
                if(!result){
                    System.err.println("Có lỗi trong quá trình thêm chi tiết hóa đơn!");
                    return;
                }
                System.out.println("Thêm hóa đơn chi tiết thành công!");
                Product product = productService.findById(invoiceDetail.getProductId());
                if (product == null) {
                    System.err.println("Không tìm thấy sản phẩm!");
                    return;
                }product.setStock(product.getStock()-invoiceDetail.getQuantity());
                boolean checkProduct = productService.update(product);
                if (!checkProduct) {
                    System.err.println("Có lỗi trong quá trình trừ số lượng!");
                    return;
                }
                System.out.println("Trừ số lượng sản phẩm thành công!");
                System.out.println("Bạn có muốn thêm hóa đơn chi tiết nữa không: ");
                System.out.println("1. Có");
                System.out.println("2. Không");
                System.out.println("Lựa chọn của bạn: ");
                int choice = Validator.ValidInt(sc,0);
                if(choice != 1){
                    break;
                }
            }
            // cập nhập lại tổng giá trị đơn hàng
            int id = invoiceService.findAll().getLast().getInvoiceId();
            double totalAmount = totalAmountInvoice(id);
            invoiceService.updateTotal(id, totalAmount);
        }
        displayInvoice();
    }

    public static double totalAmountInvoice(int id){
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllById(id);
        if(invoiceDetails==null){
            System.err.println("Không tìm chi tiết đơn hàng!");
            return 0;
        }
        return invoiceDetails.stream().mapToDouble(InvoiceDetail::getUnitPrice).sum();
    }

    public static void searchInvoice(Scanner scanner){
        while(true){
            System.out.println("===============MENU SEARCH INVOICE==============");
            System.out.println("1. Tìm kiếm hóa đơn theo tên khách hàng");
            System.out.println("2. Tìm kiếm hóa đơn theo ngày");
            System.out.println("3. Thoát!");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    searchName(scanner);
                    break;
                case 2:
                    searchDate(scanner);
                    break;
                case 3:
                    System.out.println("Thoát menu tìm kiếm!");
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-3!");
            }
        }
    }

    public static void searchName(Scanner scanner){
        System.out.println("Nhập vào tên khách hàng muốn tìm kiếm hóa đơn: ");
        String name = Validator.ValidString(scanner,0,100);
        List<Invoice> invoices = invoiceService.findByName(name);
        if(invoices==null || invoices.isEmpty()){
            System.out.println("Danh sách đơn hàng rỗng!");
            return;
        }
        System.out.println("+----+--------------------------------+------------+-------------------+");
        System.out.println("| Id |         Tên khách hàng         |  Ngày tạo  |     Tổng tiền     |");
        System.out.println("+----+--------------------------------+------------+-------------------+");
        invoices.forEach(System.out::println);
        System.out.println("+----+--------------------------------+------------+-------------------+");
    }

    public static void searchDate(Scanner scanner){
        System.out.println("Nhập vào ngày/tháng/năm muốn tìm kiếm hóa đơn: ");
        LocalDate date = Validator.validDate(scanner, LocalDate.MIN);
        List<Invoice> invoices = invoiceService.findByDate(date);
        if(invoices==null || invoices.isEmpty()){
            System.out.println("Danh sách đơn hàng rỗng!");
            return;
        }
        System.out.println("+----+--------------------------------+------------+-------------------+");
        System.out.println("| Id |         Tên khách hàng         |  Ngày tạo  |     Tổng tiền     |");
        System.out.println("+----+--------------------------------+------------+-------------------+");
        invoices.forEach(System.out::println);
        System.out.println("+----+--------------------------------+------------+-------------------+");
    }

    public static void totalRevenue(Scanner scanner){
        while(true){
            System.out.println("===============MENU TOTAL REVENUE==============");
            System.out.println("1. Hiển thị tổng doanh thu theo ngày");
            System.out.println("2. Hiển thị tổng doanh thu theo tháng");
            System.out.println("3. Hiển thị tổng doanh thu theo năm");
            System.out.println("4. Thoát!");
            System.out.println("Lựa chọn của bạn: ");
            int choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    totalRevenueDay(scanner);
                    break;
                case 2:
                    totalRevenueMonth(scanner);
                    break;
                case 3:
                    totalRevenueYear(scanner);
                    break;
                case 4:
                    System.out.println("Thoát menu doanh thu!");
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-4!");
            }
        }
    }

    public static void totalRevenueDay(Scanner scanner){
        System.out.println("Nhập vào ngày bắn đầu: ");
        LocalDate dateStart = Validator.validDate(scanner, LocalDate.MIN);
        System.out.println("Nhập vào ngày kết thúc: ");
        LocalDate dateEnd = Validator.validDate(scanner, dateStart);
        double total = invoiceService.totalDay(dateStart, dateEnd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("Tổng doanh thu từ ngày %s đến ngày %s: %.0f$\n", dateStart.format(formatter), dateEnd.format(formatter),total);
    }

    public static void totalRevenueMonth(Scanner scanner){
        System.out.println("Nhập vào tháng bắn đầu: ");
        int monthStart = Validator.validMonth(scanner, 0,13);
        System.out.println("Nhập vào năm bắn đầu: ");
        int yearStart = Validator.ValidInt(scanner, 1969);
        System.out.println("Nhập vào tháng kết thúc: ");
        int monthEnd = Validator.validMonth(scanner, 0,13);
        System.out.println("Nhập vào năm kết thúc: ");
        int yearEnd = Validator.validYear(scanner, monthStart, monthEnd, yearStart);
        double total = invoiceService.totalYear(yearStart, yearEnd);
        System.out.printf("Tổng doanh thu từ tháng %d/%d đến tháng %d/%d: %.0f$\n", monthStart, yearStart, monthEnd, yearEnd, total);
    }

    public static void totalRevenueYear(Scanner scanner){
        System.out.println("Nhập vào năm bắn đầu: ");
        int yearStart = Validator.ValidInt(scanner, 1969);
        System.out.println("Nhập vào năm kết thúc: ");
        int yearEnd = Validator.ValidInt(scanner, yearStart-1);
        double total = invoiceService.totalYear(yearStart, yearEnd);
        System.out.printf("Tổng doanh thu từ năm %d đến năm %d: %.0f$\n", yearStart, yearEnd,total);
    }
}