/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.forms;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Program;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class AddProgramFXMLController implements Initializable {

    private MainWindowsFXMLController grandparent;
    private LibrarySingleton ls;
    private Stage stage;
    private String buff;
    
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLocation;

    @FXML
    private TextArea taDesc;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpEndDate;
    
    @FXML
    private Button buttonRegister;

    /**
     * Verify if all of form is filled
     * @return 
     */
    public boolean isEmpty(){
        boolean isEmpty = true;
        
         this.buff = "";
        
         if(this.tfName.getText().equals(""))
         {
             buff += "name \n";
         }
         if(this.tfLocation.getText().equals(""))
         {
             buff += "location \n";
         }
         if(this.dpStartDate.getValue() ==  null)
         {
             buff += "start date \n";
         }
         if(this.dpEndDate.getValue() == null)
         {
             buff += "end date \n";
         }
         if(this.taDesc.getText().equals(""))
         {
             buff += "description \n";
         }
        
        if(buff.length() == 0)
        {
            isEmpty = false;
        }else{
            isEmpty = true;
        }
        
        return isEmpty;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        //Init the grandparent
        ls = LibrarySingleton.librarySingleton();
        this.grandparent = LibrarySingleton.getParent_instance();

        //Init the display for the date picker
        dpStartDate.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
        
        //Init the display for the date picker
        dpEndDate.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) {
                    return "";
                }
                return dateTimeFormatter.format(localDate);
            }

            @Override
            public LocalDate fromString(String dateString) {
                if (dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });
    }    
    
    /**
     * Cancel the operation
     * Close the window without applying any changes
     * @param event when the button cancel is being clicked
     */
    @FXML
    void onActionCancel(ActionEvent event) {
        this.stage = (Stage) buttonRegister.getScene().getWindow();
        this.stage.close();
    }

    /**
     * Empty the text field, text area and the date picker
     * @param event when the button empty is being clicked
     */
    @FXML
    void onActionEmpty(ActionEvent event) {
        this.tfName.clear();
        this.tfLocation.clear();
        this.taDesc.clear();
        this.dpStartDate.setValue(null);
        this.dpEndDate.setValue(null);
    }

    /**
     * Create a new Program object and then save it in the database
     * @param event 
     */
    @FXML
    void onActionRegister(ActionEvent event) {

        if (!this.isEmpty()) {

            if (dpStartDate.getValue().compareTo(dpEndDate.getValue()) <= 0) {
                //Create the obect of program
                Calendar dateStart = Calendar.getInstance();
                Calendar dateEnd = Calendar.getInstance();
                dateStart.set(dpStartDate.getValue().getYear(), dpStartDate.getValue().getMonthValue(), dpStartDate.getValue().getDayOfMonth());
                dateEnd.set(dpEndDate.getValue().getYear(), dpEndDate.getValue().getMonthValue(), dpEndDate.getValue().getDayOfMonth());
                
                Program newProgram = new Program(tfName.getText(), tfLocation.getText(), dateStart.getTime(), dateEnd.getTime(), taDesc.getText());
                DBSingleton.dbDaoFactory.getProgramDao().insert(newProgram);
                
                //Insert the new program into the database
                this.ls.mceLibrary.getPrograms().add(DBSingleton.dbDaoFactory.getProgramDao().findLastInserted());
                
                LibrarySingleton.getMaintab_instance().updateStatistic();
                LibrarySingleton.getProgramtab_instance().setTableProgram();

                this.stage = (Stage) buttonRegister.getScene().getWindow();
                this.stage.close();
            }
            else{
                MainWindowsFXMLController.displayErrorMessage("Error!", "The program must end in the next or the same day");
            }

        } else {
            MainWindowsFXMLController.displayErrorMessage("Error!", "Please fill this field : \n \n" + this.buff);
        }
    }
}
