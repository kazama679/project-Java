package ra.edu.business.config;

import ra.edu.utils.color.Print.PrintError;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "quanglienha";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            PrintError.print("Có lỗi trong quá trình kết nối với CSDL!");
        } catch (Exception e) {
            PrintError.print("Có lỗi không xác định!");
        }
        return conn;
    }

    public static void closeConnection(Connection conn, CallableStatement callSt) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                PrintError.print("Có lỗi trong quá trình kết nối với CSDL!");
            }
        }
        if(callSt != null) {
            try {
                callSt.close();
            } catch (SQLException e) {
                PrintError.print("Có lỗi trong quá trình kết nối với CSDL!");
            }
        }
    }
}
