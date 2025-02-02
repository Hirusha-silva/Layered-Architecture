package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.ItemBO;
import lk.ijse.gdse71.mrphone.BO.custom.ItemDetalBO;
import lk.ijse.gdse71.mrphone.dao.custom.ItemDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;

import java.sql.SQLException;

public class ItemDetailBOImpl implements ItemDetalBO {

    @Override
    public boolean saveItem(ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDetailDto itemDetailDto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
