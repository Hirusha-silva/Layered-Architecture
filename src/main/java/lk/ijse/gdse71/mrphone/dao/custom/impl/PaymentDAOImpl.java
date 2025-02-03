package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.PayementDAO;
import lk.ijse.gdse71.mrphone.dto.PaymentDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PayementDAO {
    public String getNextId() throws SQLException, SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select pay_id from payment order by pay_id  desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return "P001";
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(PaymentDto customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }


    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select * from payment");

        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }
    public boolean save(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into payment values(?, ?, ?, ?)", paymentDto.getPay_id(), paymentDto.getOrder_id(), paymentDto.getAmount(), paymentDto.getMethod());
    }
}
