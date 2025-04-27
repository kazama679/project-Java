package ra.edu.validate;

import ra.edu.utils.color.Print.PrintError;

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
                    PrintError.println("Phải lớn hơn "+min);
                    continue;
                }
                return n;
            } catch (NumberFormatException e1) {
                PrintError.println("Không phải số, vui lòng nhập lại!");
            } catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static int ValidIntPage(Scanner sc, int min, int max) {
        while(true){
            try {
                int n = Integer.parseInt(sc.nextLine());
                if(n>min && n<max){
                    return n;
                }
                PrintError.println("Phải lớn hơn "+min+" và nhỏ hơn "+max);
            } catch (NumberFormatException e1) {
                PrintError.println("Không phải số, vui lòng nhập lại!");
            } catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static double ValidDouble(Scanner sc, double min) {
        while (true) {
            try {
                double n = Double.parseDouble(sc.nextLine());
                if (n <= min) {
                    PrintError.println("Phải lớn hơn " + min);
                    continue;
                }
                return n;
            } catch (NumberFormatException e1) {
                PrintError.println("Không phải số thực, vui lòng nhập lại!");
            } catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
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
                PrintError.println("Không đúng định dạng kiểu Boolean, vui lòng nhập lại!");
            } catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static String ValidString(Scanner sc, int min, int max) {
        while(true){
            try {
                String s = sc.nextLine();
                if(s.isEmpty()){
                    PrintError.println("Không được để trống, vui lòng nhập lại!");
                    continue;
                }
                if(s.length()<min||s.length()>max){
                    PrintError.println("Không đúng định dạng chuỗi, vui lòng nhập lại!");
                    continue;
                }
                return s;
            }catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static String ValidEmail(Scanner sc) {
        while(true){
            try {
                String email = sc.nextLine();
                if(!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,6})+$")){
                    PrintError.println("Không đúng định dạng email, vui lòng nhập lại!");
                    continue;
                }
                return email;
            }catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static String ValidPhone(Scanner sc) {
        while(true){
            try {
                String phone = sc.nextLine();
                if(!phone.matches("^(03[2-9]|05[2689]|07[0-9]|08[1-9]|09[0-9])\\d{7}$")){
                    PrintError.println("Không đúng định dạng số điện thoại Việt Nam, vui lòng nhập lại!");
                    continue;
                }
                return phone;
            }catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static LocalDate validDate(Scanner sc, LocalDate date) {
        while(true){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate inputDate = LocalDate.parse(sc.nextLine(), formatter);
                if (inputDate.isBefore(date)) {
                    PrintError.println("Ngày nhập vào phải lớn hơn hoặc bằng " + date.format(formatter) + ". Vui lòng nhập lại!");
                    continue;
                }
                return inputDate;
            }catch (DateTimeParseException e1) {
                PrintError.println("Định dạng date k đúng, vui lòng nhập lại!");
            }catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static int validMonth(Scanner sc, int min, int max) {
        while(true){
            try {
                int n = Integer.parseInt(sc.nextLine());
                if(n<=min || n>=max){
                    PrintError.println("Phải lớn hơn "+min+" và phải nhỏ hơn "+max);
                    continue;
                }
                return n;
            } catch (NumberFormatException e1) {
                PrintError.println("Không phải số, vui lòng nhập lại!");
            } catch (Exception e) {
                PrintError.println("Có lỗi không xác định khi validate: "+e.getMessage());
            }
        }
    }

    public static int validYear(Scanner sc, int monthStart, int monthEnd, int yearStart) {
        while (true) {
            try {
                int yearEnd = Integer.parseInt(sc.nextLine());
                if (monthStart <= monthEnd) {
                    if (yearEnd >= yearStart) {
                        return yearEnd;
                    }
                    PrintError.println("Năm kết thúc phải lớn hơn hoặc bằng năm bắt đầu!");
                } else {
                    if (yearEnd > yearStart) {
                        return yearEnd;
                    }
                    PrintError.println("Năm kết thúc phải lớn hơn năm bắt đầu!");
                }
            } catch (NumberFormatException e) {
                PrintError.println("Không phải số. Vui lòng nhập lại.");
            } catch (Exception e) {
                PrintError.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }

    public static int validYearByMonth(Scanner sc, int monthStart) {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        while (true) {
            try {
                int yearStart = Integer.parseInt(sc.nextLine());
                if (yearStart < 1979) {
                    PrintError.println("Năm phải lớn hơn hoặc bằng 1970!");
                    continue;
                }
                if (yearStart > currentYear) {
                    PrintError.println("Không được nhập năm lớn hơn năm hiện tại!");
                    continue;
                }
                if (yearStart == currentYear && monthStart > currentMonth) {
                    PrintError.println("Không được nhập năm hiện tại vì tháng đang của tương lai!");
                    continue;
                }
                return yearStart;
            } catch (NumberFormatException e) {
                PrintError.println("Không phải số. Vui lòng nhập lại.");
            } catch (Exception e) {
                PrintError.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }
}
