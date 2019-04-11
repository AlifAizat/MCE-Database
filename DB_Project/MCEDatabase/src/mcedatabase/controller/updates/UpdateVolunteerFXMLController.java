/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.updates;

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

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class UpdateVolunteerFXMLController implements Initializable {

    private String buff;
    private Stage stage;
    
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
    private Button buttonUpdate;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField tfFirstname;
    
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

    @FXML
    void onActionCancel(ActionEvent event) {

        this.stage = (Stage) buttonUpdate.getScene().getWindow();
        this.stage.close();
    }

    @FXML
    void onActionUpdate(ActionEvent event) {
        if(!isEmpty())
        {   
            LibrarySingleton.volunteer_instance.setFirstname(tfFirstname.getText());
            LibrarySingleton.volunteer_instance.setLastname(tfLastname.getText());
            LibrarySingleton.volunteer_instance.setCountry(tfCountry.getText());
            LibrarySingleton.volunteer_instance.setEmail(tfEmail.getText());
            LibrarySingleton.volunteer_instance.setCourse(tfCourse.getText());
            LibrarySingleton.volunteer_instance.setIc(tfIC.getText());
            LibrarySingleton.volunteer_instance.setNationality(tfNationality.getText());
            LibrarySingleton.volunteer_instance.setTel(tfTel.getText());
            LibrarySingleton.volunteer_instance.setUniversity(tfUniversity.getText());
            
            DBSingleton.dbDaoFactory.getVolunteerDao().update(LibrarySingleton.volunteer_instance);
            LibrarySingleton.getVolunteertab_instance().setTableVolunteer();
            
            this.stage = (Stage) buttonUpdate.getScene().getWindow();
            this.stage.close();

        } else {
            MainWindowsFXMLController.displayErrorMessage("Warning !", "Please fill this field : \n" + this.buff);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //this.stage = (Stage) buttonCancel.getScene().getWindow();
        
        //Initialize the data
        this.tfFirstname.setText(LibrarySingleton.volunteer_instance.getFirstname());
        this.tfLastname.setText(LibrarySingleton.volunteer_instance.getLastname());
        this.tfCountry.setText(LibrarySingleton.volunteer_instance.getCountry());
        this.tfCourse.setText(LibrarySingleton.volunteer_instance.getCourse());
        this.tfEmail.setText(LibrarySingleton.volunteer_instance.getEmail());
        this.tfIC.setText(LibrarySingleton.volunteer_instance.getIc());
        this.tfNationality.setText(LibrarySingleton.volunteer_instance.getNationality());
        this.tfTel.setText(LibrarySingleton.volunteer_instance.getTel());
        this.tfUniversity.setText(LibrarySingleton.volunteer_instance.getUniversity());
    }    
    
}
