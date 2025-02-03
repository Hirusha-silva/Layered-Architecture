package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.SalaryBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.SalaryDAO;
import lk.ijse.gdse71.mrphone.dto.SalaryDto;

import java.sql.SQLException;

public class SalaryBOImpl implements SalaryBO {
    private final SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SALARY);
    @Override
    public String getNextId() throws SQLException, SQLException, ClassNotFoundException {
        return salaryDAO.getNextId();
    }

    @Override
    public boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
       return salaryDAO.save(new SalaryDto(salaryDto.getSalary_id(),salaryDto.getEmployee_id(),salaryDto.getAmount(),salaryDto.getDate()));
    }
}
