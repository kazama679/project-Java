package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validator {
    public static int ValidInt(Scanner sc, int min) {
        while(true){
            try {
                int n = Integer.parseInt(sc.nextLine());
                if(n<=min){
                    System.err.println("Phải lớn hơn "+min);
                    continue;
                }
                return n;
            } catch (NumberFormatException e1) {
                System.err.println("Không phải số, vui lòng nhập lại");
            } catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static double ValidDouble(Scanner sc, double min) {
        while (true) {
            try {
                double n = Double.parseDouble(sc.nextLine());
                if (n <= min) {
                    System.err.println("Phải lớn hơn " + min);
                    continue;
                }
                return n;
            } catch (NumberFormatException e1) {
                System.err.println("Không phải số thực, vui lòng nhập lại");
            } catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static Boolean ValidBoolean(Scanner sc) {
        while(true){
            try {
                String b = sc.nextLine();
                if(b.equals("true")||b.equals("false")||b.isEmpty()){
                    return Boolean.parseBoolean(b);
                }
                System.err.println("Không đúng định dạng kiểu Boolean, vui lòng nhập lại");
            } catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static String ValidString(Scanner sc, int min, int max) {
        while(true){
            try {
                String s = sc.nextLine();
                if(s.isEmpty()){
                    System.err.println("Không được để trống, vui lòng nhập lại");
                    continue;
                }
                if(s.length()<min||s.length()>max){
                    System.out.println("Không đúng định dạng chuỗi, vui lòng nhập lại");
                    continue;
                }
                return s;
            }catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static String ValidEmail(Scanner sc) {
        while(true){
            try {
                String email = sc.nextLine();
                if(!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,6})+$")){
                    System.err.println("Không đúng định dạng email, vui lòng nhập lại");
                    continue;
                }
                return email;
            }catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static String ValidPhone(Scanner sc) {
        while(true){
            try {
                String phone = sc.nextLine();
                if(!phone.matches("^(03[2-9]|05[2689]|07[0-9]|08[1-9]|09[0-9])\\d{7}$")){
                    System.err.println("Không đúng định dạng số điện thoại Việt Nam, vui lòng nhập lại");
                    continue;
                }
                return phone;
            }catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static LocalDate validDate(Scanner sc) {
        while(true){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                return LocalDate.parse(sc.nextLine(), formatter);
            }catch (DateTimeParseException e1) {
                System.err.println("Định dạng date k đúng, vui lòng nhập lại");
            }catch (Exception e) {
                System.err.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }
}
