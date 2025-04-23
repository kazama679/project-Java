package ra.edu.business.dao.invoice;

import ra.edu.business.model.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDAO {
    List<Invoice> findAll();
    boolean add(Invoice invoice);
    Invoice findById(int id);
    boolean updateTotal(int id, double total);
    List<Invoice> findByName(String name);
    List<Invoice> findByDate(LocalDate date);
    double totalDay(LocalDate dateStart, LocalDate dateEnd);
    double totalMonth(int monthStart, int yearStart, int monthEnd, int yearEnd);
    double totalYear(int yearStart, int yearEnd);
}