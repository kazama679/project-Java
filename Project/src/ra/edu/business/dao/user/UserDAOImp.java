package ra.edu.business.dao.user;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.User;
import ra.edu.utils.Print.PrintError;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {
    @Override
    public User findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        User user = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_user_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setAccount(rs.getString("user_account"));
                user.setPassword(rs.getString("user_password"));
                user.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy userDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy userDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<User> listUsers = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call display_user()}");
            ResultSet rs = callSt.executeQuery();
            listUsers = new ArrayList<User>();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setAccount(rs.getString("user_account"));
                user.setPassword(rs.getString("user_password"));
                user.setStatus(rs.getBoolean("status"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy userDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy userDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listUsers;
    }

    @Override
    public boolean update(User user) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_status_user(?,?,?,?)}");
            callSt.setInt(1,user.getId());
            callSt.setString(2,user.getAccount());
            callSt.setString(3,user.getPassword());
            callSt.setBoolean(4,user.getStatus());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            PrintError.println("Có lỗi trong quá trình chạy userDAOImp!");
        } catch (Exception e) {
            PrintError.println("Có lỗi không xác định trong quá trình chạy userDAOImp!");
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}