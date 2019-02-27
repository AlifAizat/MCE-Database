/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.tabs;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.controller.forms.AddVolunteerFXMLController;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Library;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class MainTabFXMLController implements Initializable {

    private MainWindowsFXMLController parent;
    private LibrarySingleton ls;
    private Library mceLibrary;
    
    @FXML
    private AnchorPane mainTab;

    @FXML
    private Text username;

    @FXML
    private Text date;

    @FXML
    private Button buttonAddVolunteer;

    @FXML
    private Button buttonAddProgram;

    @FXML
    private Text nbVolunteers;

    @FXML
    private Text nbPrograms;
    
    public MainTabFXMLController(){
        
    }
    
    public void setParent(MainWindowsFXMLController parent){
        this.parent = parent;
    }
    
    public void updateStatistic(){
        this.nbVolunteers.setText(Integer.toString(mceLibrary.getVolunteers().size()));
        this.nbPrograms.setText(Integer.toString(mceLibrary.getPrograms().size()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        //Set the username for conected user and the date
        this.username.setText(System.getProperty("user.name"));
        this.date.setText(sdf.format(Date.from(Instant.now())));
        
        //Initialize the instances
        this.ls = LibrarySingleton.librarySingleton();
        LibrarySingleton.createMainTabController(this);
        this.mceLibrary = ls.mceLibrary;
        
        
    }    
    
    @FXML
    void onActionAddProgram(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mcedatabase/view/forms/AddProgramFXML.fxml"));
            Stage stage = MainWindowsFXMLController.createModalWindows("Insert a new volunteer", fxmlLoader, ((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

        } catch (IOException ex) {

        }
    }

    @FXML
    void onActionAddVolunteer(ActionEvent event) {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mcedatabase/view/forms/AddVolunteerFXML.fxml"));
                Stage stage = MainWindowsFXMLController.createModalWindows("Insert a new volunteer", fxmlLoader, ((Node) event.getSource()).getScene().getWindow());
                stage.showAndWait();
                
            }catch(IOException ex){
                
            }
    }
    
}
