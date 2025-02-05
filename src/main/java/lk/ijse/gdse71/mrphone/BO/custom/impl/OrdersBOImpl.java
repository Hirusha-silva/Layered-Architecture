package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.OrdersBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.ItemDAO;
import lk.ijse.gdse71.mrphone.dao.custom.OrderDAO;
import lk.ijse.gdse71.mrphone.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.dto.OrdersDto;
import lk.ijse.gdse71.mrphone.entity.Order;
import lk.ijse.gdse71.mrphone.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER_DETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean saveOrders(List<OrdersDto> ordersDtos, List<OrderDetailDto> orderDetailDtos) throws Exception {
        Connection connection = null;
        System.out.println(ordersDtos+" "+orderDetailDtos);
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            for (OrdersDto ordersDto : ordersDtos) {
                boolean b1 = orderDAO.save(new Order(
                        ordersDto.getOrderId(),
                        ordersDto.getCustomerId(),
                        ordersDto.getOrderDate()
                ));
                if (!b1) {
                    System.out.println("save FAiled im order");
                    connection.rollback();
                    return false;
                }
            }

            for (OrderDetailDto orderDetailDto : orderDetailDtos) {
                boolean b2 = orderDetailsDAO.save(new OrderDetail(
                        orderDetailDto.getOrderId(),
                        orderDetailDto.getItemId(),
                        orderDetailDto.getQty(),
                        orderDetailDto.getPrice()
                ));
                if (!b2) {
                    System.out.println("save FAiled im order detail");
                    connection.rollback();
                    return false;
                }

                boolean qtyReduced = itemDAO.reduceQty(orderDetailDto);
                if (!qtyReduced) {
                    System.out.println("Failed to reduce item quantity");
                    connection.rollback();
                    return false;
                }
            }
                connection.commit();
                return true;



        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();
    }

}
