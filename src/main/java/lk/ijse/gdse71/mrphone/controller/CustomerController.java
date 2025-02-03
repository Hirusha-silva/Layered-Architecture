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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.gdse71.mrphone.BO.BOFactory;
import lk.ijse.gdse71.mrphone.BO.custom.CustomerBO;
import lk.ijse.gdse71.mrphone.dao.CrudDAO;
import lk.ijse.gdse71.mrphone.db.DBConnection;
import lk.ijse.gdse71.mrphone.dto.CustomerDto;
import lk.ijse.gdse71.mrphone.dto.tm.CustomerTm;
import lk.ijse.gdse71.mrphone.dao.custom.impl.CustomerDAOImpl;

import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;

import java.sql.Connection;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CustomerController  {

    @FXML
    private Button btnOrderReport;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnMail;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmEmail;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPhone;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private Label lblCustomerId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtEmail;

    @FXML
    void sendmail(ActionEvent event) {
        CustomerTm selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            new Alert(Alert.AlertType.WARNING,"Please select customer..! ").show();
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/sendMail.fxml"));
            Parent load = loader.load();

            SendMailController controller = loader.getController();

            String email = selectedItem.getEmail();
            controller.setCustomerEmail(email);

            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Send Mail");
            stage.initModality(Modality.APPLICATION_MODAL);
            Window window = btnUpdate.getScene().getWindow();
            stage.initOwner(window);
            stage.showAndWait();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to load UI !").show();
            e.printStackTrace();
        }
    }

    @FXML
    void onMouseClickCustomer(MouseEvent event) {
        CustomerTm customerTm = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTm != null) {
            lblCustomerId.setText(customerTm.getCustomer_id());
            txtName.setText(customerTm.getName());
            txtPhone.setText(customerTm.getPhone_no());
            txtEmail.setText(customerTm.getEmail());


            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnMail.setDisable(false);

        }
    }
    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllCustomer();
    }
   // CustomerModel customerModel = new CustomerModel();
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    public void loadNextCustomer() throws SQLException, ClassNotFoundException {
        String nextCustomerId = customerBO.getNextId();
        lblCustomerId.setText(nextCustomerId);

    }
    public void refreshPage() throws ClassNotFoundException, SQLException {
        loadNextCustomer();
        loadAllCustomer();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnMail.setDisable(true);

        txtName.clear();
        txtPhone.clear();
        txtEmail.clear();
    }
   public void setCellValueFactory(){
        clmId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
        refreshPage();
    } catch (Exception e) {
        e.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
    }
    }
    public void loadAllCustomer() throws ClassNotFoundException {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> customerDtoList = customerBO.getAll();
            for (CustomerDto customerDto : customerDtoList) {
                CustomerTm customerTm = new CustomerTm(
                        customerDto.getCustomer_id(),
                        customerDto.getName(),
                        customerDto.getPhone_no(),
                        customerDto.getEmail()
                );
                obList.add(customerTm);
                //System.out.println(customerTm);
            }
            tblCustomer.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customer_id = lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = customerBO.delete(customer_id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Customer deleted successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete customer id").show();
            }
        }
    }

    @FXML
    void saveCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
        String customer_Id = lblCustomerId.getText();
        String name = txtName.getText();
        String phone_no = txtPhone.getText();
        String email = txtEmail.getText();

        if(!name.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if(!phone_no.matches(".{10}")){
            new Alert(Alert.AlertType.ERROR, "Invalid phone number").show();
            txtPhone.requestFocus();
            return;
        }else if (!email.matches("[A-Za-z0-9]+@gmail.com")){
            new Alert(Alert.AlertType.ERROR, "Invalid email").show();
            txtEmail.requestFocus();
            return;
        }

        //CustomerDto customerDto = new CustomerDto(customer_Id, name, phone_no, email);
        try {
            boolean isSaved = customerBO.save(new CustomerDto(customer_Id,name,phone_no,email));
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Customer has been saved successfully").show();
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }
    @FXML
    void resetCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customer_id = lblCustomerId.getText();
        String name = txtName.getText();
        String phone_no = txtPhone.getText();
        String email = txtEmail.getText();

        if(!name.matches("[A-Za-z]+")){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return;
        }else if(!phone_no.matches(".{10}")){
            new Alert(Alert.AlertType.ERROR, "Invalid phone number").show();
            txtPhone.requestFocus();
            return;
        }else if (!email.matches("[A-Za-z0-9]+@gmail.com")){
            new Alert(Alert.AlertType.ERROR, "Invalid email").show();
            txtEmail.requestFocus();
            return;
        }

       // CustomerDto customerDto = new CustomerDto(customer_id, name, phone_no, email);
        boolean isUpdate = customerBO.update(new CustomerDto(customer_id,name,phone_no,email));
        if (isUpdate) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer has been updated successfully").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Fail to update customer id").show();
        }
    }

    @FXML
     void orderReportOnAction(ActionEvent event) {
        CustomerTm customerTM = tblCustomer.getSelectionModel().getSelectedItem();

        if (customerTM == null) {
            return;
        }
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/CustomerOrdersReport.jrxml"));

            Connection connection = DBConnection.getInstance().getConnection();

//            Map<String, Object> parameters = new HashMap<>();
            Map<String, Object> parameter = new HashMap<>();

            parameter.put("P_Date", LocalDate.now().toString());
            parameter.put("P_Customer_Id", customerTM.getCustomer_id());

           JasperPrint print = JasperFillManager.fillReport(jasperReport, parameter, connection);

            JasperViewer.viewReport(print, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();

        }catch (SQLException  | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "DB erro").show();
        }
    }
    }
