package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.OrderDAO;
import lk.ijse.gdse71.mrphone.entity.Order;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");

        if (resultSet.next()) {
            String lastOrderId = resultSet.getString(1);
            String substring = lastOrderId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i+1;

            return String.format("O%03d", newIdIndex);
        }
        return "O001";
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        System.out.println("OrderDAo"+entity);
       return CrudUtil.execute("INSERT INTO orders VALUES(?,?,?)",
               entity.getOrderId(),
               entity.getCustomerId(),
               entity.getOrderDate()
               );
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }
}
