package ra.edu.business.service.invoice;

import ra.edu.business.dao.invoice.InvoiceDAO;
import ra.edu.business.dao.invoice.InvoiceDAOImp;
import ra.edu.business.model.Invoice;

import java.time.LocalDate;
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

    @Override
    public boolean updateTotal(int id, double total) {
        return invoiceDAO.updateTotal(id, total);
    }

    @Override
    public List<Invoice> findByName(String name) {
        return invoiceDAO.findByName(name);
    }

    @Override
    public List<Invoice> findByDate(LocalDate date) {
        return invoiceDAO.findByDate(date);
    }

    @Override
    public double totalDay(LocalDate dateStart, LocalDate dateEnd) {
        return invoiceDAO.totalDay(dateStart, dateEnd);
    }

    @Override
    public double totalMonth(int monthStart, int yearStart, int monthEnd, int yearEnd) {
        return invoiceDAO.totalMonth(monthStart, yearStart, monthEnd, yearEnd);
    }

    @Override
    public double totalYear(int yearStart, int yearEnd) {
        return invoiceDAO.totalYear(yearStart, yearEnd);
    }

    @Override
    public List<Invoice> paginateInvoices(int offset, int rowCount) {
        return invoiceDAO.paginateInvoices(offset, rowCount);
    }

    @Override
    public int countInvoices() {
        return invoiceDAO.countInvoices();
    }
}
