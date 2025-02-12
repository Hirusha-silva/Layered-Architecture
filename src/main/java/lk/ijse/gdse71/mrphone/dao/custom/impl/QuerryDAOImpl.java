package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.QuerryDAO;
import lk.ijse.gdse71.mrphone.entity.SupplierAndSupplierDetail;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuerryDAOImpl implements QuerryDAO {
    @Override
    public ArrayList<SupplierAndSupplierDetail> getAll() throws SQLException, ClassNotFoundException {
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
}
