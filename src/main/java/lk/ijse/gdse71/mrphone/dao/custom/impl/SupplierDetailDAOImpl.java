package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.SupplierDetailDAO;
import lk.ijse.gdse71.mrphone.dto.SupplierAndSupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDetailDto;
import lk.ijse.gdse71.mrphone.entity.SupplierAndSupplierDetail;
import lk.ijse.gdse71.mrphone.entity.SupplierDetail;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDetailDAOImpl implements SupplierDetailDAO {
    public  boolean save(SupplierDetailDto supplierDetailDto) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into supplierDetail values(?,?,?,?,?)",
                supplierDetailDto.getSupplier_id(),
                supplierDetailDto.getItem_id(),
                supplierDetailDto.getQty(),
                supplierDetailDto.getPrice(),
                supplierDetailDto.getBrand()
        );
        return isSaved;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean save(SupplierAndSupplierDetail dto) throws SQLException, ClassNotFoundException {
        return false;
    }



    public List<SupplierAndSupplierDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select s.supplier_id, s.name, s.phone_no, s.company, sd.item_id, sd.qty, sd.price from supplier join supplier_detail on supplier.supplier_id = supplier_detail.supplier_id ");
        ArrayList<SupplierAndSupplierDetail> list = new ArrayList<>();
        while (resultSet.next()) {
            SupplierAndSupplierDetail supplierAndSupplierDetail = new SupplierAndSupplierDetail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(7),
                    resultSet.getDouble(8)

            );
            list.add(supplierAndSupplierDetail);
        }
        return list;
    }

    @Override
    public boolean delete(String customerId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplierAndSupplierDetail customerDto) throws SQLException, ClassNotFoundException {
        return false;
    }

}
