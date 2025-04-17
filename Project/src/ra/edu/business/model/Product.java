package ra.edu.business.model;

import ra.edu.validate.PhoneValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Product implements IApp {
    private int productId;
    private String name;
    private String brand;
    private double price;
    private int stock;

    public Product() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int id) {
        this.productId = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product(String brand, String name, double price, int id, int stock) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.productId = id;
        this.stock = stock;
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập vào tên sản phẩm: ");
        this.name = PhoneValidator.validCheckSomeName(sc, -1);
        System.out.println("Nhập vào tên nhãn hàng: ");
        this.brand = Validator.ValidString(sc, -1, 50);
        System.out.println("Nhập vào giá của sản phẩm: ");
        this.price = Validator.ValidDouble(sc, 0);
        System.out.println("Nhập vào số lượng sản phẩm: ");
        this.stock = Validator.ValidInt(sc, 0);
    }

    @Override
    public String toString() {
        return "----------------\nId: " + productId + "\nTên sản phẩm: " + name + "\nNhãn hàng: " + brand + "\nGiá: " + price + "\nSố lượng: " + stock;
    }
}