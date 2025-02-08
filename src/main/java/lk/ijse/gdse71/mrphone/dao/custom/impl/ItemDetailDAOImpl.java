package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.ItemDetailDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;
import lk.ijse.gdse71.mrphone.entity.ItemDetail;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDetailDAOImpl implements ItemDetailDAO {
    public boolean saveItem(ItemDetail itemDetailDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into item_detail values (?,?)", itemDetailDto.getItem_id(), itemDetailDto.getInventory_id());
    }

    public boolean updateItem(ItemDetail itemDetailDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("update item_detail set inventory_id=? where item_id=?", itemDetailDto.getInventory_id(), itemDetailDto.getItem_id());
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(ItemDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<ItemDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(ItemDetail customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
