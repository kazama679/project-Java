package ra.edu.business.service.invoiceDetail;

import ra.edu.business.dao.invoiceDetail.InvoiceDetailDAO;
import ra.edu.business.dao.invoiceDetail.InvoiceDetailDAOImp;
import ra.edu.business.model.InvoiceDetail;

import java.util.List;

public class InvoiceDetailServiceImp implements InvoiceDetailService {
    private final InvoiceDetailDAO invoiceDetailDAO;
    public InvoiceDetailServiceImp() {
        invoiceDetailDAO = new InvoiceDetailDAOImp();
    }

    @Override
    public boolean add(InvoiceDetail invoiceDetail) {
        return invoiceDetailDAO.add(invoiceDetail);
    }

    @Override
    public List<InvoiceDetail> findAllById(int id) {
        return invoiceDetailDAO.findAllById(id);
    }

    @Override
    public List<InvoiceDetail> findAll() {
        return invoiceDetailDAO.findAll();
    }
}
