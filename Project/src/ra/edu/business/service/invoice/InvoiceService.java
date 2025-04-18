package ra.edu.business.service.invoice;

import ra.edu.business.model.Invoice;

import java.util.List;

public interface InvoiceService {
    List<Invoice> findAll();
    boolean add(Invoice invoice);
    Invoice findById(int id);
}
