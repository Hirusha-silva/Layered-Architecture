package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.RegisterDAO;
import lk.ijse.gdse71.mrphone.entity.User;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterDAOImpl implements RegisterDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute("insert into user values(?,?)",
                dto.getUser_id(),
                dto.getPassword()
        );
        return isSaved;
    }

    public boolean existsById(String userName) throws Exception {
        String sql = "SELECT user_id FROM user WHERE user_id = ?";
        ResultSet rst = CrudUtil.execute(sql, userName);
        return rst.next();
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
