package lk.ijse.gdse71.mrphone.model;

import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.dto.OrdersDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersModel {
    private final OrderDetailModel orderDetailModel = new OrderDetailModel();

    public String getNextOderId() throws SQLException, ClassNotFoundException {
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

    public boolean saveOrder(OrdersDto ordersDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isOrderSaved = CrudUtil.execute(
                    "insert into orders values (?,?,?)",
                    ordersDto.getOrderId(),
                    ordersDto.getCustomerId(),
                    ordersDto.getOrderDate()
            );
            if (isOrderSaved) {
                boolean isOrderDetailListSaved = orderDetailModel.saveOrderDetailList(ordersDto.getOrderDetailDtos());
                if (isOrderDetailListSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }
}
