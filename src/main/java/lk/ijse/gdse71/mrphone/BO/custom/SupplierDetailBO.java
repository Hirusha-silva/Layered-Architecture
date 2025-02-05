package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.SupplierDetailDto;
import lk.ijse.gdse71.mrphone.entity.SupplierAndSupplierDetail;
import lk.ijse.gdse71.mrphone.entity.SupplierDetail;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDetailBO extends SuperBO {
      boolean save(SupplierDetailDto supplierDetailDto) throws SQLException, ClassNotFoundException;

}
