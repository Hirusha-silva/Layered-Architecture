package lk.ijse.gdse71.mrphone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.gdse71.mrphone.BO.BOFactory;
import lk.ijse.gdse71.mrphone.BO.custom.EmployeeBO;
import lk.ijse.gdse71.mrphone.dto.EmployeeDto;
import lk.ijse.gdse71.mrphone.dto.tm.EmployeeTm;
import lk.ijse.gdse71.mrphone.dao.custom.impl.EmployeeDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeController {

    @FXML
    private Button btnDelete;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSalary;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPhone;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;



    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employee_id = lblEmployeeId.getText();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = employeeBO.delete(employee_id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Customer deleted successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete empoloyee id").show();
            }
        }

    }

    @FXML
    void resetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();

    }

    @FXML
    void salaryOnAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        openSalryPage(lblEmployeeId.getText());


    }


    @FXML
    void saveOnAction(ActionEvent event) {
        String employeeId = lblEmployeeId.getText();
        String name = txtName.getText();
        String phone_no = txtPhone.getText();
        String address = txtAddress.getText();

        if(!name.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if(!phone_no.matches(".{10}")){
            new Alert(Alert.AlertType.ERROR, "Invalid phone number").show();
            txtPhone.requestFocus();
            return;
        }

       // EmployeeDto employeeDto = new EmployeeDto(employeeId,name,phone_no,address);
        try {
            boolean isSaved = employeeBO.save(new EmployeeDto(employeeId,name,phone_no,address));
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Employee has been saved successfully").show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String employee_id = lblEmployeeId.getText();
        String name = txtName.getText();
        String phone_no = txtPhone.getText();
        String address = txtAddress.getText();

        if(!name.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if(!phone_no.matches(".{10}")){
            new Alert(Alert.AlertType.ERROR, "Invalid phone number").show();
            txtPhone.requestFocus();
            return;
        }

       // EmployeeDto employeeDto = new EmployeeDto(employee_id, name, phone_no, address);
        boolean isUpdate = employeeBO.update(new EmployeeDto(employee_id,name,phone_no,address));
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Employee has been updated successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Fail to update employee id").show();
        }

    }
    @FXML
    void EmployeeOnClick(MouseEvent event) {
        EmployeeTm employeeTm = tblEmployee.getSelectionModel().getSelectedItem();
        if (employeeTm != null) {
            lblEmployeeId.setText(employeeTm.getEmployee_id());
            txtName.setText(employeeTm.getName());
            txtPhone.setText(employeeTm.getPhone_no());
            txtAddress.setText(employeeTm.getAddress());


//            btnSave.setDisable(true);
//            btnUpdate.setDisable(false);
//            btnDelete.setDisable(false);
//            btnMail.setDisable(false);

        }
    }
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllEmployee();
    }
    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load !").show();

        }

    }
    public void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextEmployee();
        loadAllEmployee();

//        btnSave.setDisable(false);
//        btnDelete.setDisable(true);
//        btnUpdate.setDisable(true);
//        btnMail.setDisable(true);

        txtName.clear();
        txtPhone.clear();
        txtAddress.clear();
    }
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOType.EMPLOYEE);
    public void loadNextEmployee() throws SQLException, ClassNotFoundException {
        String nextEmployee = employeeBO.getNextId();
        lblEmployeeId.setText(nextEmployee);

    }

    public void loadAllEmployee() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeTm> observableList = FXCollections.observableArrayList();
        try {
            List<EmployeeDto> employeeDtoList = employeeBO.getAll();
            for (EmployeeDto employeeDto : employeeDtoList) {
                EmployeeTm employeeTm = new EmployeeTm(
                        employeeDto.getEmployee_id(),
                        employeeDto.getName(),
                        employeeDto.getPhone_no(),
                        employeeDto.getAddress()
                );
                observableList.add(employeeTm);
            }
            tblEmployee.setItems(observableList);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    private void openSalryPage(String employeeId) throws SQLException, ClassNotFoundException, IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/salary.fxml"));
        Parent parent = loader.load();
        SalaryController salaryController = loader.getController();
        salaryController.setEmpolyeeId(employeeId);

        salaryController.setEmployeeController(this);

        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(parent));
        stage.show();
    }

}
