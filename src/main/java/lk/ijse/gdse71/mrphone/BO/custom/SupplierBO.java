package lk.ijse.gdse71.mrphone.BO.custom;

import lk.ijse.gdse71.mrphone.BO.SuperBO;
import lk.ijse.gdse71.mrphone.dto.SupplierAndSupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDetailDto;
import lk.ijse.gdse71.mrphone.dto.SupplierDto;
import lk.ijse.gdse71.mrphone.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
   // boolean save(ArrayList<SupplierDto>supplierDtos,ArrayList<SupplierDetailDto>supplierDetailDtos) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    //ArrayList<SupplierAndSupplierDetailDto> getAll() throws SQLException, ClassNotFoundException;
    boolean update(SupplierDto entity) throws SQLException, ClassNotFoundException;
    boolean delete(String supplierId) throws SQLException, ClassNotFoundException;
    boolean save(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
}
