/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import mcedatabase.controller.tabs.MainTabFXMLController;
import mcedatabase.controller.tabs.ProgramTabFXMLController;
import mcedatabase.controller.tabs.VolunteerTabFXMLController;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.*;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class MainWindowsFXMLController implements Initializable {

    private Library mceLibrary;
    
    @FXML
    private Tab main;
    @FXML
    private Tab volunteer;
    @FXML 
    private Tab program;
    @FXML
    private MainTabFXMLController mainTabFXMLController;
    @FXML
    private ProgramTabFXMLController programTabFXMLController;
    @FXML
    private VolunteerTabFXMLController volunteerTabFXMLController;
    
    
    /**
     * Create a modal window generated from a FXMLLoader
     * 
     * @param title         title of the modal window
     * @param fxmlLoader    the FXML document loaded from a resource
     * @param parentWindow  the parent window where the modal window is belong to
     * @return              the stage created
     * @throws IOException  when the resource used is invalid
     */
    public static Stage createModalWindows(String title, FXMLLoader fxmlLoader, Window parentWindow) throws IOException{
        
        Stage stage = new Stage();
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.initOwner(parentWindow);
        return stage;
    }
    
    /**
     * Display a modal window with the type "error"
     * 
     * @param title     title of the window
     * @param message   message passed to the window
     */
    
    public static void displayErrorMessage(String title, String message){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("An error is occured during your action");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Display a modal window with the type "warning"
     * 
     * @param title     title of the window
     * @param message   message passed to the window
     */
    
    public static void displayWarningMessage(String title, String message){
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText("Attention, something is not right in your action");
        alert.setContentText(message);
        alert.showAndWait();
    }
    /*
    * Do the necessary thing when close the application
    */
    public void closeApplication(){
        
        try{
            DBSingleton.con.close();
            System.out.println("Connection close !");
        }catch(SQLException ex){
            System.out.println("Systema okokoko");
        }
        
    }
    
    public MainTabFXMLController getMainTabFXMLController() {
        
        return mainTabFXMLController;
    }

    public ProgramTabFXMLController getProgramTabFXMLController() {
        return programTabFXMLController;
    }

    public VolunteerTabFXMLController getVolunteerTabFXMLController() {
        return volunteerTabFXMLController;
    }

    public Library getMceLibrary() {
        return mceLibrary;
    }
    
    public void initTest(){
        Volunteer v1 = new Volunteer(1,"Alif", "Aizat", "0623783237", "980321036431", "malaysian", "IUTLR", "FR", "IT", "alif@email.com");
        Volunteer v2 = new Volunteer(5,"Omar", "Poyut", "0623783237", "980321036431", "malaysian", "IUTLR", "FR", "IT", "alif@email.com");  
        
        Program p1 = new Program(12, "H4B", "Bosnia", Date.from(Instant.now()), Date.from(Instant.now()), "Hope for Bosnia 2018 this code worked for me. But it works only when I select some another row. How do I run this code everytime a tablecell is selected even if the same row is selected");
        Program p2 = new Program(13, "IGINTERS", "Sabah", Date.from(Instant.now()), Date.from(Instant.now()), "Ok tak");
        Program p3 = new Program(55, "Iftar 2019", "La Rochelle", Date.from(Instant.now()), Date.from(Instant.now()), "Iftar bukak puasa");
        Program p4 = new Program(56, "Buffet 2019", "La Rochelle", Date.from(Instant.now()), Date.from(Instant.now()), "Buffet bukak puasa");
        
        v1.getPrograms().add(p1);
        v1.getPrograms().add(p2);
        v2.getPrograms().add(p2);
        
        p1.getParticipants().add(v1);
        p2.getParticipants().add(v2);
        p2.getParticipants().add(v1);
        
        this.mceLibrary.getVolunteers().add(v1);
        this.mceLibrary.getVolunteers().add(v2);
        this.mceLibrary.getPrograms().add(p1);
        this.mceLibrary.getPrograms().add(p2);
        this.mceLibrary.getPrograms().add(p3);
        this.mceLibrary.getPrograms().add(p4);

  
    }
    
    private void initLibrary(){
        
        List<Volunteer> volunteers = DBSingleton.dbDaoFactory.getVolunteerDao().findAll();
        List<Program> programs = DBSingleton.dbDaoFactory.getProgramDao().findAll();
        List<Participation> participations = DBSingleton.dbDaoFactory.getParticipationDao().findAll();

        this.mceLibrary.setVolunteers(volunteers);
        this.mceLibrary.setPrograms(programs);
        
        for(Participation part : participations)
        {
            this.mceLibrary.getVolunteerViaID(part.getVolunteer()).getPrograms().add(this.mceLibrary.getProgramViaId(part.getProgram()));
            this.mceLibrary.getProgramViaId(part.getProgram()).getParticipants().add(this.mceLibrary.getVolunteerViaID(part.getVolunteer()));
        }
        
        this.mceLibrary.setParticipations(participations);
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
        //Initialize the instance of controller
        LibrarySingleton ls = LibrarySingleton.librarySingleton();
        this.volunteerTabFXMLController = LibrarySingleton.getVolunteertab_instance();
        this.mainTabFXMLController = LibrarySingleton.getMaintab_instance();
        this.programTabFXMLController = LibrarySingleton.getProgramtab_instance();
        
        //Initialize the DB
        try {
            DBSingleton dbsc = DBSingleton.getInstance();
        } catch (SQLException ex) {

        }
        
        //Initialize the library for the local DB
        this.mceLibrary = ls.mceLibrary;
        this.initLibrary();
        
        //Link the child controllers to the parent
        this.mainTabFXMLController.setParent(this);
        this.volunteerTabFXMLController.setParent(this);
        this.programTabFXMLController.setParent(this);
        
        //Init the data into the controller for test
        this.volunteerTabFXMLController.setVolunteers(FXCollections.observableArrayList(mceLibrary.getVolunteers()));
        this.programTabFXMLController.setPrograms(FXCollections.observableArrayList(mceLibrary.getPrograms()));
        
        //Update the main page statistic
        this.mainTabFXMLController.updateStatistic();

    }    
    
}
