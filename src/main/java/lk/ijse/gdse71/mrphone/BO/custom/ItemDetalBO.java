package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;

import java.sql.SQLException;

public interface ItemDetalBO extends SuperBO {
    boolean saveItem(ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException;
}
