/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.details;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Program;
import mcedatabase.model.entity.Volunteer;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class DetailsVolunteerFXMLController implements Initializable {

    private MainWindowsFXMLController grandparent;
    private Volunteer volunteer;
    private List<Program> notInvolvedProgram;
    private LibrarySingleton ls;
    
    @FXML
    private Text textID;

    @FXML
    private Text textLastname;

    @FXML
    private Text textIC;

    @FXML
    private Text textNationality;

    @FXML
    private Text textUniversity;

    @FXML
    private Text textCountry;

    @FXML
    private Text textCourse;

    @FXML
    private Text textTel;

    @FXML
    private Text textEmail;

    @FXML
    private Text textFirstname;

    @FXML
    private TableView<Program> tableProgramDetailsVol;

    @FXML
    private TableColumn<Program, String> columnVDId;

    @FXML
    private TableColumn<Program, String> columnVDName;

    @FXML
    private TableColumn<Program, String> columnVDSD;

    @FXML
    private ChoiceBox<Program> choiceboxDV;
    
    public void setTableProgramDetailsVol(){
        this.tableProgramDetailsVol.getItems().clear();
        this.tableProgramDetailsVol.setItems(FXCollections.observableArrayList(volunteer.getPrograms()));
    }
    
    public void setChoiceboxDV(){
        
        this.notInvolvedProgram.clear();
        
        for(Program p : ls.mceLibrary.getPrograms())
        {
            if(!this.volunteer.getPrograms().contains(p))
            {
                this.notInvolvedProgram.add(p);
            }
        }
        
        this.choiceboxDV.getItems().clear();
        this.choiceboxDV.setItems(FXCollections.observableArrayList(this.notInvolvedProgram));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Init the LibrarySingleton
        this.ls = LibrarySingleton.librarySingleton();
        
        //Init the grandparent
        this.grandparent = LibrarySingleton.getParent_instance();
        
        //init the volunteer
        this.volunteer = LibrarySingleton.getVolunteer_instance();
        
        //Init the details of volunteer
        this.textID.setText(Integer.toString(volunteer.getId()));
        this.textFirstname.setText(volunteer.getFirstname());
        this.textLastname.setText(volunteer.getLastname());
        this.textIC.setText(volunteer.getIc());
        this.textNationality.setText(volunteer.getNationality());
        this.textUniversity.setText(volunteer.getUniversity());
        this.textCountry.setText(volunteer.getCountry());
        this.textCourse.setText(volunteer.getCourse());
        this.textTel.setText(volunteer.getTel());
        this.textEmail.setText(volunteer.getEmail());
        
        columnVDId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnVDName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        columnVDSD.setCellValueFactory(program -> {
            if(program.getValue() != null)
            {
                return new SimpleStringProperty(sdf.format(program.getValue().getDatestart()));
                
            }else{
                return new SimpleStringProperty("no date");
            }
        });
        
        this.tableProgramDetailsVol.setItems(FXCollections.observableArrayList(volunteer.getPrograms()));
        
        //Init the choicebox
        this.notInvolvedProgram = new ArrayList<>();
        
        this.choiceboxDV.setConverter(new StringConverter<Program>() {
            @Override
            public String toString(Program program) {
                return new String().format("%s (ID : %s)", program.getName(), program.getId());
            }

            @Override
            public Program fromString(String string) {
                return null;
            }
        });
        
        this.setChoiceboxDV();
        
        
    }   
    
    @FXML
    void onActionDVAddprogram(ActionEvent event) {
        if(!choiceboxDV.getSelectionModel().isEmpty())
        {
            DBSingleton.dbDaoFactory.getParticipationDao().insert(this.volunteer.getId(), choiceboxDV.getSelectionModel().getSelectedItem().getId());
            this.volunteer.getPrograms().add(choiceboxDV.getSelectionModel().getSelectedItem());
            this.setTableProgramDetailsVol();
            this.setChoiceboxDV();
            
            LibrarySingleton.getMaintab_instance().updateStatistic();
            LibrarySingleton.getVolunteertab_instance().setTableVolunteer();
            
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error", "Please select a program to be added");
        }
    }

    @FXML
    void onActionDeleteprogram(ActionEvent event) {
        if(tableProgramDetailsVol.getSelectionModel().getSelectedItem() != null)
        {
            DBSingleton.dbDaoFactory.getParticipationDao().delete(this.volunteer.getId(), tableProgramDetailsVol.getSelectionModel().getSelectedItem().getId());
            this.volunteer.getPrograms().remove(tableProgramDetailsVol.getSelectionModel().getSelectedItem());
            this.setTableProgramDetailsVol();
            this.setChoiceboxDV();
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error", "Please select a program to be deleted");
        }
    }
    
}
