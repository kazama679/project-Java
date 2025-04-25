package ra.edu.business.service.invoiceDetail;

import ra.edu.business.model.InvoiceDetail;

import java.util.List;

public interface InvoiceDetailService {
    List<InvoiceDetail> findAll();
    boolean add(InvoiceDetail invoiceDetail);
    List<InvoiceDetail> findAllById(int id);
}