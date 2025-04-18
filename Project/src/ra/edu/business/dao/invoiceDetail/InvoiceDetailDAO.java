package ra.edu.business.dao.invoiceDetail;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Invoice;
import ra.edu.business.model.InvoiceDetail;

import java.util.List;

public interface InvoiceDetailDAO{
    List<InvoiceDetail> findAll();
    boolean add(InvoiceDetail invoiceDetail);
}
