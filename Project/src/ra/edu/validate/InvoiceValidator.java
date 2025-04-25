package ra.edu.validate;

import ra.edu.business.model.Invoice;
import ra.edu.business.service.invoice.InvoiceService;
import ra.edu.business.service.invoice.InvoiceServiceImp;
import ra.edu.utils.Print.PrintError;

import java.util.Scanner;

public class InvoiceValidator {
    private static final InvoiceService invoiceService = new InvoiceServiceImp();

    public static int checkIdIsExist(Scanner sc) {
        while(true){
            int id = Validator.ValidInt(sc, 0);
            Invoice invoice = invoiceService.findById(id);
            if (invoice != null) {
                return id;
            }
            PrintError.println("Id đơn hàng không tồn tại, vui lòng nhập lại!");
        }
    }
}
