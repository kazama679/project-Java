package ra.edu.utils.color.Print;

public class PrintSuccess{
    public static void println(Object obj) {
        System.out.println("\u001B[32m" + String.valueOf(obj) + "\u001B[0m");
    }

    public static void println() {
        System.out.println();
    }

    public static void print(Object obj) {
        System.out.print("\u001B[32m" + String.valueOf(obj) + "\u001B[0m");
    }

}