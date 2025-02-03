package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    List<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    boolean delete(String employeeId) throws SQLException, ClassNotFoundException;
    boolean update(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;
}
