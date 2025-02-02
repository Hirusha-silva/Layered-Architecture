package lk.ijse.gdse71.mrphone.model;

import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.dto.*;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {


    public static boolean save(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {

        boolean isSaved = CrudUtil.execute(
                "insert into supplier values(?,?,?,?)",
                supplierDto.getSupplier_id(),
                supplierDto.getName(),
                supplierDto.getPhone_no(),
                supplierDto.getCompany()
        );
        return isSaved;
    }

    public String nextSupplierId() throws SQLException, ClassNotFoundException {
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

    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select * from supplier");
        ArrayList<SupplierDto> list = new ArrayList<>();
        while (resultSet.next()) {
            SupplierDto supplierDto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)

            );
            list.add(supplierDto);
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
public boolean updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {


    return CrudUtil.execute(
            "Update supplier set name=?,phone_no=?,company=? where supplier_id=?",
            supplierDto.getName(),
            supplierDto.getPhone_no(),
            supplierDto.getCompany(),
            supplierDto.getSupplier_id()


    );

}
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("delete from supplier where supplier_id=?", supplierId);
    }

}
