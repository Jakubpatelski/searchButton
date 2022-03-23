package com.example.searchbutton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductSearchController implements Initializable {
    @FXML
    private TableColumn<ProductSearchModel, String> brandTableColumn;

    @FXML
    private TableColumn<ProductSearchModel, String> descriptionTableColumn;

    @FXML
    private TableColumn<ProductSearchModel, String> modelNumberTableColumn;

    @FXML
    private TableColumn<ProductSearchModel, Integer> modelYearTableColumn;

    @FXML
    private TableColumn<ProductSearchModel, Integer> productIDTableColumn;

    @FXML
    private TableColumn<ProductSearchModel, String> productNameTableColumn;

    @FXML
    private TableView<ProductSearchModel> productTableView;

    @FXML
    private TextField searchTextField;

    ObservableList<ProductSearchModel> productSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.connection();

        String productViewQuery = "SELECT ProductId, ProductName, Brand, ModelNumber, ModelYear, Description FROM Product";

        try {

            Statement statement = connectDb.createStatement();
            ResultSet queryOutput = statement.executeQuery(productViewQuery);

            while (queryOutput.next()){

                int queryProductID = queryOutput.getInt("ProductID");
                String queryProductName = queryOutput.getString("ProductName");
                String queryBrand = queryOutput.getString("Brand");
                String queryModelNumber = queryOutput.getString("ModelNumber");
                int queryModelYear = queryOutput.getInt("ModelYear");
                String queryDescription = queryOutput.getString("Description");



                productSearchModelObservableList.add(new ProductSearchModel(queryProductID, queryProductName, queryBrand,queryModelNumber,queryModelYear,queryDescription));
            }

            productIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
            productNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
            brandTableColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
            modelNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("modelNumber"));
            modelYearTableColumn.setCellValueFactory(new PropertyValueFactory<>("modelYear"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            productTableView.setItems(productSearchModelObservableList);

            FilteredList<ProductSearchModel> filteredData = new FilteredList<>(productSearchModelObservableList, b -> true);
            searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(productSearchModel -> {
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();

                    if (productSearchModel.getProductName().toLowerCase().indexOf(searchKeyword)> -1){
                        return true;
                    } else {
                        return false;
                    }
                });
            });

            SortedList<ProductSearchModel> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(productTableView.comparatorProperty());
            productTableView.setItems(sortedData);




        } catch (SQLException e){
            Logger.getLogger(ProductSearchController.class.getName()).log(Level.SEVERE, null, e);
        }


    }
}
