package lk.ijse.CakeBakery.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import lk.ijse.CakeBakery.model.item;
import lk.ijse.CakeBakery.tm.itemTM;
import lk.ijse.CakeBakery.util.CrudUtil;
import lk.ijse.CakeBakery.util.ValidationUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import static com.lowagie.text.pdf.PdfName.TM;

public class ItemFormController {
    public AnchorPane ItemFormContext;
    public Button btnAdd;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TableView<itemTM> tblItem;
    public TableColumn tblColumId;
    public TableColumn tblColumnName;
    public TableColumn tblColumnDescription;
    public TableColumn tblColumnUnitPrice;
    public TableColumn tblColumnOption;
    public TextField txtQty;
    public TextField txtItemID;
    public Button btnSearch;
    public TableColumn tblColumnQtyOnHand;

    //public TableColumn tblColumnQty;
    //public TableView tblSupOrderTable;
    //public TableColumn colsupOrderCode;
    //public TableColumn colSupOrderName;
    //public Button btnADDSupplierOrder;
    //public JFXComboBox cmbSupplierOrderCode;
    //public TableColumn colIngredientName;
    //public TextField txtIngredientQty;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    Pattern idPattern = Pattern.compile("^(I00-)[0-9]{3,5}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
    Pattern discriptionPattern = Pattern.compile("^[A-z ]{4,30}$");
    Pattern unitPricePattern = Pattern.compile("^[0-9]{3,5}$");
    Pattern qtyonhandPattern= Pattern.compile("^[0-9]{1,5}$");

    public void initialize() throws SQLException, ClassNotFoundException {

        btnAdd.setDisable(true);
        ValidateUnit();

        tblColumId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblColumnUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblColumnQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblColumnOption.setCellValueFactory(new PropertyValueFactory<>("btn1"));
        loadAllItems();
        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                try {
                    itemTM selectedItem = (itemTM) tblItem.getSelectionModel().getSelectedItem();
                    txtItemID.setText(selectedItem.getItemId());
                    txtName.setText(selectedItem.getName());
                    txtDescription.setText(selectedItem.getDescription());
                    txtUnitPrice.setText(selectedItem.getUnitPrice());
                    txtQty.setText(selectedItem.getQtyOnHand());

                    search();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                btnAdd.setText("Update");
                //setData((RoomRM) newValue);
            }
        });

    }
    private void ValidateUnit() {
        btnAdd.setDisable(true);
        map.put(txtItemID,idPattern);
        map.put(txtName,namePattern);
        map.put(txtDescription,discriptionPattern);
        map.put(txtUnitPrice,unitPricePattern);
        map.put(txtQty,qtyonhandPattern);

    }

    public void AddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (btnAdd.getText().equals("Update")){
            item i = new item(
                    txtItemID.getText(),txtName.getText(), txtDescription.getText(),Integer.parseInt(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()));

            try{
                boolean isUpdated = CrudUtil.execute("UPDATE Item SET name=? , description=? , unitPrice=? , qtyOnHand=? WHERE itemId=?",i.getName(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand(),i.getItemId());
                if (isUpdated){
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                    loadAllItems();
                    btnAdd.setText("+ ADD Item");
                }else{
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }

            }catch (SQLException | ClassNotFoundException e){

            }
        }else {
            item i = new item(
                    txtItemID.getText(),txtName.getText(), txtDescription.getText(),Integer.parseInt(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()));

            try {
                if (CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?,?)",i.getItemId(),i.getName(),i.getDescription(),i.getUnitPrice(),i.getQtyOnHand())){
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

            clearAllTexts();
            loadAllItems();
        }
    }
    private void loadAllItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Item");
        ObservableList<itemTM> obList = FXCollections.observableArrayList();


        while (result.next()) {
            Button btn1=new Button("Delete");
            btn1.setOnAction(event -> {
                itemTM selectedItem= (itemTM) tblItem.getSelectionModel().getSelectedItem();
                txtItemID.setText(selectedItem.getItemId());


                deleteItem();

                try {
                    loadAllItems();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            obList.add(
                    new itemTM(
                            result.getString(1),
                            result.getString(2),
                            result.getString(3),
                            result.getString(4),
                            result.getString(5),
                            btn1
                    )
            );

        }
        tblItem.setItems(obList);
        clearAllTexts();

    }

    private void deleteItem() {
        try{

            if(CrudUtil.execute("DELETE FROM Item WHERE itemId=?",txtItemID.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
                loadAllItems();
                btnAdd.setText("Save Deliver");
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

    }

    private void clearAllTexts() {
        txtItemID.clear();
        txtName.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        txtItemID.requestFocus();
        setBorders(txtItemID,txtName,txtDescription,txtUnitPrice,txtQty);


    }
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }



    private void search() throws ClassNotFoundException, SQLException {
        ResultSet result = (ResultSet) CrudUtil.execute("SELECT * FROM Item WHERE itemId=?",txtItemID.getText());
        if (result.next()) {
            txtName.setText(result.getString(2));
            txtDescription.setText(result.getString(3));
            txtQty.setText(result.getString(4));
            txtUnitPrice.setText(result.getString(5));


        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void itemIdOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void txtFeild_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}
