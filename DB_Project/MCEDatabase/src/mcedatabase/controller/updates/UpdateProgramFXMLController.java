/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.updates;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.db.singleton.LibrarySingleton;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class UpdateProgramFXMLController implements Initializable {

    private String buff;
    private Stage stage;
    
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

    @FXML
    private Button buttonCancel;

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
    
    @FXML
    void onActionCancel(ActionEvent event) {
        this.stage = (Stage) buttonCancel.getScene().getWindow();
        this.stage.close();
    }

    @FXML
    void onActionUpdate(ActionEvent event) {
        
        this.stage = (Stage) buttonCancel.getScene().getWindow();
        
        if(!isEmpty()) {
            
            
            Calendar dateStart = Calendar.getInstance();
            Calendar dateEnd = Calendar.getInstance();
            dateStart.set(dpStartDate.getValue().getYear(), dpStartDate.getValue().getMonthValue(), dpStartDate.getValue().getDayOfMonth());
            dateEnd.set(dpEndDate.getValue().getYear(), dpEndDate.getValue().getMonthValue(), dpEndDate.getValue().getDayOfMonth());
            
            LibrarySingleton.getProgram_instance().setName(tfName.getText());
            LibrarySingleton.getProgram_instance().setLocation(tfLocation.getText());
            LibrarySingleton.getProgram_instance().setDescription(taDesc.getText());
            LibrarySingleton.getProgram_instance().setDatestart(dateStart.getTime());
            LibrarySingleton.getProgram_instance().setDateend(dateEnd.getTime());
            
            DBSingleton.dbDaoFactory.getProgramDao().update(LibrarySingleton.getProgram_instance());
            LibrarySingleton.getProgramtab_instance().setTableProgram();
            this.stage.close();

        }else{
            MainWindowsFXMLController.displayErrorMessage("Error ! ", buff);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.tfName.setText(LibrarySingleton.getProgram_instance().getName());
        this.tfLocation.setText(LibrarySingleton.getProgram_instance().getLocation());
        this.taDesc.setText(LibrarySingleton.getProgram_instance().getDescription());
        this.dpStartDate.setValue(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(LibrarySingleton.getProgram_instance().getDatestart())));
        this.dpEndDate.setValue(LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(LibrarySingleton.getProgram_instance().getDateend())));
        
        
    }    
    
}
