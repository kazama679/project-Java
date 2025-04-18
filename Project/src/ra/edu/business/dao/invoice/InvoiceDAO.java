package ra.edu.business.dao.invoice;

import ra.edu.business.model.Invoice;

import java.util.List;

public interface InvoiceDAO {
    List<Invoice> findAll();
    boolean add(Invoice invoice);
    Invoice findById(int id);
}
