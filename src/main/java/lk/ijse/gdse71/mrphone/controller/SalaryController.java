package lk.ijse.gdse71.mrphone.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.mrphone.BO.BOFactory;
import lk.ijse.gdse71.mrphone.BO.custom.SalaryBO;
import lk.ijse.gdse71.mrphone.BO.custom.impl.SalaryBOImpl;
import lk.ijse.gdse71.mrphone.dto.SalaryDto;
import lk.ijse.gdse71.mrphone.dao.custom.impl.SalaryDAOImpl;
import lombok.Setter;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmAmount;

    @FXML
    private TableColumn<?, ?> clmDate;

    @FXML
    private TableColumn<?, ?> clmEId;

    @FXML
    private TableColumn<?, ?> clmSId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEId;

    @FXML
    private Label lblSId;

    @FXML
    private TableView<?> tblSalary;

    @FXML
    private TextField txtAmount;

    @Setter
    private EmployeeController employeeController;

    @FXML
    void DeleteOnAction(ActionEvent event) {

    }

    @FXML
    void ResetOnAction(ActionEvent event) {

    }

    @FXML
    void SalaryOnMouseClick(MouseEvent event) {

    }
     private final SalaryBO salaryBO = (SalaryBO) BOFactory.getInstance().getBO(BOFactory.BOType.SALARY);
    @FXML
    void SaveOnAction(ActionEvent event) {
        String salaryId = lblSId.getText();
        String orderId = lblEId.getText();
        String salarytext = txtAmount.getText();
        Date date = Date.valueOf(lblDate.getText());

        if (!salarytext.matches("^\\d+(\\.\\d{1,2})?$")){
            new Alert(Alert.AlertType.ERROR, "Invalid Salary !").show();
            txtAmount.requestFocus();
            return;
        }
        double salary = Double.parseDouble(salarytext);

//        SalaryDto salaryDto = new SalaryDto(salaryId,orderId,salary,date);

        try {
            boolean isSave = salaryBO.save(new SalaryDto(salaryId,orderId,salary,date));
            if (isSave){
                //refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Salary Saved").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new  Alert(Alert.AlertType.ERROR,"Salary Save Error").show();
        }


    }

    public void setEmpolyeeId(String empolyeeId) {

        lblEId.setText(empolyeeId);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clmEId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        clmSId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        clmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

        SalaryDAOImpl salaryDAOImpl = new SalaryDAOImpl();
    private void loadNextSalaryId() throws Exception {
        String nextSalaryID = salaryBO.getNextId();
        lblSId.setText(nextSalaryID);
    }
    private void refreshPage() throws Exception {
        loadNextSalaryId();
        loadAllSalary();

        lblEId.setText("");
        txtAmount.clear();

    }
    private void loadAllSalary() throws Exception {

    }
}
