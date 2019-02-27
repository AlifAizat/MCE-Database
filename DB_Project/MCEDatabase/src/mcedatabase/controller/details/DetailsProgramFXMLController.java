/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcedatabase.controller.details;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import mcedatabase.model.db.singleton.LibrarySingleton;
import mcedatabase.model.entity.Program;
import mcedatabase.model.entity.Volunteer;

/**
 * FXML Controller class
 *
 * @author alifaizat
 */
public class DetailsProgramFXMLController implements Initializable {

    @FXML
    private Text textId;

    @FXML
    private Text textName;

    @FXML
    private Text textLocation;

    @FXML
    private Text textSD;

    @FXML
    private Text textED;

    @FXML
    private Text textDesc;

    
    @FXML
    private TableView<Volunteer> tableviewDPP;

    @FXML
    private TableColumn<Volunteer, String> columnDPId;

    @FXML
    private TableColumn<Volunteer, String> columnDPFirstname;

    @FXML
    private TableColumn<Volunteer, String> columnDPLastname;

    @FXML
    private TableColumn<Volunteer, String> columnDPCountry;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Init the selected program
        Program prog = LibrarySingleton.program_instance;
        
        //Date formatter
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        //Init the program's details
        this.textId.setText(Integer.toString(prog.getId()));
        this.textName.setText(prog.getName());
        this.textLocation.setText(prog.getLocation());
        this.textDesc.setText(prog.getDescription());
        this.textSD.setText(sdf.format(prog.getDatestart()));
        this.textED.setText(sdf.format(prog.getDateend()));
        
        //init the tableview
        this.columnDPId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnDPFirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        this.columnDPLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        this.columnDPCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        this.tableviewDPP.setItems(FXCollections.observableArrayList(prog.getParticipants()));
        
    }    
    
}
