package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean checkCredential(String userName, String password) throws SQLException, ClassNotFoundException;
}
