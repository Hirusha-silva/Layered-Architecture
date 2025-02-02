package lk.ijse.gdse71.mrphone.model;

import lk.ijse.gdse71.mrphone.dto.CustomerDto;
import lk.ijse.gdse71.mrphone.dto.RepairDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepairModel {
    public String getNextReparingId() throws SQLException, ClassNotFoundException {
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
    public static List<RepairDto> getAll() throws SQLException, ClassNotFoundException {


        ResultSet resultSet = CrudUtil.execute("select * from repairing");
        List<RepairDto> repairDtos = new ArrayList<>();
        while (resultSet.next()) {
            RepairDto repairDto = new RepairDto(
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
    public static boolean save(RepairDto repairDto) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into repairing values(?,?,?,?,?)",
                repairDto.getRepairing_id(),
                repairDto.getCustomer_id(),
                repairDto.getDescription(),
                repairDto.getDate(),
                repairDto.getStatus()
        );
        return isSaved;
    }

    public boolean updateRepair(RepairDto repairDto) throws SQLException, ClassNotFoundException {


        return CrudUtil.execute(
                "Update repairing  set customer_id=?,description=?,status=?, date=? where repairing_id=?",
                repairDto.getCustomer_id(),
                repairDto.getDescription(),
                repairDto.getStatus(),
                repairDto.getDate(),
                repairDto.getRepairing_id()

        );

    }
    public boolean deleteReparing(String reparing_id) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from repairing where repairing_id=?", reparing_id);
    }
}
