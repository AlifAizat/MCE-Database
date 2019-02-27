/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase;

import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.DBSingleton;

/**
 *
 * @author alifaizat
 */
public class MCEDatabase extends Application{
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/MainWindowsFXML.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.setTitle("helo");
        stage.setResizable(false);
        
        MainWindowsFXMLController mainWindowController = fxmlLoader.getController();
        
         stage.setOnHidden(e -> {
             mainWindowController.closeApplication();
             Platform.exit();
         });
        
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SQLException {
        
        
        System.out.println(System.getProperty("user.name"));
        java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
        DBSingleton db = DBSingleton.getInstance();
        System.out.println("test jap :");
        System.out.println(DBSingleton.con);
        launch(args); 
       
        
    }
    
}
