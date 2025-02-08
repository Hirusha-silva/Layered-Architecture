package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.RepairBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.RepairDAO;
import lk.ijse.gdse71.mrphone.dto.RepairDto;
import lk.ijse.gdse71.mrphone.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairBOImpl implements RepairBO {
    RepairDAO repairDAO = (RepairDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.REPAIR);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return repairDAO.getNextId();
    }

    @Override
    public List<RepairDto> getAll() throws SQLException, ClassNotFoundException {
        List<RepairDto> repairDtos = new ArrayList<>();
        List<Repair> repairs = repairDAO.getAll();
        for (Repair repair : repairs) {
            repairDtos.add(new RepairDto(repair.getRepairing_id(),repair.getCustomer_id(),repair.getDescription(),repair.getDate(),repair.getStatus()));
        }
        return repairDtos;
    }

    @Override
    public boolean save(RepairDto repairDto) throws SQLException, ClassNotFoundException {
        return repairDAO.save(new Repair(repairDto.getRepairing_id(),repairDto.getCustomer_id(),repairDto.getDescription(),repairDto.getDate(),repairDto.getStatus()));
    }

    @Override
    public boolean update(RepairDto repairDto) throws SQLException, ClassNotFoundException {
        return repairDAO.update(new Repair(repairDto.getRepairing_id(),repairDto.getCustomer_id(),repairDto.getDescription(),repairDto.getDate(),repairDto.getStatus()));
    }

    @Override
    public boolean delete(String reparing_id) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(reparing_id);
    }
}
