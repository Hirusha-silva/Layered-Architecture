package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.ItemBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.ItemDAO;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        List<ItemDto> items = new ArrayList<>();
        List<ItemDto> itemList = itemDAO.getAll();
        for (ItemDto item : itemList) {
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
    public boolean save(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new ItemDto(
                itemDto.getItem_id(),
                itemDto.getPrice(),
                itemDto.getBrand(),
                itemDto.getQty(),
                itemDto.getDescription()
        )) ;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return itemDAO.getNextId();
    }

    @Override
    public boolean update(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new ItemDto(
                itemDto.getItem_id(),
                itemDto.getPrice(),
                itemDto.getBrand(),
                itemDto.getQty(),
                itemDto.getDescription()
                ));
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
