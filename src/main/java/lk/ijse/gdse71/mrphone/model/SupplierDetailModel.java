package lk.ijse.gdse71.mrphone.model;

import lk.ijse.gdse71.mrphone.dto.SupplierAndSupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDto;
import lk.ijse.gdse71.mrphone.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDetailModel {
    public static boolean save(SupplierDetailDto supplierDetailDto) throws SQLException, ClassNotFoundException {

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
    public List<SupplierAndSupplierDetailDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("select s.supplier_id, s.name, s.phone_no, s.company, sd.item_id, sd.qty, sd.price from supplier join supplier_detail on supplier.supplier_id = supplier_detail.supplier_id ");
        ArrayList<SupplierAndSupplierDetailDto> list = new ArrayList<>();
        while (resultSet.next()) {
            SupplierAndSupplierDetailDto supplierAndSupplierDetailDto = new SupplierAndSupplierDetailDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(7),
                    resultSet.getDouble(8)

            );
            list.add(supplierAndSupplierDetailDto);
        }
        return list;
    }
}
