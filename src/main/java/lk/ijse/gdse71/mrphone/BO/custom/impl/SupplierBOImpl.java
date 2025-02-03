package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.SupplierBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.SupplierDAO;
import lk.ijse.gdse71.mrphone.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(entity.getSupplier_id(),entity.getName(),entity.getPhone_no(),entity.getCompany()));
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNextId();
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> list = new ArrayList<>();
        List<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier supplier : suppliers) {
            list.add(new Supplier(supplier.getSupplier_id(),supplier.getName(),supplier.getPhone_no(),supplier.getCompany()));
        }
        return list;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(entity.getSupplier_id(),entity.getName(),entity.getPhone_no(),entity.getCompany()));
    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }
}
