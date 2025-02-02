package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
     List<ItemDto> getAll() throws SQLException, ClassNotFoundException;
     boolean save(ItemDto itemDto) throws SQLException, ClassNotFoundException;
     String getNextId() throws SQLException, ClassNotFoundException;
     boolean update(ItemDto itemDto) throws SQLException, ClassNotFoundException;
     boolean delete(String item_id) throws SQLException, ClassNotFoundException;
     boolean reduceQty(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException;
     ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
     ItemDto findById(String selectedItemId) throws SQLException, ClassNotFoundException;

}
