package ra.edu.business.dao.invoiceDetail;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.InvoiceDetail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailDAOImp implements InvoiceDetailDAO{
    @Override
    public List<InvoiceDetail> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<InvoiceDetail> listInvoiceDetails = new ArrayList<InvoiceDetail>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call display_invoice_detail()}");
            ResultSet rs = callSt.executeQuery();
            listInvoiceDetails = new ArrayList<>();
            while(rs.next()){
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setInvoiceId(rs.getInt("item_id"));
                invoiceDetail.setProductId(rs.getInt("invoice_id"));
                invoiceDetail.setProductId(rs.getInt("product_id"));
                invoiceDetail.setQuantity(rs.getInt("quantity"));
                invoiceDetail.setUnitPrice(rs.getDouble("unit_price"));
                listInvoiceDetails.add(invoiceDetail);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi trong quá trình chạy invoiceDetailDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định trong quá trình chạy invoiceDetailDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listInvoiceDetails;
    }

    @Override
    public boolean add(InvoiceDetail invoiceDetail) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_invoice_detail(?,?,?,?)}");
            callSt.setInt(1, invoiceDetail.getInvoiceId());
            callSt.setInt(2, invoiceDetail.getProductId());
            callSt.setInt(3, invoiceDetail.getQuantity());
            callSt.setDouble(4, invoiceDetail.getUnitPrice());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Có lỗi trong quá trình chạy invoiceDetailDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định trong quá trình chạy invoiceDetailDAOImp: "+ e.fillInStackTrace());
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
