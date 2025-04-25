package ra.edu.business.model;

import ra.edu.business.service.product.ProductService;
import ra.edu.business.service.product.ProductServiceImp;
import ra.edu.presentation.ProductUI;
import ra.edu.validate.ProductValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class InvoiceDetail {
    private static final ProductService productService = new ProductServiceImp();
    private int invoiceDetailId;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;

    public InvoiceDetail(int invoiceDetailId, int invoiceId, int productId, int quantity, double unitPrice) {
        this.invoiceDetailId = invoiceDetailId;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceDetail() {
    }

    public int getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(int invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double totalUnit(){
        Product product = productService.findById(this.productId);
        if(product == null){
            System.err.println("Không tìm thấy sản phẩm, lỗi trong InvoiceDetail!");
            return 0;
        }
        return this.quantity * product.getPrice();
    }

    public void inputData(Scanner sc, int idInvoice) {
        this.invoiceId = idInvoice;
        ProductUI.displayProduct(sc);
        System.out.println("Nhập vào id sản phẩm: ");
        this.productId = ProductValidator.checkIdIsExist(sc);
        System.out.println("Nhập vào số lượng: ");
        this.quantity = ProductValidator.checkQuantity(sc,productId);
        this.unitPrice = totalUnit();
    }

    @Override
    public String toString() {
        return String.format("| %-2d | %-10d | %-30s | %-8d | %-24.0f |",
                invoiceDetailId, invoiceId, productService.findByIdAll(productId).getName(), quantity, unitPrice);
    }
}