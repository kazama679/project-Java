package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validator {
    public static String validString(Scanner sc, int min, int max) {
        while (true){
            String input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println("Không được để trống");
                continue;
            }
            if(input.length() < min && input.length() > max){
                System.out.println("Kí tự không được vượt giới hạn");
            }
            return input;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = validString(sc, 0, 5);
        System.out.println(s);
    }

    public static int validInt(Scanner sc, String mess) {
        System.out.println(mess);
        while (true){
            try {
                int n = Integer.parseInt(sc.nextLine());
                return n;
            }catch (NumberFormatException e){
                System.out.println("K p số");
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    public static float validFloat(Scanner sc, String mess) {
        System.out.println(mess);
        while (true){
            try {
                float n = Float.parseFloat(sc.nextLine());
                return n;
            }catch (NumberFormatException e){
                System.out.println("K p số");
            }catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }

    public static LocalDate validDate(Scanner sc) {
        while(true){
            System.out.println("Nhập vào ngày tháng năm: ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                return LocalDate.parse(sc.nextLine(), formatter);
            }catch (DateTimeParseException e1) {
                System.out.println("Định dạng date k đúng, nhập lại");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean validBoolean(Scanner sc, String mess) {
        System.out.println(mess);
        while (true){
            String input = sc.nextLine();
            if(input.equals("true")||input.equals("false")){
                return Boolean.parseBoolean(input);
            }
            System.out.println("K hợp lệ");
        }
    }
}
