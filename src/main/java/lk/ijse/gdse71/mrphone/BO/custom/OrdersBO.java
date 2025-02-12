package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.OrderDetailDto;
import lk.ijse.gdse71.mrphone.dto.OrdersDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrdersBO extends SuperBO {
     boolean saveOrders(List<OrdersDto>ordersDtos, List<OrderDetailDto>orderDetailDtos) throws Exception;
     String getNextId() throws SQLException, ClassNotFoundException;
}
