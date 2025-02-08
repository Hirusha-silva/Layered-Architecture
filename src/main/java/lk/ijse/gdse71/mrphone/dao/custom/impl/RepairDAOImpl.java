package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.RepairDAO;
import lk.ijse.gdse71.mrphone.dto.RepairDto;
import lk.ijse.gdse71.mrphone.entity.Item;
import lk.ijse.gdse71.mrphone.entity.Repair;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairDAOImpl implements RepairDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select repairing_id from repairing order by repairing_id desc limit 1");

        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;

            return String.format("R%03d",newIdIndex);
        }
        return "R001";
    }
    public ArrayList<Repair> getAll() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = CrudUtil.execute("select * from repairing");
        ArrayList<Repair> repairDtos = new ArrayList<>();
        while (resultSet.next()) {
            Repair repairDto = new Repair(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getString(5)
            );
            repairDtos.add(repairDto);
        }
        return repairDtos;
    }
    public  boolean save(Repair entity) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into repairing values(?,?,?,?,?)",
                entity.getRepairing_id(),
                entity.getCustomer_id(),
                entity.getDescription(),
                entity.getDate(),
                entity.getStatus()
        );
        return isSaved;
    }

    public boolean update(Repair repair) throws SQLException, ClassNotFoundException {


        return CrudUtil.execute(
                "Update repairing  set customer_id=?,description=?,status=?, date=? where repairing_id=?",
                repair.getCustomer_id(),
                repair.getDescription(),
                repair.getStatus(),
                repair.getDate(),
                repair.getRepairing_id()

        );

    }
    public boolean delete(String reparing_id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from repairing where repairing_id=?", reparing_id);
    }
}
