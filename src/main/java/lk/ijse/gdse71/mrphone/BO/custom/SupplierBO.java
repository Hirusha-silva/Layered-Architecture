package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO extends SuperBO {
    boolean save(Supplier entity) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    List<Supplier> getAll() throws SQLException, ClassNotFoundException;
    boolean update(Supplier entity) throws SQLException, ClassNotFoundException;
    boolean delete(String supplierId) throws SQLException, ClassNotFoundException;
}
