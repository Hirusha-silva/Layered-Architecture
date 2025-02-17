package lk.ijse.gdse71.mrphone.util;

import lk.ijse.gdse71.mrphone.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql, Object...obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            preparedStatement.setObject((i + 1), obj[i]);
        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return (T) resultSet;
        }else{
            int i = preparedStatement.executeUpdate();
            boolean isSaved = i >0;
            return (T) ((Boolean) isSaved);
        }

    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection();
    }
}
