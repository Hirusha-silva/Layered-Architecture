package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.SalaryDto;

import java.sql.SQLException;

public interface SalaryBO extends SuperBO {
    String getNextId() throws SQLException, SQLException, ClassNotFoundException;
    boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException;
}
