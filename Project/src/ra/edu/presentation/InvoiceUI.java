package ra.edu.presentation;

import ra.edu.business.model.Invoice;
import ra.edu.business.model.InvoiceDetail;
import ra.edu.business.model.Product;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.business.service.invoiceDetail.InvoiceDetailService;
import ra.edu.business.service.invoiceDetail.InvoiceDetailServiceImp;
import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.utils.color.Color;
import ra.edu.utils.color.Print.PrintError;
import ra.edu.utils.color.Print.PrintSuccess;
import ra.edu.utils.color.Print.printColor.PrintColor;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InvoiceUI {
    public static final InvoiceService invoiceService = new InvoiceServiceImp();
    public static final InvoiceDetailService invoiceDetailService = new InvoiceDetailServiceImp();
    private static final ProductService productService = new ProductServiceImp();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GRAY = "\u001B[90m";

    public static void menuInvoice(Scanner scanner){
        int choice;
        do {
            System.out.println("================MENU INVOICE==============\n" +
                                "| "+Color.BRIGHT_PURPLE+"1. In danh sách hóa đơn"+Color.RESET+"                |\n" +
                                "| "+Color.BRIGHT_PURPLE+"2. In danh sách hóa đơn chi tiết"+Color.RESET+"       |\n" +
                                "| "+Color.BRIGHT_PURPLE+"3. Thêm mới hóa đơn"+Color.RESET+"                    |\n" +
                                "| "+Color.BRIGHT_PURPLE+"4. Tìm kiếm hóa đơn"+Color.RESET+"                    |\n" +
                                "| "+Color.BRIGHT_PURPLE+"5. Hiển thị tổng doanh thu"+Color.RESET+"             |\n" +
                                "| "+Color.BRIGHT_PURPLE+"6. Quay về menu chính"+Color.RESET+"                  |\n" +
                                "==========================================");
            System.out.print("Lựa chọn của bạn: ");
            choice = Validator.ValidInt(scanner, 0);
            switch (choice) {
                case 1:
                    displayInvoice(scanner);
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
                    PrintColor.printYellow("Thoát MENU INVOICE!");
                    break;
                default:
                    PrintError.println("Vui lòng chọn từ 1-6!");
            }
        }while(choice!=6);
    }

    public static void displayInvoice(Scanner scanner){
        int offset = 0, rowCount = 5, page = invoiceService.countInvoices();
        int numberPage = (int) Math.ceil((double) page / rowCount);
        while(true){
            int currentPage = offset / rowCount + 1;
            List<Invoice> invoices = invoiceService.paginateInvoices(offset, rowCount);
            System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
            System.out.println("| ID |         Tên khách hàng         |  Ngày tạo  | Số loại sản phẩm |     Tổng tiền     |");
            System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
            invoices.forEach(System.out::println);
            System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
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
                    PrintColor.printYellow("Thoát menu hiển thị danh sách hóa đơn!");
                    return;
                default:
                    PrintError.println("Vui lòng chọn đúng!");
            }
        }
    }

    public static void displayInvoiceDetail(Scanner scanner){
        displayInvoice(scanner);
        System.out.println("Mời bạn nhập id hóa đơn muốn xem chi tiết: ");
        int id = Validator.ValidInt(scanner, 0);
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllById(id);
        if(invoiceDetails == null || invoiceDetails.isEmpty()) {
            PrintError.println("Không tìm thấy hóa đơn có id là: "+id+"!");
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
                PrintError.println("Có lỗi trong quá trình thêm!");
                return;
            }
            PrintSuccess.println("Thêm hóa đơn thành công!");
            System.out.println("===============THÊM HÓA ĐƠN CHI TIẾT================");
            while(true){
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.inputData(sc, invoiceService.findAll().getLast().getInvoiceId());
                boolean result = invoiceDetailService.add(invoiceDetail);
                if(!result){
                    PrintError.println("Có lỗi trong quá trình thêm chi tiết hóa đơn!");
                    return;
                }
                PrintSuccess.println("Thêm hóa đơn chi tiết thành công!");
                Product product = productService.findById(invoiceDetail.getProductId());
                if (product == null) {
                    PrintError.println("Không tìm thấy sản phẩm!");
                    return;
                }product.setStock(product.getStock()-invoiceDetail.getQuantity());
                boolean checkProduct = productService.update(product);
                if (!checkProduct) {
                    PrintError.println("Có lỗi trong quá trình trừ số lượng!");
                    return;
                }
                System.out.println("Trừ số lượng sản phẩm thành công!");
                System.out.println("Bạn có muốn thêm hóa đơn chi tiết nữa không: ");
                System.out.println("1. Có");
                System.out.println("2. Không");
                System.out.println("Lựa chọn của bạn: ");
                int choice = Validator.ValidInt(sc,0);
                if(choice != 1){
                    PrintSuccess.println("Đã hoàn tất quá trình thêm các hóa đơn chi tiết!");
                    break;
                }
            }
            // cập nhập lại tổng giá trị đơn hàng
            int id = invoiceService.findAll().getLast().getInvoiceId();
            double totalAmount = totalAmountInvoice(id);
            invoiceService.updateTotal(id, totalAmount);
        }
        displayInvoice(sc);
    }

    public static double totalAmountInvoice(int id){
        List<InvoiceDetail> invoiceDetails = invoiceDetailService.findAllById(id);
        if(invoiceDetails==null){
            PrintError.println("Không tìm chi tiết đơn hàng!");
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
                    PrintColor.printYellow("Thoát menu tìm kiếm!");
                    return;
                default:
                    PrintError.println("Vui lòng chọn từ 1-3!");
            }
        }
    }

    public static void searchName(Scanner scanner){
        System.out.println("Nhập vào tên khách hàng muốn tìm kiếm hóa đơn: ");
        String name = Validator.ValidString(scanner,0,100);
        List<Invoice> invoices = invoiceService.findByName(name);
        if(invoices==null || invoices.isEmpty()){
            PrintError.println("Không tìm thấy hóa đơn của khác hàng có tên là: "+name+"!");
            return;
        }
        System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
        System.out.println("| ID |         Tên khách hàng         |  Ngày tạo  | Số loại sản phẩm |     Tổng tiền     |");
        System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
        invoices.forEach(System.out::println);
        System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
    }

    public static void searchDate(Scanner scanner){
        System.out.println("Nhập vào ngày/tháng/năm muốn tìm kiếm hóa đơn: ");
        LocalDate date = Validator.validDate(scanner, LocalDate.MIN);
        List<Invoice> invoices = invoiceService.findByDate(date);
        if(invoices==null || invoices.isEmpty()){
            PrintError.println("Không tìm thấy hóa đơn của ngày: "+date+"!");
            return;
        }
        System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
        System.out.println("| ID |         Tên khách hàng         |  Ngày tạo  | Số loại sản phẩm |     Tổng tiền     |");
        System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
        invoices.forEach(System.out::println);
        System.out.println("+----+--------------------------------+------------+------------------+-------------------+");
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
                    PrintColor.printYellow("Thoát menu doanh thu!");
                    return;
                default:
                    PrintError.println("Vui lòng chọn từ 1-4!");
            }
        }
    }

    public static void totalRevenueDay(Scanner scanner){
        System.out.println("Nhập vào ngày bắn đầu: ");
        LocalDate dateStart = Validator.validDate(scanner, LocalDate.now().minusDays(1));
        System.out.println("Nhập vào ngày kết thúc: ");
        LocalDate dateEnd = Validator.validDate(scanner, dateStart);
        double total = invoiceService.totalDay(dateStart, dateEnd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(total>0){
            System.out.printf("Tổng doanh thu từ ngày %s%s%s đến ngày %s%s%s: %s%.0f$%s\n", Color.BLUE, dateStart.format(formatter), Color.RESET, Color.BLUE, dateEnd.format(formatter), Color.RESET,Color.BRIGHT_YELLOW,total, Color.RESET);
            return;
        }
        PrintError.println("Không tìm thấy bất kì hóa đơn nào từ ngày " + Color.BLUE + dateStart.format(formatter) + Color.RED + " đến ngày " + Color.BLUE + dateEnd.format(formatter) + Color.RED + "!\n");
    }

    public static void totalRevenueMonth(Scanner scanner){
        System.out.println("Nhập vào tháng bắn đầu: ");
        int monthStart = Validator.validMonth(scanner, 0,13);
        System.out.println("Nhập vào năm bắn đầu: ");
        int yearStart = Validator.validYearByMonth(scanner, monthStart);
        System.out.println("Nhập vào tháng kết thúc: ");
        int monthEnd = Validator.validMonth(scanner, 0,13);
        System.out.println("Nhập vào năm kết thúc: ");
        int yearEnd = Validator.validYear(scanner, monthStart, monthEnd, yearStart);
        double total = invoiceService.totalYear(yearStart, yearEnd);
        if(total>0){
            System.out.printf("Tổng doanh thu từ tháng %s%d/%d%s đến tháng %s%d/%d%s: %s$%.0f%s\n", Color.BLUE, monthStart, yearStart, Color.RESET, Color.BLUE, monthEnd, yearEnd, Color.RESET, Color.BRIGHT_YELLOW, total, Color.RESET);
            return;
        }
        System.out.printf("%sKhông tìm thấy bất kì hóa đơn nào từ %s%d/%d%s đến %s%d/%d%s!%s\n", Color.RED, Color.BLUE, monthStart, yearStart, Color.RED, Color.BLUE, monthEnd, yearEnd, Color.RED, Color.RESET);
    }

    public static void totalRevenueYear(Scanner scanner){
        System.out.println("Nhập vào năm bắn đầu: ");
        int yearStart = Validator.ValidInt(scanner, LocalDate.now().getYear()-1);
        System.out.println("Nhập vào năm kết thúc: ");
        int yearEnd = Validator.ValidInt(scanner, yearStart-1);
        double total = invoiceService.totalYear(yearStart, yearEnd);
        if(total>0){
            System.out.printf("Tổng doanh thu từ năm %s%d%s đến năm %s%d%s: %s%.0f$%s\n", Color.BLUE, yearStart, Color.RESET, Color.BLUE, yearEnd, Color.RESET, Color.BRIGHT_YELLOW, total, Color.RESET);
            return;
        }
        System.out.printf("%sKhông tìm thấy bất kì hóa đơn nào từ %s%d%s đến %s%d%s!%s\n", Color.RED, Color.BLUE, yearStart, Color.RED, Color.BLUE, yearEnd, Color.RED, Color.RESET);
    }
}