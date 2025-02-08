package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.PaymentBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.PayementDAO;
import lk.ijse.gdse71.mrphone.dto.PaymentDto;
import lk.ijse.gdse71.mrphone.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayementBOImpl implements PaymentBO {
    PayementDAO payementDAO = (PayementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    @Override
    public String getNextId() throws SQLException, SQLException, ClassNotFoundException {
        return payementDAO.getNextId();
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        ArrayList<Payment> payments = payementDAO.getAll();
        for (Payment paymentDto : payments) {
            paymentDtos.add(new PaymentDto(paymentDto.getPay_id(),paymentDto.getOrder_id(),paymentDto.getAmount(),paymentDto.getMethod()));
        }
        return paymentDtos;
    }

    @Override
    public boolean save(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return payementDAO.save(new Payment(
                paymentDto.getPay_id(),
                paymentDto.getOrder_id(),
                paymentDto.getAmount(),
                paymentDto.getMethod()));
    }
}
