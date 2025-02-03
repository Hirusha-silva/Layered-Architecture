package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.SalaryDAO;
import lk.ijse.gdse71.mrphone.dto.SalaryDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    public String getNextId() throws SQLException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select salary_id  from salary order by salary_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("S%03d", newIdIndex);
        }
        return "S001";
    }
    public  boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute(
                "insert into salary values(?,?,?,?,?)",
                salaryDto.getSalary_id(),
                salaryDto.getEmployee_id(),
                salaryDto.getAmount(),
                salaryDto.getDate()

        );
        return isSaved;
    }

    @Override
    public List<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SalaryDto customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }
//    public static List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
//        ResultSet resultSet = CrudUtil.execute("select * from item");
//        List<ItemDto> itemDtoList = new ArrayList<>();
//        while (resultSet.next()) {
//            ItemDto itemDto = new ItemDto(
//                    resultSet.getString(1),
//                    resultSet.getDouble(2),
//                    resultSet.getString(3),
//                    resultSet.getInt(4),
//                    resultSet.getString(5)
//
//            );
//            itemDtoList.add(itemDto);
//        }
//        return itemDtoList;
//    }
}
