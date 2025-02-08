package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.SalaryBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.SalaryDAO;
import lk.ijse.gdse71.mrphone.dto.SalaryDto;
import lk.ijse.gdse71.mrphone.entity.Salary;

import java.sql.SQLException;

public class SalaryBOImpl implements SalaryBO {
    private final SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SALARY);
    @Override
    public String getNextId() throws SQLException, SQLException, ClassNotFoundException {
        return salaryDAO.getNextId();
    }

    @Override
    public boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
