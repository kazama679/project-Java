package ra.edu.business.model;

import ra.edu.validate.CustomerValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Invoice implements IApp {
    private int invoiceId;
    private int customerId;
    private LocalDate invoiceDate;
    private double totalAmount;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Invoice(int customerId, LocalDate invoiceDate, int invoiceId, double totalAmount) {
        this.customerId = customerId;
        this.invoiceDate = invoiceDate;
        this.invoiceId = invoiceId;
        this.totalAmount = totalAmount;
    }

    public Invoice() {
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập vào id khách hàng cho đơn hàng: ");
        this.invoiceId = CustomerValidator.validCheckIsExist(sc);
        this.invoiceDate = LocalDate.now();
        this.totalAmount = 0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Id: "+invoiceId+", Date: "+invoiceDate.format(formatter)+", Amount: "+totalAmount;
    }
}
