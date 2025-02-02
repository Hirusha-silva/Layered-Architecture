package lk.ijse.gdse71.mrphone.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse71.mrphone.dao.custom.ItemDAO;
import lk.ijse.gdse71.mrphone.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse71.mrphone.dao.custom.impl.ItemDetailDAOImpl;
import lk.ijse.gdse71.mrphone.dto.ItemDetailDto;
import lk.ijse.gdse71.mrphone.dto.ItemDto;
import lk.ijse.gdse71.mrphone.dto.tm.ItemTm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemController {

    @FXML
    private Button btnDelete;


    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TableColumn<?, ?> clmBrand;

    @FXML
    private TableColumn<?, ?> clmDescription;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableColumn<?, ?> clmQty;

    @FXML
    private Label lblItem;

    @FXML
    private Label lblInventoryId;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML


    public void initialize() throws ClassNotFoundException, SQLException {
        setCellValueFactory();
        loadAllItem();

    }

    ItemDAOImpl itemDAOImpl = new ItemDAOImpl();
    ItemDetailDAOImpl itemDetailDAOImpl = new ItemDetailDAOImpl();

    public void loadNextItem() throws SQLException, ClassNotFoundException {
        String nextItemId = itemDAOImpl.getNextId();
        lblItem.setText(nextItemId);
    }

    public void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        clmBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        clmQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Fail to load ").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextItem();
        loadAllItem();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);


        txtPrice.clear();
        txtBrand.clear();
        txtQty.clear();
        txtDescription.clear();



    }

    public void loadAllItem() throws  ClassNotFoundException {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        try{
            List<ItemDto> itemDtoList = itemDAOImpl.getAll();
            for (ItemDto itemDto : itemDtoList) {
                ItemTm itemTm = new ItemTm(
                  itemDto.getItem_id(),
                   itemDto.getPrice(),
                  itemDto.getBrand(),
                  itemDto.getQty(),
                  itemDto.getDescription()
                );
                obList.add(itemTm);
            }
            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void deleteItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        String item_id = lblItem.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this item?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = itemDAOImpl.delete(item_id);
            if (isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Item deleted").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Fail to delete item ").show();
            }
        }
    }

    @FXML
    void resetItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void saveItem(ActionEvent event) {
        String item_id = lblItem.getText();
        String priceText = txtPrice.getText();
        String brand = txtBrand.getText();
        String qtyText = txtQty.getText();
        String description = txtDescription.getText();
        String inventory_id = lblInventoryId.getText();


        if (!priceText.matches("^\\d+(\\.\\d{1,2})?$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid price").show();
            txtPrice.requestFocus();
            return;
        }
        double price = Double.parseDouble(priceText);

        if(!brand.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid brand").show();
            txtBrand.requestFocus();
            return;
        }else if (!qtyText.matches("^\\d+(\\.\\d{1,2})?$")){
            new Alert(Alert.AlertType.ERROR,"Invalid qty").show();
            txtQty.requestFocus();
            return;
        }
        int qty = Integer.parseInt(qtyText);


//        if (!price.matches("\\$?\\d+(\\.\\d{1,2})?")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid price").show();
//            txtPrice.requestFocus();
//            return;
//        }else if(!brand.matches("[A-Za-z0-9 ]+")){
//            new Alert(Alert.AlertType.ERROR,"Invalid brand").show();
//            txtBrand.requestFocus();
//            return;
//        }else if (!qty.matches("^\\d+(\\.\\d{1,2})?$")){
//            new Alert(Alert.AlertType.ERROR,"Invalid qty").show();
//            txtQty.requestFocus();
//            return;
//        }else if (!description.matches("^(?s).*?[a-zA-Z]+.*$")){
//            new Alert(Alert.AlertType.ERROR,"Invalid description").show();
//            txtDescription.requestFocus();
//            return;
//        }

        ItemDto itemDto = new ItemDto(item_id, price, brand, qty, description);
        ItemDetailDto itemDetailDto = new ItemDetailDto(item_id, inventory_id);
        try {
            boolean isSavedI = itemDAOImpl.save(itemDto);
            boolean isSavedID = itemDetailDAOImpl.saveItem(itemDetailDto);

            if (isSavedI && isSavedID) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION,"Item Saved").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Fail to add item").show();
        }

    }

    @FXML
    void updateItem(ActionEvent event) throws SQLException, ClassNotFoundException {
        String item_id = lblItem.getText();
        String priceText = txtPrice.getText();
        String brand = txtBrand.getText();
        String qtyText = txtQty.getText();
        String description = txtDescription.getText();
        String inventory_id = lblInventoryId.getText();


        if (!priceText.matches("^\\d+(\\.\\d{1,2})?$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid price").show();
            txtPrice.requestFocus();
            return;
        }
        double price = Double.parseDouble(priceText);

         if(!brand.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid brand").show();
            txtBrand.requestFocus();
            return;
        }else if (!qtyText.matches("^\\\\d+$")){
            new Alert(Alert.AlertType.ERROR,"Invalid qty").show();
            txtQty.requestFocus();
            return;
        }
         int qty = Integer.parseInt(qtyText);
         //else if (!description.matches("^(?s).*?[a-zA-Z]+.*$")){
//            new Alert(Alert.AlertType.ERROR,"Invalid description").show();
//            txtDescription.requestFocus();
//            return;
//        }

        ItemDto itemDto = new ItemDto(item_id, price, brand, qty, description);
        ItemDetailDto itemDetailDto = new ItemDetailDto(item_id, inventory_id);

        boolean isUpdateI = itemDAOImpl.update(itemDto);
        boolean isUpdateID = itemDetailDAOImpl.updateItem(itemDetailDto);

        if (isUpdateI && isUpdateID) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION,"Item Updated").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Fail to update item !").show();
        }

    }
    @FXML
    void OnMouseClickItem(MouseEvent event) {
        ItemTm itemTm = tblItem.getSelectionModel().getSelectedItem();
        if (itemTm != null) {
            lblItem.setText(itemTm.getItem_id());
            txtPrice.setText(String.valueOf(itemTm.getPrice()));
            txtBrand.setText(itemTm.getBrand());
            txtQty.setText(String.valueOf(itemTm.getQty()));
            txtDescription.setText(itemTm.getDescription());

            btnSave.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
}
