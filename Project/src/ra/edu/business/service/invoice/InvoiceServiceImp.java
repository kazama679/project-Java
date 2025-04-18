package ra.edu.business.service.invoice;

import ra.edu.business.dao.invoice.InvoiceDAO;
import ra.edu.business.dao.invoice.InvoiceDAOImp;
import ra.edu.business.model.Invoice;

import java.util.List;

public class InvoiceServiceImp implements InvoiceService{
    private final InvoiceDAO invoiceDAO;
    public InvoiceServiceImp() {
        invoiceDAO = new InvoiceDAOImp();
    }

    @Override
    public boolean add(Invoice invoice) {
        return invoiceDAO.add(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceDAO.findAll();
    }

    @Override
    public Invoice findById(int id) {
        return invoiceDAO.findById(id);
    }
}
