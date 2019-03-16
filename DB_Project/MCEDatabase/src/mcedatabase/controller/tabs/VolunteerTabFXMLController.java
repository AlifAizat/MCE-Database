/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.tabs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Library;
import mcedatabase.model.entity.Volunteer;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class VolunteerTabFXMLController implements Initializable{

    
    private ObservableList<Volunteer> itemsVolunteer;
    private Library mceLibrary;
    private LibrarySingleton ls;
    
    @FXML
    private MainWindowsFXMLController parent;
    
    @FXML
    private AnchorPane volunteerTab;

    @FXML
    private TableView<Volunteer> tableVolunteer;
    
    @FXML
    private TableColumn<Volunteer, String> columnVolunteerId;
    
    @FXML
    private TableColumn<Volunteer, String> columnVolunteerFirstname;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerLastname;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerIc;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerNationality;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerUniversity;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerCountry;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerCourse;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerTel;

    @FXML
    private TableColumn<Volunteer, String> ColumnVolunteerEmail;

    @FXML
    private TableColumn<Volunteer, String> columnVolunteerNbProg;

    @FXML
    private Button buttonVolunteerDetail;

    @FXML
    private Button buttonVolunteerUpdate;

    @FXML
    private Button columnVolunteerDelete;
    
    /**
     * Default constructor
     */
    public VolunteerTabFXMLController(){
        this.itemsVolunteer = FXCollections.observableArrayList();
    }
    
    /**
     * Set the parent window controller to the controller
     * @param parent 
     */
    public void setParent(MainWindowsFXMLController parent){
        this.parent = parent;
    }

    /**
     * Reset the value of volunteers stocked in the table view
     * @param volunteers 
     */
    public void setVolunteers(ObservableList<Volunteer> volunteers) {
        this.tableVolunteer.getItems().clear();
        this.itemsVolunteer.removeAll();
        this.itemsVolunteer.addAll(volunteers);
        this.tableVolunteer.refresh();
    }
    
    /**
     * 
     * @return the instance of MainWindowController
     */
    public MainWindowsFXMLController getParent() {
        return parent;
    }

    
    /**
     * 
     * @return the volunteers stocked in the library
     */
    public ObservableList<Volunteer> getItemsVolunteer() {
        return itemsVolunteer;
    }
    
    /**
     * Initialize the table view 
     */
    public void setTableVolunteer(){
        this.tableVolunteer.getItems().clear();
        this.tableVolunteer.setItems(FXCollections.observableArrayList(this.mceLibrary.getVolunteers()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Init the instances
        this.ls = LibrarySingleton.librarySingleton();
        LibrarySingleton.createVolunteerTabController(this);
        this.mceLibrary = ls.mceLibrary;
        
        // TODO
        columnVolunteerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnVolunteerFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        columnVolunteerLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        columnVolunteerIc.setCellValueFactory(new PropertyValueFactory<>("ic"));
        columnVolunteerNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        columnVolunteerUniversity.setCellValueFactory(new PropertyValueFactory<>("university"));
        columnVolunteerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        columnVolunteerCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        columnVolunteerTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        ColumnVolunteerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnVolunteerNbProg.setCellValueFactory(new PropertyValueFactory<>("nbprogram"));
        
        tableVolunteer.setItems(this.itemsVolunteer);

        
    }    
    
    /**
     * Create a window where the details of the selected volunteer will be displayed
     * @param event when the detail button is being clicked
     */
    @FXML
    void onActionVolunteerDetail(ActionEvent event) {
        
        if(this.tableVolunteer.getSelectionModel().getSelectedItem() != null)
        {
            LibrarySingleton.volunteer_instance = this.tableVolunteer.getSelectionModel().getSelectedItem();
            
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mcedatabase/view/details/DetailsVolunteerFXML.fxml"));
                Stage stage = MainWindowsFXMLController.createModalWindows("Volunteer's details", fxmlLoader, ((Node) event.getSource()).getScene().getWindow());
                stage.showAndWait();
            }catch(IOException ex){
                MainWindowsFXMLController.displayErrorMessage("Error", ex.getMessage());
            }
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error", "Please select a volunteer");
        }
        
    }
    
    /**
     *Create a window where the update window for the selected volunteer will be displayed
     * @param event when the update button is being clicked
     */
    @FXML
    void onActionVolunteerUpdate(ActionEvent event) {

        if (this.tableVolunteer.getSelectionModel().getSelectedItem() != null) {
            
            LibrarySingleton.volunteer_instance = this.tableVolunteer.getSelectionModel().getSelectedItem();
            
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mcedatabase/view/updates/UpdateVolunteerFXML.fxml"));
                Stage stage = MainWindowsFXMLController.createModalWindows("Update", fxmlLoader, ((Node) event.getSource()).getScene().getWindow());
                stage.showAndWait();

            } catch (IOException ex) {
                MainWindowsFXMLController.displayErrorMessage("Error", ex.getMessage());
            }
        } else {
            MainWindowsFXMLController.displayErrorMessage("Error", "Please select a volunteer");
        }

        
    }
    
    /**
     * Delete the selected volunteer
     * @param event when the delete button is being clicked
     */
     @FXML
    void onActionVolunteerDelete(ActionEvent event) {
        if(this.tableVolunteer.getSelectionModel().getSelectedItem() != null)
        {
            this.mceLibrary.deleteVolunteer(tableVolunteer.getSelectionModel().getSelectedItem());
            setVolunteers(FXCollections.observableArrayList(this.mceLibrary.getVolunteers()));
            this.parent.getProgramTabFXMLController().setPrograms(FXCollections.observableArrayList(this.mceLibrary.getPrograms()));
            System.out.println("Delete volunteer : succes");
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error", "Please select a volunteer");
        }
    }
    
}
