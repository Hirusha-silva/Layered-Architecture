package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
     String getNextId() throws SQLException, SQLException, ClassNotFoundException;
     ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException;
    boolean save(PaymentDto paymentDto) throws SQLException, ClassNotFoundException;
}
