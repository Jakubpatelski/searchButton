package com.example.searchbutton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("productsearch.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1080, 600);
//        stage.setTitle("Product Search");
//        stage.setScene(scene);
//        stage.show();
        Parent root= FXMLLoader.load(getClass().getResource("productsearch.fxml"));
        //Scene scene= new Scene(root);
        stage.setTitle("product!");
        stage.setScene(new Scene(root, 1080, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}