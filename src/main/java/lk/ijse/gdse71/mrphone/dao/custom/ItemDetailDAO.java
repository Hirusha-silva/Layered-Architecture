package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;

import java.sql.SQLException;

public interface ItemDetailDAO  {
    boolean saveItem(ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException;
}
