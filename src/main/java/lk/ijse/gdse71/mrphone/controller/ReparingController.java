package lk.ijse.gdse71.mrphone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.mrphone.dto.RepairDto;
import lk.ijse.gdse71.mrphone.dto.tm.RepairTm;
import lk.ijse.gdse71.mrphone.model.RepairModel;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReparingController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmCId;

    @FXML
    private TableColumn<?, ?> clmDate;

    @FXML
    private TableColumn<?, ?> clmDescription;

    @FXML
    private TableColumn<?, ?> clmRId;

    @FXML
    private TableColumn<?, ?> clmStatus;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblReparing;

    @FXML
    private TableView<RepairTm> tblReparing;

    @FXML
    private TextArea txfdescription;

    @FXML
    private TextField txtCustomer;

    @FXML
    private TextField txtStatus;

    @FXML
    void reparingOnMouseClicked(MouseEvent event) {
        RepairTm repairTm = tblReparing.getSelectionModel().getSelectedItem();
        if (repairTm != null) {
            lblReparing.setText(repairTm.getRepairing_id());
            txtCustomer.setText(repairTm.getCustomer_id());
            txfdescription.setText(repairTm.getDescription());
            lblDate.setText(repairTm.getDate().toString());
            txtStatus.setText(repairTm.getStatus());

        }
    }

    @FXML
    void DeleteOnAction(ActionEvent event) throws Exception {
        String reparing_id = lblReparing.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = repairModel.deleteReparing(reparing_id);
            if (isDeleted) {

                new Alert(Alert.AlertType.INFORMATION,"Reparing deleted successfully").show();
                refreshPage();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete reparing id").show();
            }
        }

    }

    @FXML
    void ResetOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void SaveOnAction(ActionEvent event) {
        String repairing_id= lblReparing.getText();
        String customer_id = txtCustomer.getText();
        String description = txfdescription.getText();
        Date date = Date.valueOf(lblDate.getText());
        String status = txtStatus.getText();



        RepairDto repairDto = new RepairDto(repairing_id,customer_id,description,date,status);
        try {
            boolean isSaved = RepairModel.save(repairDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Reparing has been saved successfully").show();
            }
        } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "Something went wrong try again").show();
        }
    }



    @FXML
    void UpdateOnAction(ActionEvent event) throws Exception {
        String repairing_id= lblReparing.getText();
        String customer_id = txtCustomer.getText();
        String description = txfdescription.getText();
        Date date = Date.valueOf(lblDate.getText());
        String status = txtStatus.getText();

        RepairDto repairDto = new RepairDto(repairing_id,customer_id,description,date,status);
        boolean isUpdate = repairModel.updateRepair(repairDto);
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Repair has been updated successfully").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Repair has not been updated").show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setVaCellValues();
    }

    private void setVaCellValues() {
        clmRId.setCellValueFactory(new PropertyValueFactory<>("repairing_id"));
        clmCId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to refresh page").show();
        }
    }
    RepairModel repairModel = new RepairModel();

    private void refreshPage() throws Exception {
        lblDate.setText(LocalDate.now().toString());
        lblReparing.setText(repairModel.getNextReparingId());

        loadAllReparing();

        txfdescription.clear();
        txtCustomer.clear();
        txtStatus.clear();

    }

    private void loadAllReparing() {
        ObservableList<RepairTm> obList = FXCollections.observableArrayList();
        try {
            List<RepairDto> repairDtoList = RepairModel.getAll();
            for (RepairDto repairDto : repairDtoList) {
                RepairTm repairTm= new RepairTm(
                        repairDto.getRepairing_id(),
                        repairDto.getCustomer_id(),
                        repairDto.getDescription(),
                        repairDto.getDate(),
                        repairDto.getStatus()
                );
                obList.add(repairTm);
                //System.out.println(customerTm);
            }
            tblReparing.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
