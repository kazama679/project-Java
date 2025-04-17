package ra.edu.business.dao.user;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Product;
import ra.edu.business.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {
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
                listUsers.add(user);
            }
        } catch (SQLException e) {
            System.err.println("Có lỗi trong quá trình chạy productDAOImp: "+ e.fillInStackTrace());
        } catch (Exception e) {
            System.err.println("Có lỗi không xác định trong quá trình chạy productDAOImp: "+ e.fillInStackTrace());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listUsers;
    }

    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}