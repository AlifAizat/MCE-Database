/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.forms;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mcedatabase.controller.MainWindowsFXMLController;
import mcedatabase.model.db.singleton.DBSingleton;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Volunteer;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class AddVolunteerFXMLController implements Initializable {

    private MainWindowsFXMLController grandparent;
    private LibrarySingleton ls;
    private Stage stage;
    public String buff;
    
     @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfIC;

    @FXML
    private TextField tfNationality;

    @FXML
    private TextField tfUniversity;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfCourse;

    @FXML
    private TextField tfTel;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirstname;
    
    @FXML
    private Button buttonRegister;

    @FXML
    private Button buttonEmpty;

    @FXML
    private Button buttonCancel;

    private boolean isEmpty(){
        boolean isEmpty = true;
        
        this.buff = "";

        
        if(tfFirstname.getText().equals(""))
        {
            buff += "first name \n";
        }
        if(tfLastname.getText().equals(""))
        {
            buff += "last name \n";
        }
        if(tfIC.getText().equals(""))
        {
            buff += "IC \n";
        }
        if(tfNationality.getText().equals(""))
        {
            buff += "nationality \n";
        }
        if(tfUniversity.getText().equals(""))
        {
            buff += "university \n";
        }
        if(tfCountry.getText().equals(""))
        {
            buff += "country \n";
        }
        if(tfCourse.getText().equals(""))
        {
            buff += "course \n";
        }
        if(tfTel.getText().equals(""))
        {
            buff += "tel number \n";
        }
        if(tfEmail.getText().equals(""))
        {
            buff += "email \n";
        }
        
        //Verify if the buff is null or not
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
        
        //Init grandparent
        ls = LibrarySingleton.librarySingleton();
        this.grandparent = LibrarySingleton.getParent_instance();
        
    }   
    
    @FXML
    void onActionCancel(ActionEvent event) {
        this.stage = (Stage) buttonRegister.getScene().getWindow();
        this.stage.close();
    }

    @FXML
    void onActionEmpty(ActionEvent event) {
        this.tfFirstname.clear();
        this.tfLastname.clear();
        this.tfIC.clear();
        this.tfNationality.clear();
        this.tfUniversity.clear();
        this.tfCountry.clear();
        this.tfCourse.clear();
        this.tfTel.clear();
        this.tfEmail.clear();
    }

    @FXML
    void onActionRegister(ActionEvent event) {
        if(!isEmpty())
        {
            Volunteer newVolunteer = new Volunteer(tfFirstname.getText(), tfLastname.getText(), tfTel.getText(), tfIC.getText(), tfNationality.getText(), tfUniversity.getText(), tfCountry.getText(), tfCourse.getText(), tfEmail.getText());
            DBSingleton.dbDaoFactory.getVolunteerDao().insert(newVolunteer);
            
            this.ls.mceLibrary.getVolunteers().add(DBSingleton.dbDaoFactory.getVolunteerDao().findLastInserted());
            LibrarySingleton.getMaintab_instance().updateStatistic();
            LibrarySingleton.getVolunteertab_instance().setTableVolunteer();
            
            this.stage = (Stage) buttonRegister.getScene().getWindow();
            this.stage.close();
            
            
        }else{
            MainWindowsFXMLController.displayErrorMessage("Error!", "Please fill this field : \n" + this.buff);
        }
    }
    
}
