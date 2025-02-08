package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.ItemBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.ItemDAO;
import lk.ijse.gdse71.mrphone.dao.custom.ItemDetailDAO;
import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.entity.Item;
import lk.ijse.gdse71.mrphone.entity.ItemDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);
    ItemDetailDAO itemDetailDAO = (ItemDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEMDETAIL);

    @Override
    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        List<ItemDto> items = new ArrayList<>();
        List<Item> itemList = itemDAO.getAll();
        for (Item item : itemList) {
            items.add(new ItemDto(
                    item.getItem_id(),
                    item.getPrice(),
                    item.getBrand(),
                    item.getQty(),
                    item.getDescription()
                    ));
        }
        return items;
    }

    @Override
    public boolean save(ArrayList<ItemDto> itemDtos, ArrayList<ItemDetailDto> itemDetailDtos) throws SQLException, ClassNotFoundException {
        Connection connection = null;

        try{
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            for (ItemDto itemDto : itemDtos) {
                boolean b1 = itemDAO.save(new Item(
                   itemDto.getItem_id(),
                   itemDto.getPrice(),
                   itemDto.getBrand(),
                   itemDto.getQty(),
                   itemDto.getDescription()
                ));
                if (!b1) {
                    connection.rollback();
                    return false;
                }
            }
            for (ItemDetailDto itemDetailDto : itemDetailDtos) {
                boolean b2 = itemDetailDAO.saveItem(new ItemDetail(
                        itemDetailDto.getItem_id(),
                        itemDetailDto.getInventory_id()
                ));
                if (!b2) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        }catch (Exception e){
            if (connection != null) {
                connection.rollback();

            }
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return itemDAO.getNextId();
    }

    @Override
    public boolean update(ItemDto item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                item.getItem_id(),
                item.getPrice(),
                item.getBrand(),
                item.getQty(),
                item.getDescription()));
    }

    @Override
    public boolean delete(String item_id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(item_id);
    }

    @Override
    public boolean reduceQty(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException {
        return itemDAO.reduceQty(orderDetailDto);
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllItemId();
    }

    @Override
    public ItemDto findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        return itemDAO.findById(selectedItemId);
    }
}
