package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.LoginBO;
import lk.ijse.gdse71.mrphone.dao.custom.LoginDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.LoginDAOImpl;
import lk.ijse.gdse71.mrphone.entity.User;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private final LoginDAO loginDAO = new LoginDAOImpl();
    @Override
    public boolean checkCredential(String userName, String password) throws SQLException, ClassNotFoundException {
        User user = loginDAO.findByUserId(userName, password);
        if (user != null) {
            return password.equals(user.getPassword());
        }
        return false;
    }
}
