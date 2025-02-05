package lk.ijse.gdse71.mrphone.BO.custom.impl;

import lk.ijse.gdse71.mrphone.BO.custom.SupplierDetailBO;
import lk.ijse.gdse71.mrphone.dao.DAOFactory;
import lk.ijse.gdse71.mrphone.dao.custom.SupplierDAO;
import lk.ijse.gdse71.mrphone.dao.custom.SupplierDetailDAO;
import lk.ijse.gdse71.mrphone.dto.SupplierAndSupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.tm.SupplierTm;
import lk.ijse.gdse71.mrphone.entity.Supplier;
import lk.ijse.gdse71.mrphone.entity.SupplierAndSupplierDetail;
import lk.ijse.gdse71.mrphone.entity.SupplierDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDetailBOImpl implements SupplierDetailBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIERDETAIL);
    @Override
    public boolean save(SupplierDetailDto supplierDetailDto) throws SQLException, ClassNotFoundException {
       return supplierDAO.save(new Supplier());
    }
}
