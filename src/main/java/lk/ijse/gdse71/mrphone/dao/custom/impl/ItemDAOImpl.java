package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.ItemDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select item_id from item order by item_id desc limit 1");

        if (resultSet.next()) {
            String lastItemId = resultSet.getString(1);
            String substring = lastItemId.substring(1);
            int i = Integer.parseInt(substring);
            int newItemId = i + 1;
            return String.format("I%03d", newItemId);
        }
        return "I001";
    }
    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from item");
        List<ItemDto> itemDtoList = new ArrayList<>();
        while (resultSet.next()) {
            ItemDto itemDto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)

            );
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }
    public boolean save(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute(
                "insert into item values(?,?,?,?,?)",
                itemDto.getItem_id(),
                itemDto.getPrice(),
                itemDto.getBrand(),
                itemDto.getQty(),
                itemDto.getDescription()

        );
        return isSaved;
    }
    public boolean update(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update item set price=?,brand=?,qty=?,description=? where item_id=?",
                itemDto.getPrice(),
                itemDto.getBrand(),
                itemDto.getQty(),
                itemDto.getDescription(),
                itemDto.getItem_id()
        );
    }
    public boolean delete(String item_id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from item where item_id=?", item_id);
    }

    public boolean reduceQty(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update item set qty = qty - ? where item_id = ?",
                orderDetailDto.getQty(),
                orderDetailDto.getItemId()
        );
    }

    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select item_id from item");

        ArrayList<String> itemIdList = new ArrayList<>();

        while (resultSet.next()) {
            itemIdList.add(resultSet.getString(1));
        }
        return itemIdList;
    }
    public ItemDto findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from item where item_id = ?", selectedItemId);

        if (resultSet.next()) {
            return new ItemDto(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
        }
        return null;
    }
}
