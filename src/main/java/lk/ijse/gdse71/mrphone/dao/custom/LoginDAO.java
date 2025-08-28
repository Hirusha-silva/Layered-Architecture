package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.entity.User;

import java.sql.SQLException;

public interface LoginDAO extends CrudDAO<User> {
    User findByUserId(String username, String password) throws SQLException, ClassNotFoundException;
}
