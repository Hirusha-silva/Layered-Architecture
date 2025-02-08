package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;
import lk.ijse.gdse71.mrphone.entity.ItemDetail;

import java.sql.SQLException;

public interface ItemDetailDAO extends CrudDAO <ItemDetail>{
    boolean saveItem(ItemDetail itemDetail) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDetail itemDetail) throws SQLException, ClassNotFoundException;
}
