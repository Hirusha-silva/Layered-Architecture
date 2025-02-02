package lk.ijse.gdse71.mrphone.dao;

import lk.ijse.gdse71.mrphone.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    String getNextId() throws SQLException, ClassNotFoundException;
     boolean save(T dto) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
    boolean delete(String customerId) throws SQLException, ClassNotFoundException;
    boolean update(T customerDto) throws SQLException, ClassNotFoundException;


}
