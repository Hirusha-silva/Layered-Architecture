package lk.ijse.gdse71.mrphone.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.gdse71.mrphone.dao.custom.LoginDAO;
import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public User findByUserId(String user_id, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT user_id,password FROM User WHERE user_id = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,user_id);

        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            return new User(
                    rst.getString("user_id"),
                    rst.getString("password")
            );
        }
        return null;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
