/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package point_of_sell;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class ChatFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage)throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("messenger.fxml"));
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root,478,396));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
