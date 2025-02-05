package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
     String getNextId() throws SQLException, ClassNotFoundException;
     List<CustomerDto> getAll() throws SQLException, ClassNotFoundException;
     boolean delete(String customerId) throws SQLException, ClassNotFoundException;
     boolean save(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
     boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
  String findById(String customerId) throws SQLException, ClassNotFoundException;
}
