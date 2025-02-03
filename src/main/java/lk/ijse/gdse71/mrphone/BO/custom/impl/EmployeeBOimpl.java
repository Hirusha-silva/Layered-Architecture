package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.EmployeeBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.EmployeeDAO;
import lk.ijse.gdse71.mrphone.dto.EmployeeDto;
import lk.ijse.gdse71.mrphone.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOimpl implements EmployeeBO {
   private final EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EMPLOYEE);

    @Override
    public List<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<EmployeeDto> employees = employeeDAO.getAll();

        for (EmployeeDto employee : employees) {
            employeeDtos.add(new EmployeeDto(
                    employee.getEmployee_id(),
                    employee.getName(),
                    employee.getAddress(),
                    employee.getPhone_no()
            ));
        }
        return employeeDtos;

    }

    @Override
    public boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
       return employeeDAO.save(employeeDto);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNextId();
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public boolean update(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(employeeDto);
    }
}
