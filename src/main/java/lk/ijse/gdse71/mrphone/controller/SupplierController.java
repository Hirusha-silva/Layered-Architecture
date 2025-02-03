package lk.ijse.gdse71.mrphone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse71.mrphone.BO.BOFactory;
import lk.ijse.gdse71.mrphone.BO.custom.SupplierBO;
import lk.ijse.gdse71.mrphone.BO.custom.impl.SupplierBOImpl;
import lk.ijse.gdse71.mrphone.dto.SupplierDto;
import lk.ijse.gdse71.mrphone.dto.tm.SupplierTm;
import lk.ijse.gdse71.mrphone.entity.Supplier;
import lk.ijse.gdse71.mrphone.model.SupplierDetailModel;
import lk.ijse.gdse71.mrphone.dao.custom.impl.SupplierDAOImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private AnchorPane SupplierAnchorpane;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;


    @FXML
    private TableColumn<?, ?> clmCompany;

    @FXML
    private TableColumn<?, ?> clmId;



    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPhone;

    @FXML
    private TableColumn<?, ?> clmItemId;

    @FXML
    private TableColumn<?, ?> clmQty;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private Label lblSupplierId;

    @FXML
    private TableView<SupplierTm> tblSupplier;



    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;


     SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    @FXML
    void supplierMouseClick(MouseEvent event) {
        SupplierTm supplierTm = tblSupplier.getSelectionModel().getSelectedItem();
        if (supplierTm != null) {
            lblSupplierId.setText(supplierTm.getSupplier_id());
            txtCompany.setText(supplierTm.getCompany());
            txtPhone.setText(supplierTm.getPhone_no());
            txtName.setText(supplierTm.getName());
        }
    }



    @FXML
    void DeleteSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplier_id = lblSupplierId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = supplierBO.delete(supplier_id);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION,"Supplier deleted successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete supplier id").show();
            }
        }
    }

    @FXML
    void ResetSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();

    }
    //SupplierDAOImpl supplierDAOImpl = new SupplierDAOImpl();
    SupplierDetailModel supplierDetailModel = new SupplierDetailModel();


    @FXML
    void SaveSupplier(ActionEvent event) {
        String supplier_id = lblSupplierId.getText();
        String name = txtName.getText();
        String phone_no = txtPhone.getText();
        String company = txtCompany.getText();



        if(!name.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }
        if(!phone_no.matches(".{10}")){
            new Alert(Alert.AlertType.ERROR, "Invalid phone number").show();
            txtPhone.requestFocus();
            return;
        }



//        SupplierDAOImpl supplierDAOImpl = new SupplierDAOImpl();
//        SupplierDto supplierDto = new SupplierDto(supplier_id, name, phone_no, company);

        try {
            boolean isSaved = supplierBO.save(new Supplier(supplier_id,name,phone_no,company));

            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Supplier has been saved successfully").show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }

    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextSupplierId();
        loadTableData();
        
        txtName.clear();
        txtPhone.clear();
        txtCompany.clear();

    }

    private void loadTableData() {
        ObservableList<SupplierTm> oblist = FXCollections.observableArrayList();
        try {
            List<Supplier> suppliers = supplierBO.getAll();
            for (Supplier supplierDto: suppliers) {
                SupplierTm supplierTm = new SupplierTm(
                        supplierDto.getSupplier_id(),
                        supplierDto.getName(),
                        supplierDto.getPhone_no(),
                        supplierDto.getCompany()

                );
                oblist.add(supplierTm);
            }
            tblSupplier.setItems(oblist);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void loadNextSupplierId() throws SQLException, ClassNotFoundException {
        String nextSupplierId = supplierBO.getNextId();
        lblSupplierId.setText(nextSupplierId);
    }

    @FXML
    void UpdateSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplier_id = lblSupplierId.getText();
        String name = txtName.getText();
        String phone_no = txtPhone.getText();
        String company = txtCompany.getText();



        if(!name.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }
        if(!phone_no.matches(".{10}")){
            new Alert(Alert.AlertType.ERROR, "Invalid phone number").show();
            txtPhone.requestFocus();
            return;
        }

        boolean isUpdated = supplierBO.update(new Supplier(supplier_id,name,phone_no,company));
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Supplier has been updated successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier has not been updated").show();
        }

    }
    public void initialize(URL url, ResourceBundle resources) {
        clmId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        clmCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

        try {
            refreshPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load !").show();
        }
    }

}

