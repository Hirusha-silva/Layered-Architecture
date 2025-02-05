package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDto> {
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    String findById(String customerId) throws SQLException, ClassNotFoundException;
}
