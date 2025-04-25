package ra.edu.business.dao.invoice;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Invoice;
import ra.edu.utils.Print.PrintError;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
                invoice.setTotalAmount(rs.getDouble("total_amount"));
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return invoice;
    }

    @Override
    public boolean updateTotal(int id, double total) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_total_invoice(?,?)}");
            callSt.setInt(1, id);
            callSt.setDouble(2, total);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Invoice> findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoice = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_by_name_customer(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            listInvoice = new ArrayList<>();
            while (rs.next()) {
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
                listInvoice.add(invoice);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listInvoice;
    }

    @Override
    public List<Invoice> findByDate(LocalDate date) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoice = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_by_date(?)}");
            callSt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = callSt.executeQuery();
            listInvoice = new ArrayList<>();
            while (rs.next()) {
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
                listInvoice.add(invoice);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listInvoice;
    }

    @Override
    public double totalDay(LocalDate dateStart, LocalDate dateEnd) {
        Connection conn = null;
        CallableStatement callSt = null;
        double total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call total_by_day(?, ?, ?)}");
            callSt.setDate(1, java.sql.Date.valueOf(dateStart));
            callSt.setDate(2, java.sql.Date.valueOf(dateEnd));
            callSt.registerOutParameter(3, Types.DECIMAL);
            callSt.execute();
            total = callSt.getDouble(3);
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }

    @Override
    public double totalMonth(int monthStart, int yearStart, int monthEnd, int yearEnd) {
        Connection conn = null;
        CallableStatement callSt = null;
        double total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call total_by_month(?,?,?,?,?)}");
            callSt.setInt(1, monthStart);
            callSt.setInt(2, yearStart);
            callSt.setInt(3, monthEnd);
            callSt.setInt(4, monthEnd);
            callSt.registerOutParameter(5, Types.DECIMAL);
            callSt.execute();
            total = callSt.getDouble(5);
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }

    @Override
    public double totalYear(int yearStart, int yearEnd) {
        Connection conn = null;
        CallableStatement callSt = null;
        double total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call total_by_year(?, ?, ?)}");
            callSt.setInt(1, yearStart);
            callSt.setInt(2, yearEnd);
            callSt.registerOutParameter(3, Types.DECIMAL);
            callSt.execute();
            total = callSt.getDouble(3);
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }

    @Override
    public List<Invoice> paginateInvoices(int offset, int rowCount) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> listInvoices = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call paginate_invoices(?,?)}");
            callSt.setInt(1, offset);
            callSt.setInt(2, rowCount);
            ResultSet rs = callSt.executeQuery();
            listInvoices = new ArrayList<Invoice>();
            while(rs.next()){
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
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listInvoices;
    }

    @Override
    public int countInvoices() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call count_invoices()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return 0;
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
            listInvoices = new ArrayList<Invoice>();
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
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
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
            PrintError.println("Có lỗi trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy invoiceDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}