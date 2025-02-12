package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.SupplierDetailDAO;
import lk.ijse.gdse71.mrphone.dto.SupplierDetailDto;
import lk.ijse.gdse71.mrphone.entity.Item;
import lk.ijse.gdse71.mrphone.entity.SupplierAndSupplierDetail;
import lk.ijse.gdse71.mrphone.entity.SupplierDetail;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDetailDAOImpl implements SupplierDetailDAO {
    public  boolean save(SupplierDetail supplierDetailDto) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into supplierDetail values(?,?,?,?)",
                supplierDetailDto.getSupplier_id(),
                supplierDetailDto.getItem_id(),
                supplierDetailDto.getQty(),
                supplierDetailDto.getPrice()
        );
        return isSaved;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    public ArrayList<SupplierDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplierDetail customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

}
