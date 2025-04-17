package ra.edu.business.model;

import ra.edu.validate.CustomerValidator;
import ra.edu.validate.Validator;

import java.util.Scanner;

public class Customer implements IApp{
    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(String address, String email, int id, String name, String phone) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Customer() {
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.println("Nhập vào tên khách hàng: ");
        this.name = Validator.ValidString(sc,0,100);
        System.out.println("Nhập vào số điện thoại khách hàng: ");
        this.phone = CustomerValidator.validCheckSomePhone(sc,-1);
        System.out.println("Nhập vào email khách hàng: ");
        this.email = CustomerValidator.validCheckSomeEmail(sc,-1);
        System.out.println("Nhập vào địa chỉ khách hàng: ");
        this.address = Validator.ValidString(sc, 0, 255);
    }

    @Override
    public String toString(){
        return "--------------\nId khách hàng: "+id+"\nTên khách hàng: "+name+"\nSố điện thoại: "+phone+"\nEmail: "+email+"\nĐịa chỉ: "+address;
    }
}
