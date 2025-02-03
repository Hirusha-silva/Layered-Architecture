package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.RepairDto;

import java.sql.SQLException;
import java.util.List;

public interface RepairBO extends SuperBO {
    String getNextId() throws SQLException, ClassNotFoundException;
    List<RepairDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(RepairDto repairDto) throws SQLException, ClassNotFoundException;
    boolean update(RepairDto repairDto) throws SQLException, ClassNotFoundException;
    boolean delete(String reparing_id) throws SQLException, ClassNotFoundException;


}
