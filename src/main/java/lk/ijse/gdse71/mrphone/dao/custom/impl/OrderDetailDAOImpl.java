package lk.ijse.gdse71.mrphone.dao.custom.impl;

import com.mysql.cj.util.Util;
import lk.ijse.gdse71.mrphone.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse71.mrphone.entity.OrderDetail;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailsDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(OrderDetail entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES(?,?,?,?)",
                    entity.getOrderId(),
                    entity.getItemId(),
                    entity.getQty(),
                    entity.getPrice()
                );
    }

    @Override
    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetail customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
