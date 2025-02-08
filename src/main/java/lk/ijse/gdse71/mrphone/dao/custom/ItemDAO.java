package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
     // List<ItemDto> getAll() throws SQLException, ClassNotFoundException;
      boolean reduceQty(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException;
      ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
      ItemDto findById(String selectedItemId) throws SQLException, ClassNotFoundException;
}
