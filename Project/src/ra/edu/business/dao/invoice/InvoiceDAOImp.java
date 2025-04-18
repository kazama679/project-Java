package ra.edu.business.dao.invoice;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Invoice;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class InvoiceDAOImp implements InvoiceDAO{
    @Override
    public Invoice findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Invoice invoice = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_invoice_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                java.sql.Date sqlDate = rs.getDate("invoice_date");
                if(sqlDate != null) {
                    invoice.setInvoiceDate(sqlDate.toLocalDate());
                }else{
                    invoice.setInvoiceDate(null);
                }
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return invoice;
    }

    @Override
    public List<Invoice> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoices = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call display_invoice()}");
            ResultSet rs = callSt.executeQuery();
            while(rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                java.sql.Date sqlDate = rs.getDate("invoice_date");
                if(sqlDate != null) {
                    invoice.setInvoiceDate(sqlDate.toLocalDate());
                }else{
                    invoice.setInvoiceDate(null);
                }
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                listInvoices.add(invoice);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listInvoices;
    }

    @Override
    public boolean add(Invoice invoice) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_invoice(?,?,?)}");
            callSt.setInt(1, invoice.getCustomerId());
            callSt.setDate(2, java.sql.Date.valueOf(invoice.getInvoiceDate()));
            callSt.setDouble(3, invoice.getTotalAmount());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}