package lk.ijse.gdse71.mrphone.dao.custom;

import lk.ijse.gdse71.mrphone.dao.SuperDAO;
import lk.ijse.gdse71.mrphone.entity.SupplierAndSupplierDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QuerryDAO extends SuperDAO {
     ArrayList<SupplierAndSupplierDetail> getAll() throws SQLException, ClassNotFoundException;
}
