/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.tabs;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Program;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import mcedatabase.model.entity.Library;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class ProgramTabFXMLController implements Initializable {

    private ObservableList<Program> itemsProgram;
    private MainWindowsFXMLController parent;
    private LibrarySingleton ls;
    private Library mceLibray;
    
    @FXML
    private AnchorPane programTab;

    @FXML
    private TableView<Program> tableProgram;

    @FXML
    private TableColumn<Program, String> columnProgramId;

    @FXML
    private TableColumn<Program, String> columnProgramName;

    @FXML
    private TableColumn<Program, String> columnProgramLocation;

    @FXML
    private TableColumn<Program, String> columnProgramSD;

    @FXML
    private TableColumn<Program, String> columnProgramED;

    @FXML
    private TableColumn<Program, String> columnProgramDesc;

    /**
     * Default constructor
     */
    public ProgramTabFXMLController(){
        this.itemsProgram = FXCollections.observableArrayList();
    }
    
    /**
     * Set the parent window controller to the controller
     * @param parent 
     */
    public void setParent(MainWindowsFXMLController parent){
        this.parent = parent;
    }
    
    public void setPrograms(ObservableList<Program> programs){
        this.tableProgram.getItems().clear();
        this.itemsProgram.removeAll();
        this.itemsProgram.addAll(programs);
        this.tableProgram.refresh();
    }
    
    /**
     * Initialize the table view
     */
    public void setTableProgram(){
        this.tableProgram.getItems().clear();
        this.tableProgram.setItems(FXCollections.observableArrayList(ls.mceLibrary.getPrograms()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initialize the instances
        this.ls = LibrarySingleton.librarySingleton();
        LibrarySingleton.createProgramTabController(this);
        this.mceLibray = ls.mceLibrary;
        
        //Date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        //Init the table column
        columnProgramId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProgramName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnProgramLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnProgramDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        this.columnProgramSD.setCellValueFactory(program -> {
            if(program.getValue() != null){
                return new SimpleStringProperty(sdf.format(program.getValue().getDatestart()));
            }
            else{
                return new SimpleStringProperty("no date");
            }
        });
        
        this.columnProgramED.setCellValueFactory(program -> {
            if(program.getValue() != null){
                return new SimpleStringProperty(sdf.format(program.getValue().getDateend()));
            }
            else{
                return new SimpleStringProperty("no date");
            }
        });
        
        this.tableProgram.setItems(itemsProgram);
    } 
    
    @FXML
    void onActionProgramDelete(ActionEvent event) {
        if(tableProgram.getSelectionModel().getSelectedItem() != null)
        {
            this.mceLibray.deleteProgram(tableProgram.getSelectionModel().getSelectedItem());
            this.setPrograms(FXCollections.observableArrayList(mceLibray.getPrograms()));
            this.parent.getVolunteerTabFXMLController().setVolunteers(FXCollections.observableArrayList(mceLibray.getVolunteers()));
            System.out.println("Delete program : succes");
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error", "Please select a program");
        }
    }

    @FXML
    void onActionProgramDetail(ActionEvent event) {
        if(tableProgram.getSelectionModel().getSelectedItem() != null)
        {
            LibrarySingleton.program_instance = this.tableProgram.getSelectionModel().getSelectedItem();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mcedatabase/view/details/DetailsProgramFXML.fxml"));
                Stage stage = MainWindowsFXMLController.createModalWindows("Program's detail", fxmlLoader, ((Node) event.getSource()).getScene().getWindow());
                stage.showAndWait();
            }catch(IOException ex){
                MainWindowsFXMLController.displayErrorMessage("Error", ex.getMessage());
            }
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error", "Please selcet a program");
        }
    }

    @FXML
    void onActionProgramUpdate(ActionEvent event) {
            MainWindowsFXMLController.displayWarningMessage("Error", "Not yet implemented!");
    }
    
}
