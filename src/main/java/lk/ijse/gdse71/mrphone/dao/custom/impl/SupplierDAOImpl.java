package lk.ijse.gdse71.mrphone.dao.custom.impl;

import lk.ijse.gdse71.mrphone.dao.custom.SupplierDAO;
import lk.ijse.gdse71.mrphone.entity.Item;
import lk.ijse.gdse71.mrphone.entity.Supplier;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {


    public  boolean save(Supplier entity) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into supplier values(?,?,?,?)",
                entity.getSupplier_id(),
                entity.getName(),
                entity.getPhone_no(),
                entity.getCompany()
        );
        return isSaved;
    }

    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select supplier_id from supplier order by supplier_id desc limit 1");

        if (resultSet.next()) {
            String lastItemId = resultSet.getString(1);
            String substring = lastItemId.substring(1);
            int i = Integer.parseInt(substring);
            int newItemId = i + 1;
            return String.format("S%03d", newItemId);
        }
        return "S001";
    }

    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from supplier");
        ArrayList<Supplier> list = new ArrayList<>();
        while (resultSet.next()) {
            Supplier suppliers = new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
            list.add(suppliers);
        }
        return list;
    }

//public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
//    ResultSet resultSet = CrudUtil.execute("select supplier_id from supplier");
//
//    ArrayList<String> supplierIdList = new ArrayList<>();
//
//    while (resultSet.next()) {
//        supplierIdList.add(resultSet.getString(1));
//    }
//    return supplierIdList;
//}
public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {


    return CrudUtil.execute(
            "Update supplier set name=?,phone_no=?,company=? where supplier_id=?",
            entity.getName(),
            entity.getPhone_no(),
            entity.getCompany(),
            entity.getSupplier_id()


    );

}
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from supplier where supplier_id=?", supplierId);
    }

}
